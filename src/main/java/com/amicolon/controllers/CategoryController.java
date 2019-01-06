package com.amicolon.controllers;

import com.amicolon.commands.CategoryCommand;
import com.amicolon.domain.Category;
import com.amicolon.services.middlewares.CategoryService;
import com.amicolon.services.middlewares.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

		model.addAttribute("category", category);

		return "category";
	}

	@RequestMapping("/category/new")
	public String newCategory(Model model)
	{
		model.addAttribute("category", new CategoryCommand());

		return "forms/categoryform";
	}

	@PostMapping("forms")
	public String saveOrUpdate(@ModelAttribute CategoryCommand categoryCommand)
	{
		CategoryCommand savedCategoryCommand = categoryService.saveCategoryCommand(categoryCommand);

		return "redirect:/panel";
	}

}
