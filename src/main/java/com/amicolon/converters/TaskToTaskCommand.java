package com.amicolon.converters;

import com.amicolon.commands.TaskCommand;
import com.amicolon.domain.Task;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE;

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

		return TaskCommand.builder()
				.id(source.getId())
				.categoryId(source.getCategory().getId())
				.title(source.getTitle())
				.description(source.getDescription())
				.startDate(source.getStartDate())
				.finishDate(source.getFinishDate().format(ISO_LOCAL_DATE))
				.priority(source.getPriority())
				.state(source.getState())
				.build();
	}
}
