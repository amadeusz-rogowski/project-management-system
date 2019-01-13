package com.amicolon.bootstrap;

import com.amicolon.domain.*;
import com.amicolon.domain.enumerated.LabelName;
import com.amicolon.domain.enumerated.PriorityName;
import com.amicolon.domain.enumerated.StateName;
import com.amicolon.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;

import static com.amicolon.domain.enumerated.LabelName.*;
import static java.time.LocalDate.now;
import static java.util.Arrays.asList;

@Component
public class InitialDataRunner implements ApplicationRunner
{
	private final StateRepository stateRepository;
	private final PriorityRepository priorityRepository;

	private final LabelRepository labelRepository;
	private final TaskRepository taskRepository;

	private final CategoryRepository categoryRepository;

	@Autowired
	public InitialDataRunner(StateRepository stateRepository, PriorityRepository priorityRepository, LabelRepository labelRepository, TaskRepository taskRepository, CategoryRepository categoryRepository)
	{
		this.stateRepository = stateRepository;
		this.priorityRepository = priorityRepository;
		this.labelRepository = labelRepository;
		this.taskRepository = taskRepository;
		this.categoryRepository = categoryRepository;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception
	{
		prepareEnums();

		List<Category> categoryList = asList(prepareCategory("University"),
				prepareCategory("Friends"),
				prepareCategory("Sport"));

		categoryRepository.saveAll(categoryList);
	}

	private Category prepareCategory(String catName)
	{
		Task task1 = prepareTask("First task","drink water",2, getLabel(HEALTH), getLabel(DAILY));
		Task task2 = prepareTask("Second task", "run every day",6,getLabel(DAILY),getLabel(HEALTH));
		Task task3 = prepareTask("Exams are coming", "calculus exam",1,getLabel(EXAM),getLabel(TEST));

		Category category = new Category();
		category.setCategoryName(catName);
		category.addTask(task1);
		category.addTask(task2);
		category.addTask(task3);

		categoryRepository.save(category);

		return category;
	}

	private Label getLabel(LabelName labelName)
	{
		return labelRepository.findByLabelName(labelName).get();
	}

	private Task prepareTask(/*Long id,*/String title, String desc, int months, Label enum1, Label enum2)
	{

		Task task = Task.builder()
				.title(title)
				.description(desc)
				.startDate(now())
				.finishDate(now().plusMonths(months))
				.priority(priorityRepository.findById(3L).get())
				.state(stateRepository.findById(1L).get())
				.labels(new HashSet<>())
				.build();

		task.addLabel(enum1);
		task.addLabel(enum2);

		return task;

	}

	private void prepareEnums()
	{
		List<StateName> states = asList(StateName.values());

		states.forEach(stateName -> {
			State state = new State();
			state.setStateName(stateName);
			stateRepository.save(state);
		});


		List<PriorityName> priorities = asList(PriorityName.values());

		priorities.forEach(priorityName -> {
			Priority priority = new Priority();
			priority.setPriorityName(priorityName);
			priorityRepository.save(priority);
		});

		List<LabelName> labels = asList(LabelName.values());

		labels.forEach(labelName -> {
			Label label = new Label();
			label.setLabelName(labelName);
			labelRepository.save(label);
		});
	}

}
