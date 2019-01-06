package com.amicolon.converters;

import com.amicolon.commands.CategoryCommand;
import com.amicolon.domain.Category;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;


@Component
public class CategoryCommandToCategory implements Converter<CategoryCommand, Category>
{
	@Override
	@Synchronized
	@Nullable
	public Category convert(CategoryCommand source)
	{
		if (source == null) {
			return null;
		}

		final Category category = new Category();
		category.setId(source.getId());
		category.setCategoryName(source.getCategoryName());
		category.setImage(source.getImage());
		// TODO: forEach( task -> category.getTasks().add(
		category.setTasks(source.getTasks());

		return category;
	}
}
