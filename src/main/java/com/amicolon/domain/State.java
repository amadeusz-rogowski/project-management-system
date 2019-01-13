package com.amicolon.domain;

import com.amicolon.domain.enumerated.StateName;
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
@Table(name = "state")
public class State
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	private StateName stateName;

	@OneToMany(mappedBy = "state", fetch = FetchType.LAZY)
	private Set<Task> tasks = new HashSet<>();
}
