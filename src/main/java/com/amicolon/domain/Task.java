package com.amicolon.domain;

import com.amicolon.domain.enumerated.PriorityName;
import com.amicolon.domain.enumerated.StateName;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

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

	@Enumerated(EnumType.STRING)
	private PriorityName priorityName;

	@Enumerated(EnumType.STRING)
	private StateName stateName;

	@ManyToOne
	private Category category;

//	@ManyToMany
//	@JoinTable(name = "task_label",
//			joinColumns = @JoinColumn(name = "task_id"), inverseJoinColumns = @JoinColumn(name = "label_id"))
//	private Set<Label> labels = new HashSet<>();
//
//	public Task addLabel(Label label)
//	{
//		this.getLabels().add(label);
//		return this;
//	}
//
//	public String getLabelNames()
//	{
//		return labels.stream()
//				.map(Label::getLabelName)
//				.map(LabelName::toString)
//				.collect(joining(","));
//	}
}
