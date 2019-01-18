package com.amicolon.domain;

import com.amicolon.domain.enumerated.LabelName;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static java.util.stream.Collectors.joining;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"category"})
@AllArgsConstructor
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

	@ManyToOne
	private Category category;

	@ManyToMany
	@JoinTable(name = "task_label",
			joinColumns = @JoinColumn(name = "task_id"), inverseJoinColumns = @JoinColumn(name = "label_id"))
	private Set<Label> labels = new HashSet<>();

	public Task addLabel(Label label)
	{
		this.getLabels().add(label);
		return this;
	}

	public String getLabelNames()
	{
		return labels.stream()
				.map(Label::getLabelName)
				.map(LabelName::toString)
				.collect(joining(","));
	}
}
