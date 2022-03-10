package com.zageno.TechAssesment.dto;


import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class URLDocument
{

	@NotNull(message = "URL Cannot be URL")
	private String actualURL;

	private Instant createDate;
	@NotNull(message = "duration Cannot be URL")
	private Duration duration;

}
