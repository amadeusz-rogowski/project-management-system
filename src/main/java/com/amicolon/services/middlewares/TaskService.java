package com.amicolon.services.middlewares;

import com.amicolon.domain.Task;

import java.util.Set;

public interface TaskService
{
	Set<Task> getAllTasks();

	Task findTaskById(Long id);
}
