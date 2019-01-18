package com.amicolon.controllers;

import com.amicolon.commands.CategoryCommand;
import com.amicolon.services.middlewares.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class CategoryController
{
	private final CategoryService categoryService;

	@Autowired
	public CategoryController(CategoryService categoryService)
	{
		this.categoryService = categoryService;
	}

	@GetMapping("/category/{id}")
	public String displayCategoryById(@PathVariable Long id, Model model)
	{
		model.addAttribute("category", categoryService.findCategoryById(id));

		return "category";
	}

	@GetMapping("/category/{id}/delete")
	public String deleteCategoryById(@PathVariable Long id)
	{
		categoryService.deleteCategoryById(id);

		return "redirect:/panel";
	}

	@GetMapping("/category/new")
	public String createNewCategoryUsingCategoryCommand(Model model)
	{
		model.addAttribute("categorycommand", new CategoryCommand());

		return "forms/category/categoryform";
	}

	@GetMapping("/category/{id}/update")
	public String updateCategoryUsingCategoryCommand(@PathVariable Long id, Model model)
	{
		model.addAttribute("categorycommand", categoryService.obtainCategoryCommandById(id));

		return "forms/category/categoryform";
	}

	@PostMapping("/forms/category")
	public String saveOrUpdateCategory(@ModelAttribute CategoryCommand categoryCommand)
	{
		categoryService.persistCategoryInDatabaseUsingCategoryCommand(categoryCommand);

		return "redirect:/panel";
	}
}
