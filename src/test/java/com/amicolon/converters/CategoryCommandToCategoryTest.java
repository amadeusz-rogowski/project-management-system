package com.amicolon.converters;

import com.amicolon.commands.CategoryCommand;
import com.amicolon.domain.Category;
import com.amicolon.domain.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Set;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class CategoryCommandToCategoryTest
{
	@Mock
	private Set<Task> tasks;

	private CategoryCommandToCategory tested = new CategoryCommandToCategory();

	@Test
	public void testNullObject()
	{
		assertNull(tested.convert(null));
	}

	@Test
	public void testEmptyObject()
	{
		assertNotNull(tested.convert(new CategoryCommand()));
	}


	@Test
	public void convertCategoryCommandToCategory()
	{
		CategoryCommand source = new CategoryCommand();
		Long id = 5L;
		source.setId(id);
		String categoryName = "university";
		source.setCategoryName(categoryName);
		source.setTasks(tasks);

		Category converted = tested.convert(source);

		assertEquals(id, converted.getId());
		assertEquals(categoryName, converted.getCategoryName());
		assertEquals(tasks, converted.getTasks());

	}
}