package com.amicolon.converters;

import com.amicolon.commands.TaskCommand;
import com.amicolon.domain.Task;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class TaskCommandToTask implements Converter<TaskCommand, Task>
{
	@Override
	@Synchronized
	@Nullable
	public Task convert(TaskCommand source)
	{
		if (source == null) {
			return null;
		}

		final Task task = new Task();
		task.setId(source.getId());
		task.setTitle(source.getTitle());
		task.setDescription(source.getDescription());
		task.setStartDate(source.getStartDate());
		task.setFinishDate(source.getFinishDate());
		task.setPriority(source.getPriority());
		task.setState(source.getState());
		task.setLabels(source.getLabels());
		task.setCategory(source.getCategory());

		return task;
	}

}
