package com.amicolon.controllers;

import com.amicolon.domain.Category;
import com.amicolon.services.middlewares.CategoryService;
import com.amicolon.services.middlewares.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CategoryController
{
	private final CategoryService categoryService;
	private final TaskService taskService;

	@Autowired
	public CategoryController(CategoryService categoryService, TaskService taskService)
	{
		this.categoryService = categoryService;
		this.taskService = taskService;
	}

	@RequestMapping("/category/{id}")
	public String getPanelPage(@PathVariable String id, Model model)
	{
		Category category = categoryService.findCategoryById(new Long(id));

		model.addAttribute("tasks", category.getTasks());

		return "category";
	}

}
