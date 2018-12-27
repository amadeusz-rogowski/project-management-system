package com.amicolon.domain;

import com.amicolon.domain.enumerated.StateName;

import javax.persistence.*;
import java.util.Set;

@Entity
public class State
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	private StateName stateName;

	@OneToMany(mappedBy = "state", fetch = FetchType.LAZY)
	private Set<Task> tasks;

	public State()
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

	public StateName getStateName()
	{
		return stateName;
	}

	public void setStateName(StateName stateName)
	{
		this.stateName = stateName;
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
