package com.amicolon.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Category
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String categoryName;

	@Lob
	private Byte[] image;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "category", fetch = FetchType.EAGER)
	private Set<Task> tasks;

	public Category()
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

	public String getCategoryName()
	{
		return categoryName;
	}

	public void setCategoryName(String categoryName)
	{
		this.categoryName = categoryName;
	}

	public Byte[] getImage()
	{
		return image;
	}

	public void setImage(Byte[] image)
	{
		this.image = image;
	}

	public Set<Task> getTasks()
	{
		return tasks;
	}

	public void setTasks(Set<Task> tasks)
	{
		this.tasks = tasks;
	}

	public Category addTask(Task task)
	{
		task.setCategory(this);
		this.tasks.add(task);
		return this;
	}
}
