package com.amicolon.controllers;

import com.amicolon.domain.Category;
import com.amicolon.services.middlewares.CategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(MockitoJUnitRunner.class)
public class PanelControllerTest
{
	@Mock
	private Model model;

	@Mock
	private CategoryService categoryService;

	@InjectMocks
	private PanelController tested;

	@Test
	public void testMockMVC() throws Exception {
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(tested).build();

		mockMvc.perform(get("/"))
				.andExpect(status().isOk())
				.andExpect(view().name("panel"));
	}

	@Test
	public void getPanelPage()
	{
		Set<Category> categories = new HashSet<>();
		categories.add(new Category());
		categories.add(Category.builder().id(4L).build());

		when(categoryService.getAllCategories()).thenReturn(categories);

		ArgumentCaptor<Set<Category>> argumentCaptor = ArgumentCaptor.forClass(Set.class);
		String viewName = tested.getPanelPage(model);

		assertEquals("panel", viewName);
		verify(categoryService,times(1)).getAllCategories();
		verify(model,times(1)).addAttribute(eq("categories"),argumentCaptor.capture());
		assertEquals(2, argumentCaptor.getValue().size());
	}

}