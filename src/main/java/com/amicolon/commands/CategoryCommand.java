package com.amicolon.commands;

import com.amicolon.domain.Task;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class CategoryCommand
{
	private Long id;
	private String categoryName;
	private Byte[] image;
	private Set<Task> tasks = new HashSet<>();
}
