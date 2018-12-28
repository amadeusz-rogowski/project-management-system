package com.amicolon.commands;

import com.amicolon.domain.Task;
import com.amicolon.domain.enumerated.StateName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class StateCommand
{
	private Long id;
	private StateName stateName;
	private Set<Task> tasks = new HashSet<>();
}
