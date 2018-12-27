package com.amicolon.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
public class Task
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;
	@Lob
	private String description;
	private LocalDate finishDate;

	@Enumerated(value = EnumType.STRING)
	private Status status;

	@Enumerated(value = EnumType.STRING)
	private Priority priority;

	@ManyToMany
	@JoinTable(name = "task_label",
		joinColumns = @JoinColumn(name = "task_id"), inverseJoinColumns = @JoinColumn(name = "label_id"))
	private Set<Label> labels;

	@ManyToOne
	private Category category;
}
