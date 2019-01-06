package com.amicolon.services.middlewares;

import com.amicolon.commands.CategoryCommand;
import com.amicolon.domain.Category;

import java.util.Set;

public interface CategoryService
{
	Set<Category> getAllCategories();

	Category findCategoryById(Long id);

	CategoryCommand saveCategoryCommand(CategoryCommand categoryCommand);

	CategoryCommand findCommandById(Long id);
}
