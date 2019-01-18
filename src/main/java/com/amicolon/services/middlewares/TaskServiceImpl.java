package com.amicolon.services.middlewares;

import com.amicolon.domain.Category;
import com.amicolon.domain.Task;
import com.amicolon.repositories.CategoryRepository;
import com.amicolon.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class TaskServiceImpl implements TaskService
{
	private final TaskRepository taskRepository;
	private final CategoryRepository categoryRepository;

	@Autowired
	public TaskServiceImpl(TaskRepository taskRepository, CategoryRepository categoryRepository)
	{
		this.taskRepository = taskRepository;
		this.categoryRepository = categoryRepository;
	}

	@Override
	public Set<Task> getAllTasks()
	{
		Set<Task> taskSet = new HashSet<>();
		taskRepository.findAll().iterator().forEachRemaining(taskSet::add);
		return taskSet;
	}

	@Override
	public Task findTaskById(Long id) throws RuntimeException
	{
		Optional<Task> taskOptional = taskRepository.findById(id);

		return taskOptional.orElseGet(() -> {
			throw new RuntimeException("Task not found in database");
		});
	}

	@Override
	public void deleteTaskByIdFromGivenCategoryWithId(Long categoryId, Long taskId)
	{
		Task task = taskRepository.findById(taskId).get();
		Category category = categoryRepository.findById(categoryId).get();

		category.getTasks().remove(task);
		categoryRepository.save(category);
	}
}
