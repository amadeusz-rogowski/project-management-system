package com.amicolon.commands;

import com.amicolon.domain.enumerated.PriorityName;
import com.amicolon.domain.enumerated.StateName;
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
	private LocalDate finishDate;
	private StateName state;
	private PriorityName priority;
//	private Set<Label> labels = new HashSet<>();
	private Long categoryId;
}
