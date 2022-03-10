package com.zageno.TechAssesment.entities;

import java.time.Instant;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "urlCollections")
@Getter
@Setter
@NoArgsConstructor
public class URLEntity
{

	@Id
	private String id;

	private String URL;

	private Instant creationDate;

	private Instant expireDate;

	private  int redirectCount;

	private boolean active;
}
