package com.amicolon.domain;

import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@EqualsAndHashCode(exclude = {"category"})
public class Task
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;
	@Lob
	private String description;

	private LocalDate startDate;
	private LocalDate finishDate;

	@ManyToOne
	private State state;

	@ManyToOne
	private Priority priority;

	@ManyToMany
	@JoinTable(name = "task_label",
		joinColumns = @JoinColumn(name = "task_id"), inverseJoinColumns = @JoinColumn(name = "label_id"))
	private Set<Label> labels = new HashSet<>();

	@ManyToOne
	@JoinColumn(name="category_id", nullable=false)
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

	public LocalDate getStartDate()
	{
		return startDate;
	}

	public void setStartDate(LocalDate startDate)
	{
		this.startDate = startDate;
	}

	public LocalDate getFinishDate()
	{
		return finishDate;
	}

	public void setFinishDate(LocalDate finishDate)
	{
		this.finishDate = finishDate;
	}

	public State getState()
	{
		return state;
	}

	public void setState(State state)
	{
		this.state = state;
	}

	public Priority getPriority()
	{
		return priority;
	}

	public void setPriority(Priority priority)
	{
		this.priority = priority;
	}

	public Set<Label> getLabels()
	{
		return labels;
	}

	public void setLabels(Set<Label> labels)
	{
		this.labels = labels;
	}

	public Category getCategory()
	{
		return category;
	}

	public void setCategory(Category category)
	{
		this.category = category;
	}

	public Task addLabel(Label label)
	{
		this.getLabels().add(label);
		return this;
	}
}
