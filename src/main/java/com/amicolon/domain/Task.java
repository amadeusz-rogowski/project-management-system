package com.amicolon.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
public class Task
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;
	@Lob
	private String description;
	private LocalDate finishDate;

	@ManyToMany
	@JoinTable(name = "task_state",
			joinColumns = @JoinColumn(name = "task_id"), inverseJoinColumns = @JoinColumn(name = "state_id"))
	private Set<State> states;

	@ManyToMany
	@JoinTable(name = "task_priority",
			joinColumns = @JoinColumn(name = "task_id"), inverseJoinColumns = @JoinColumn(name = "priority_id"))
	private Set<Priority> priorities;

	@ManyToMany
	@JoinTable(name = "task_label",
		joinColumns = @JoinColumn(name = "task_id"), inverseJoinColumns = @JoinColumn(name = "label_id"))
	private Set<Label> labels;

	@ManyToOne
	private Category category;

	public Task()
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

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public LocalDate getFinishDate()
	{
		return finishDate;
	}

	public void setFinishDate(LocalDate finishDate)
	{
		this.finishDate = finishDate;
	}

//	public Set<State> getStates()
//	{
//		return states;
//	}
//
//	public void setStates(Set<State> states)
//	{
//		this.states = states;
//	}
//
//	public Set<Priority> getPriorities()
//	{
//		return priorities;
//	}
//
//	public void setPriorities(Set<Priority> priorities)
//	{
//		this.priorities = priorities;
//	}
//
//	public Set<Label> getLabels()
//	{
//		return labels;
//	}
//
//	public void setLabels(Set<Label> labels)
//	{
//		this.labels = labels;
//	}

	public Category getCategory()
	{
		return category;
	}

	public void setCategory(Category category)
	{
		this.category = category;
	}
}
