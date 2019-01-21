package com.amicolon.domain;

import lombok.*;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "category")
public class Category
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "category_name")
	private String categoryName;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "category", orphanRemoval = true)
	private Set<Task> tasks = new LinkedHashSet<>();

	public Category addTask(Task task)
	{
		task.setCategory(this);
		this.getTasks().add(task);
		return this;
	}
}
