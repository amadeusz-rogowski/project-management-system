package com.amicolon.converters;

import com.amicolon.commands.CategoryCommand;
import com.amicolon.domain.Category;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CategoryToCategoryCommand implements Converter<Category, CategoryCommand>
{
	@Override
	@Synchronized
	@Nullable
	public CategoryCommand convert(Category source)
	{
		if (source == null) {
			return null;
		}

		final CategoryCommand categoryCommand = new CategoryCommand();
		categoryCommand.setId(source.getId());
		categoryCommand.setCategoryName(source.getCategoryName());
		categoryCommand.setImage(source.getImage());
		categoryCommand.setTasks(source.getTasks());

		return categoryCommand;
	}
}
