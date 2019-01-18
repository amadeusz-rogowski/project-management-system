package com.amicolon.controllers;

import com.amicolon.commands.CategoryCommand;
import com.amicolon.services.middlewares.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

	@GetMapping
	@RequestMapping("/category/{id}")
	public String displayCategoryById(@PathVariable String id, Model model)
	{
		model.addAttribute("category", categoryService.findCategoryById(Long.valueOf(id)));

		return "category";
	}

	@GetMapping
	@RequestMapping("/category/{id}/delete")
	public String deleteCategoryById(@PathVariable Long id)
	{
		categoryService.deleteCategoryById(id);

		return "redirect:/panel";
	}

	@GetMapping
	@RequestMapping("/category/new")
	public String createNewCategoryUsingCategoryCommand(Model model)
	{
		model.addAttribute("categorycommand", new CategoryCommand());

		return "forms/category/categoryform";
	}

	@GetMapping
	@RequestMapping("/category/{id}/update")
	public String updateCategoryUsingCategoryCommand(@PathVariable String id, Model model)
	{
		model.addAttribute("categorycommand", categoryService.obtainCategoryCommandById(Long.valueOf(id)));

		return "forms/category/categoryform";
	}

	@PostMapping("/forms/category")
	public String saveOrUpdateCategory(@ModelAttribute CategoryCommand categoryCommand)
	{
		categoryService.persistCategoryInDatabaseUsingCategoryCommand(categoryCommand);

		return "redirect:/panel";
	}
}
