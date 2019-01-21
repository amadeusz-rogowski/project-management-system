package com.amicolon.services.middlewares;

import com.amicolon.commands.TaskCommand;
import com.amicolon.converters.TaskToTaskCommand;
import com.amicolon.domain.Task;
import com.amicolon.repositories.TaskRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static com.amicolon.domain.enumerated.Priority.HIGH;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TaskServiceImplTest
{

	@Mock
	private TaskRepository taskRepository;

	@Mock
	private TaskToTaskCommand taskToTaskCommand;

	@InjectMocks
	private TaskServiceImpl tested;

	@Test(expected = RuntimeException.class)
	public void shouldThrowExceptionWhenTaskInNotFound()
	{
		Long taskId = 111L;

		tested.findTaskById(taskId);
	}

	@Test
	public void shouldReturnTaskById()
	{
		prepareTaskExample();

		Task taskById = tested.findTaskById(5L);

		assertNotNull(taskById);
		assertEquals(Long.valueOf(5L), taskById.getId());
		assertEquals(HIGH, taskById.getPriority());
		assertEquals("title", taskById.getTitle());
		verify(taskRepository, times(1)).findById(5L);
	}

	@Test
	public void shouldObtainTaskCommandById()
	{
		prepareTaskExample();
		TaskCommand taskCommand = TaskCommand.builder()
				.id(5L)
				.build();
		when(taskToTaskCommand.convert(any())).thenReturn(taskCommand);

		TaskCommand commandById = tested.obtainTaskCommandById(5L);

		assertNotNull(commandById);
		assertEquals(Long.valueOf(5L),commandById.getId());
		verify(taskRepository, times(1)).findById(anyLong());
	}

	private void prepareTaskExample()
	{
		Task task = Task.builder()
				.id(5L)
				.priority(HIGH)
				.title("title")
				.build();
		Optional<Task> taskOptional = Optional.of(task);
		when(taskRepository.findById(5L)).thenReturn(taskOptional);
	}
}