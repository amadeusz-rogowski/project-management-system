package com.amicolon.controllers;

import com.amicolon.commands.CategoryCommand;
import com.amicolon.domain.Category;
import com.amicolon.services.middlewares.CategoryService;
import com.amicolon.services.middlewares.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
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
	public String getCategoryPage(@PathVariable String id, Model model)
	{
		Category category = categoryService.findCategoryById(Long.valueOf(id));

		model.addAttribute("category", category);

		return "category";
	}

	@RequestMapping("/category/new")
	public String newCategory(Model model)
	{
		model.addAttribute("category", new CategoryCommand());

		return "forms/category/categoryform";
	}

	@RequestMapping("/category/{id}/update")
	public String updateCategory(@PathVariable String id, Model model)
	{
		model.addAttribute("category", categoryService.findCommandById(Long.valueOf(id)));

		return "forms/category/categoryform";
	}

	@RequestMapping("forms/category/")
	public String saveOrUpdateCategory(@ModelAttribute CategoryCommand categoryCommand)
	{
		CategoryCommand savedCategoryCommand = categoryService.saveCategoryCommand(categoryCommand);

		return "redirect:/panel";
	}

	@GetMapping("/category/{id}/delete")
	public String deleteCategoryById(@PathVariable Long id)
	{
		log.debug("Deleting category id: " + id);
		categoryService.deleteCategoryById(id);

		return "redirect:/panel";
	}
}
