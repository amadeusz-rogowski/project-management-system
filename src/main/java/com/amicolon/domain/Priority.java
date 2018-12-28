package com.amicolon.domain;

import com.amicolon.domain.enumerated.PriorityName;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Priority
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	private PriorityName priorityName;

	@OneToMany(mappedBy = "priority", fetch = FetchType.LAZY)
	private Set<Task> tasks = new HashSet<>();

	public Priority()
	{
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public PriorityName getPriorityName()
	{
		return priorityName;
	}

	public void setPriorityName(PriorityName priorityName)
	{
		this.priorityName = priorityName;
	}

	public Set<Task> getTasks()
	{
		return tasks;
	}

	public void setTasks(Set<Task> tasks)
	{
		this.tasks = tasks;
	}
}
