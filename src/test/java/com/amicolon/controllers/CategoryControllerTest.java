package com.amicolon.controllers;

import com.amicolon.domain.Category;
import com.amicolon.services.middlewares.CategoryService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(MockitoJUnitRunner.class)
public class CategoryControllerTest
{

	@Mock
	private CategoryService categoryService;

	@InjectMocks
	private CategoryController tested;

	private MockMvc mockMvc;

	@Before
	public void setUp()
	{
		mockMvc = standaloneSetup(tested)
				.build();
	}

	@Test
	public void testGetTask() throws Exception
	{
		Category category = Category.builder()
				.id(3L)
				.build();

		when(categoryService.findCategoryById(3L)).thenReturn(category);

		mockMvc.perform(get("/category/3"))
				.andExpect(status().isOk())
				.andExpect(view().name("category"))
				.andExpect(model().attributeExists("category"));

	}

}