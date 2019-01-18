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

		return CategoryCommand.builder()
				.id(source.getId())
				.categoryName(source.getCategoryName())
				.image(source.getImage())
				.tasks(source.getTasks())
				.build();
	}
}
