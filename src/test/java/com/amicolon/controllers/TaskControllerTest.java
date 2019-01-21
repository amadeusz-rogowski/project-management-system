package com.amicolon.controllers;

import com.amicolon.services.middlewares.TaskService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(MockitoJUnitRunner.class)
public class TaskControllerTest
{

	@Mock
	private TaskService taskService;

	@InjectMocks
	private TaskController tested;

	private MockMvc mockMvc;

	@Before
	public void setUp()
	{
		mockMvc =  standaloneSetup(tested).build();
	}

	@Test
	public void testGetNewTaskForm() throws Exception {

		mockMvc.perform(get("/category/5/task/new"))
				.andExpect(status().isOk())
				.andExpect(view().name("forms/task/taskform"))
				.andExpect(model().attributeExists("taskcommand"));
	}

	@Test
	public void testDeleteAction() throws Exception {
		mockMvc.perform(get("/category/11/task/52/delete"))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/category/11"));

		verify(taskService, times(1)).deleteTaskByIdFromGivenCategoryWithId(anyLong(),anyLong());
	}

}