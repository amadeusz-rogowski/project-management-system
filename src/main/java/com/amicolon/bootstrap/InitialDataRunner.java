package com.amicolon.bootstrap;

import com.amicolon.domain.Category;
import com.amicolon.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import java.util.List;

import static java.util.Arrays.asList;

// @Component
public class InitialDataRunner implements ApplicationRunner
{
	private final CategoryRepository categoryRepository;

	@Autowired
	public InitialDataRunner(CategoryRepository categoryRepository)
	{
		this.categoryRepository = categoryRepository;
	}

	@Override
	public void run(ApplicationArguments args)
	{
		List<Category> categoryList = asList(prepareCategory("University"),
				prepareCategory("Work"),
				prepareCategory("Student Organizations"),
				prepareCategory("Hobby"),
				prepareCategory("Shopping"),
				prepareCategory("Friends"),
				prepareCategory("Entertainment"));

		categoryRepository.saveAll(categoryList);
	}

	private Category prepareCategory(String catName)
	{
		Category category = new Category();
		category.setCategoryName(catName);

		categoryRepository.save(category);

		return category;
	}
}
