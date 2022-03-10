package com.zageno.TechAssesment.entities;

import java.time.Instant;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "analyticsCollection")
@Getter
@Setter
@Builder
public class AnalyticsEntity
{

	@Id
	private UUID id;
	private String tinyURL;

	private String URL;

	private Instant creationDate;

	private Instant expireDate;

	private  int redirectCount;

}
