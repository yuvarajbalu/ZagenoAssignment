package com.zageno.TechAssesment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class AnalyticsResponse
{

	private String ActualURL;
	private String TinyURL;
	private Integer redirectCount;

}
