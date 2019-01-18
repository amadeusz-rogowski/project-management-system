package com.amicolon.controllers;

import com.amicolon.commands.TaskCommand;
import com.amicolon.services.middlewares.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import static java.time.LocalDate.now;

@Controller
public class TaskController
{
	private final TaskService taskService;

	@Autowired
	public TaskController(TaskService taskService)
	{
		this.taskService = taskService;
	}

	@GetMapping("/category/{categoryId}/task/{taskId}/delete")
	public String deleteTask(@PathVariable Long categoryId, @PathVariable Long taskId)
	{
		taskService.deleteTaskByIdFromGivenCategoryWithId(categoryId, taskId);

		return "redirect:/category/" + categoryId;
	}

	@GetMapping("/category/{id}/task/new")
	public String createNewTaskUsingTaskCommand(@PathVariable Long id, Model model)
	{
		TaskCommand taskCommand = new TaskCommand();
		taskCommand.setCategoryId(id);
		taskCommand.setStartDate(now());

		model.addAttribute("taskcommand", taskCommand);

		return "forms/task/taskform";
	}

	@GetMapping("/category/{categoryId}/task/{taskId}/update")
	public String updateTaskUsingTaskCommand(@PathVariable Long categoryId, @PathVariable Long taskId, Model model)
	{
		model.addAttribute("taskcommand", taskService.obtainTaskCommandById(taskId));

		return "forms/task/taskform";
	}

	@PostMapping("/forms/task")
	public String saveOrUpdateTask(@ModelAttribute TaskCommand taskCommand)
	{
		taskService.persistTaskInDatabaseUsingTaskCommand(taskCommand);

		return "redirect:/category/" + taskCommand.getCategoryId();
	}

}
