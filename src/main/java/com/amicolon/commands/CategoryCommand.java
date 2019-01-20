package com.amicolon.commands;

import com.amicolon.domain.Task;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryCommand
{
	private Long id;
	private String categoryName;
	private Set<Task> tasks = new HashSet<>();
}
