package com.amicolon.commands;

import com.amicolon.domain.Label;
import com.amicolon.domain.Priority;
import com.amicolon.domain.State;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

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
	private LocalDate finishDate;
	private State state;
	private Priority priority;
	private Set<Label> labels = new HashSet<>();
	private Long categoryId;
}
