package com.amicolon.controllers;

import com.amicolon.services.middlewares.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TaskController
{
	private final TaskService taskService;

	@Autowired
	public TaskController(TaskService taskService)
	{
		this.taskService = taskService;
	}

	@GetMapping
	@RequestMapping("/category/{categoryId}/task/{taskId}/delete")
	public String deleteTask(@PathVariable Long categoryId,@PathVariable Long taskId)
	{
		taskService.deleteTaskByIdFromGivenCategoryWithId(categoryId, taskId);

		return "redirect:/category/" + categoryId;
	}
}
