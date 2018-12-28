package com.amicolon.commands;

import com.amicolon.domain.Task;
import com.amicolon.domain.enumerated.PriorityName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class PriorityCommand
{
	private Long id;
	private PriorityName priorityName;
	private Set<Task> tasks = new HashSet<>();
}
