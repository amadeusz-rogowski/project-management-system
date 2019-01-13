package com.amicolon.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"category"})
@Table(name = "task")
public class Task
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "title")
	private String title;

	@Column(name = "description")
	@Lob
	private String description;

	@Column(name = "start_date")
	private LocalDate startDate;
	@Column(name = "finish_date")
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

	public Task addLabel(Label label)
	{
		this.getLabels().add(label);
		return this;
	}
}
