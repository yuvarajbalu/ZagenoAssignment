package com.zageno.TechAssesment.controller;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zageno.TechAssesment.dto.AnalyticsResponse;
import com.zageno.TechAssesment.repository.AnalyticsRepository;
import com.zageno.TechAssesment.repository.URLRepository;

@RestController
public class AnalyticsResource
{

	@Autowired
	AnalyticsRepository repository;
	@Autowired
	URLRepository urlRepository;
	@GetMapping("/useranalytics")
	public List<AnalyticsResponse> getUserAnalytics(){
	List<AnalyticsResponse> list=repository.findAll().stream().map(x->{return
		AnalyticsResponse.builder().ActualURL(x.getURL()).TinyURL(x.getTinyURL()).redirectCount(x.getRedirectCount()).build();}).collect(Collectors.toList());
		List<AnalyticsResponse> activelist=urlRepository.findAll().stream().map(x->{return
			AnalyticsResponse.builder().ActualURL(x.getURL()).TinyURL("http://localhost:8082/tiny/" + x.getId()).redirectCount(x.getRedirectCount()).build();}).collect(Collectors.toList());
		return Stream.concat(list.stream(), activelist.stream())
			.collect(Collectors.toList());
	}
}
