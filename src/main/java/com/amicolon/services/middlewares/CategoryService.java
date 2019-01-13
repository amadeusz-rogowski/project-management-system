package com.amicolon.services.middlewares;

import com.amicolon.commands.CategoryCommand;
import com.amicolon.domain.Category;

import java.util.Set;

public interface CategoryService
{
	Set<Category> getAllCategories();

	Category findCategoryById(Long id);

	CategoryCommand persistCategoryInDatabaseUsingCategoryCommand(CategoryCommand categoryCommand);

	CategoryCommand obtainCategoryCommandById(Long id);

	void deleteCategoryById(Long id);
}
