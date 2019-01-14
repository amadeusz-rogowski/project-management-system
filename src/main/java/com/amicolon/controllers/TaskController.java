package com.amicolon.controllers;

import com.amicolon.domain.Category;
import com.amicolon.domain.Task;
import com.amicolon.repositories.CategoryRepository;
import com.amicolon.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TaskController
{
	private final CategoryRepository categoryRepository;
	private final TaskRepository taskRepository;

	@Autowired
	public TaskController(CategoryRepository categoryRepository, TaskRepository taskRepository)
	{
		this.categoryRepository = categoryRepository;
		this.taskRepository = taskRepository;
	}

	@GetMapping
	@RequestMapping("/category/{categoryId}/task/{taskId}/delete")
	public String deleteTask(@PathVariable Long categoryId,@PathVariable Long taskId)
	{
		Task task = taskRepository.findById(taskId).get();
		Category category = categoryRepository.findById(categoryId).get();
		category.getTasks().remove(task);
		categoryRepository.save(category);

		return "redirect:/panel";
	}
}
