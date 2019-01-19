package com.amicolon.commands;

import com.amicolon.domain.enumerated.Priority;
import com.amicolon.domain.enumerated.State;
import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskCommand
{
	private Long id;
	private String title;
	private String description;
	private LocalDate startDate;
	private String finishDate;
	private State state;
	private Priority priority;
	private Long categoryId;
}
