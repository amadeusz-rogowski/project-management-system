package com.amicolon.converters;

import com.amicolon.commands.TaskCommand;
import com.amicolon.domain.Task;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class TaskToTaskCommand implements Converter<Task, TaskCommand>
{
	@Override
	@Synchronized
	@Nullable
	public TaskCommand convert(Task source)
	{
		if (source == null) {
			return null;
		}

		final TaskCommand taskCommand = new TaskCommand();
		taskCommand.setId(source.getId());
		taskCommand.setTitle(source.getTitle());
		taskCommand.setDescription(source.getDescription());
		taskCommand.setStartDate(source.getStartDate());
		taskCommand.setFinishDate(source.getFinishDate());
		taskCommand.setPriority(source.getPriority());
		taskCommand.setState(source.getState());
		taskCommand.setLabels(source.getLabels());
		taskCommand.setCategory(source.getCategory());

		return taskCommand;
	}
}
