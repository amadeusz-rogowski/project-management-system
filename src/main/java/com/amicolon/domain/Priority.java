package com.amicolon.domain;

import com.amicolon.domain.enumerated.PriorityName;

import javax.persistence.*;

@Entity
public class Priority
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	private PriorityName priorityName;

	public Priority()
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

	public PriorityName getPriorityName()
	{
		return priorityName;
	}

	public void setPriorityName(PriorityName priorityName)
	{
		this.priorityName = priorityName;
	}
}
