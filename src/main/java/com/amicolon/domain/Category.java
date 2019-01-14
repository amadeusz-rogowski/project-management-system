package com.amicolon.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "category")
public class Category
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "category_name")
	private String categoryName;

	@Lob
	@Column(name = "image")
	private Byte[] image;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "category", orphanRemoval = true)
//	@JoinTable(name = "category_task", joinColumns = { @JoinColumn(name = "category_id") }, inverseJoinColumns = { @JoinColumn(name = "task_id") })
	private Set<Task> tasks = new HashSet<>();

	public Category addTask(Task task)
	{
		task.setCategory(this);
		this.getTasks().add(task);
		return this;
	}
}
