package com.zageno.TechAssesment.dto;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Duration
{
	@NotNull(message = " NoOf cannot be null or empty")
	private Long number;
	@NotNull(message = " unit cannot be null or empty")
	private Unit unit;
}
