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

		return Category.builder()
				.id(source.getId())
				.categoryName(source.getCategoryName())
				.tasks(source.getTasks())
				.build();
	}
}
