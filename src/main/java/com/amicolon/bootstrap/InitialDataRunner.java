package com.amicolon.bootstrap;

import com.amicolon.domain.Category;
import com.amicolon.domain.Task;
import com.amicolon.domain.enumerated.Priority;
import com.amicolon.domain.enumerated.State;
import com.amicolon.repositories.CategoryRepository;
import com.amicolon.repositories.TaskRepository;
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
	private final TaskRepository taskRepository;

	private final CategoryRepository categoryRepository;

	@Autowired
	public InitialDataRunner(TaskRepository taskRepository, CategoryRepository categoryRepository)
	{
		this.taskRepository = taskRepository;
		this.categoryRepository = categoryRepository;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception
	{
		List<Category> categoryList = asList(prepareCategory("University"),
				prepareCategory("Friends"),
				prepareCategory("Sport"));

		categoryRepository.saveAll(categoryList);
	}

	private Category prepareCategory(String catName)
	{
		Task task1 = prepareTask("First task", "drink water", 2);
		Task task2 = prepareTask("Second task", "run every day", 6);
		Task task3 = prepareTask("Exams are coming", "calculus exam", 1);

		Category category = new Category();
		category.setCategoryName(catName);
		category.addTask(task1);
		category.addTask(task2);
		category.addTask(task3);

		categoryRepository.save(category);

		return category;
	}

	private Task prepareTask(/*Long id,*/String title, String desc, int months/*, Label enum1, Label enum2*/)
	{

		//		task.addLabel(enum1);
//		task.addLabel(enum2);

		return Task.builder()
				.title(title)
				.description(desc)
				.startDate(now())
				.finishDate(now().plusMonths(months))
				.priority(Priority.LOW)
				.state(State.ACTIVE)
//				.labels(new HashSet<>())
				.build();

	}

	//	private Label getLabel(LabelName labelName)
//	{
//		return labelRepository.findByLabelName(labelName).get();
//	}

}
