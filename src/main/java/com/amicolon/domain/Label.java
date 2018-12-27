package com.amicolon.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Label
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String labelName;

	public Label()
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

	public String getLabelName()
	{
		return labelName;
	}

	public void setLabelName(String labelName)
	{
		this.labelName = labelName;
	}
}
