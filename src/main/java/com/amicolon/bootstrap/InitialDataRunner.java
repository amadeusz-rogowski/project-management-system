package com.amicolon.bootstrap;

import com.amicolon.domain.*;
import com.amicolon.domain.enumerated.PriorityName;
import com.amicolon.domain.enumerated.StateName;
import com.amicolon.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

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

		List<Category> categoryList = asList(prepareCategory("Main category 1"),
				prepareCategory("Main category 2"),
				prepareCategory("Main category 3"));

		categoryRepository.saveAll(categoryList);
	}

	private Category prepareCategory(String catName)
	{
		Task task = new Task();
		task.setTitle("test-task");
		task.setDescription("making-test");
		task.setStartDate(now());

		Label school = new Label();
		school.setLabelName("school");
		labelRepository.save(school);

		//task.addLabel(school);

		task.setPriority(priorityRepository.findById(3L).get());

		task.setState(stateRepository.findById(2L).get());

		Category category = new Category();
		category.setCategoryName(catName);

		category.addTask(task);

		categoryRepository.save(category);
		taskRepository.save(task);

		return category;
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
	}

}
