package com.amicolon.services.middlewares;

import com.amicolon.commands.CategoryCommand;
import com.amicolon.converters.CategoryCommandToCategory;
import com.amicolon.converters.CategoryToCategoryCommand;
import com.amicolon.domain.Category;
import com.amicolon.repositories.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService
{
	private final CategoryRepository categoryRepository;
	private final CategoryCommandToCategory categoryCommandToCategory;
	private final CategoryToCategoryCommand categoryToCategoryCommand;

	@Autowired
	public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryCommandToCategory categoryCommandToCategory, CategoryToCategoryCommand categoryToCategoryCommand)
	{
		this.categoryRepository = categoryRepository;
		this.categoryCommandToCategory = categoryCommandToCategory;
		this.categoryToCategoryCommand = categoryToCategoryCommand;
	}

	@Override
	public Set<Category> getAllCategories()
	{
		Set<Category> categorySet = new HashSet<>();
		categoryRepository.findAll().iterator().forEachRemaining(categorySet::add);
		return categorySet;
	}

	@Override
	public Category findCategoryById(Long id)
	{
		Optional<Category> categoryOptional = categoryRepository.findById(id);

		return categoryOptional.orElseGet(() -> {
			throw new RuntimeException("Category not found in database");
		});
	}

	@Transactional
	@Override
	public CategoryCommand findCommandById(Long id)
	{
		Category foundCategory = findCategoryById(id);

		return categoryToCategoryCommand.convert(foundCategory);
	}

	@Transactional
	@Override
	public CategoryCommand saveCategoryCommand(CategoryCommand categoryCommand)
	{
		Category converted = categoryCommandToCategory.convert(categoryCommand);

		Category saved = categoryRepository.save(converted);
		log.debug("Saved category: " + saved.getId());

		return categoryToCategoryCommand.convert(saved);
	}

	@Override
	public void deleteCategoryById(Long id)
	{
		categoryRepository.deleteById(id);
	}
}
