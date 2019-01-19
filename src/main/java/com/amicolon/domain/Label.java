package com.amicolon.domain;

import com.amicolon.domain.enumerated.LabelName;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
//@Table(name = "label")
public class Label
{
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

//	@Enumerated(EnumType.STRING)
	private LabelName labelName;
}
