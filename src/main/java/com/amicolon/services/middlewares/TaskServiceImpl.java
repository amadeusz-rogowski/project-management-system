package com.amicolon.services.middlewares;

import com.amicolon.commands.TaskCommand;
import com.amicolon.converters.TaskCommandToTask;
import com.amicolon.converters.TaskToTaskCommand;
import com.amicolon.domain.Category;
import com.amicolon.domain.Task;
import com.amicolon.repositories.CategoryRepository;
import com.amicolon.repositories.TaskRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

import static com.amicolon.domain.enumerated.State.FINISHED;

@Slf4j
@Service
public class TaskServiceImpl implements TaskService
{
	private final TaskRepository taskRepository;
	private final CategoryRepository categoryRepository;
	private final TaskCommandToTask taskCommandToTask;
	private final TaskToTaskCommand taskToTaskCommand;

	@Autowired
	public TaskServiceImpl(TaskRepository taskRepository, CategoryRepository categoryRepository, TaskCommandToTask taskCommandToTask, TaskToTaskCommand taskToTaskCommand)
	{
		this.taskRepository = taskRepository;
		this.categoryRepository = categoryRepository;
		this.taskCommandToTask = taskCommandToTask;
		this.taskToTaskCommand = taskToTaskCommand;
	}

	@Override
	public Task findTaskById(Long id)
	{
		Optional<Task> taskOptional = taskRepository.findById(id);

		return taskOptional.orElseGet(() -> {
			throw new RuntimeException("Task #" + id + " not found in database");
		});
	}

	@Override
	public void deleteTaskByIdFromGivenCategoryWithId(Long categoryId, Long taskId) throws RuntimeException
	{
		Task task = taskRepository.findById(taskId).orElseGet(() -> {
			throw new RuntimeException("Task #" + taskId + " not found in database");
		});

		Category category = categoryRepository.findById(categoryId).orElseGet(() -> {
			throw new RuntimeException("Category #" + categoryId + " not found in database");
		});

		category.getTasks().remove(task);
		categoryRepository.save(category);
		log.debug("Deleted category id: " + taskId);
	}

	@Override
	public void finishTaskByIdFromGivenCategoryWithId(Long categoryId, Long taskId)
	{
		Task task = taskRepository.findById(taskId).orElseGet(() -> {
			throw new RuntimeException("Task #" + taskId + " not found in database");
		});

		task.setState(FINISHED);

		taskRepository.save(task);
	}

	@Transactional
	@Override
	public TaskCommand obtainTaskCommandById(Long id)
	{
		Task foundTask = findTaskById(id);

		return taskToTaskCommand.convert(foundTask);
	}

	@Transactional
	@Override
	public void persistTaskInDatabaseUsingTaskCommand(TaskCommand taskCommand)
	{
		Task converted = taskCommandToTask.convert(taskCommand);

		Task saved = taskRepository.save(converted);
		log.debug("Saved category: " + saved.getId());
	}
}
