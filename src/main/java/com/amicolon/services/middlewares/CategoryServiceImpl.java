package com.amicolon.services.middlewares;

import com.amicolon.domain.Category;
import com.amicolon.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class CategoryServiceImpl implements CategoryService
{
	private final CategoryRepository categoryRepository;

	@Autowired
	public CategoryServiceImpl(CategoryRepository categoryRepository)
	{
		this.categoryRepository = categoryRepository;
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
}
