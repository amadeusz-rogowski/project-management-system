package com.amicolon.services.middlewares;

import com.amicolon.domain.Task;
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

	@Autowired
	public TaskServiceImpl(TaskRepository taskRepository)
	{
		this.taskRepository = taskRepository;
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
}
