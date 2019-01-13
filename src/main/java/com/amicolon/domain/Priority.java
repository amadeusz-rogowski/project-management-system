package com.amicolon.domain;

import com.amicolon.domain.enumerated.PriorityName;
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
@Table(name = "priority")
public class Priority
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	private PriorityName priorityName;

	@OneToMany(mappedBy = "priority", fetch = FetchType.LAZY)
	private Set<Task> tasks = new HashSet<>();

}
