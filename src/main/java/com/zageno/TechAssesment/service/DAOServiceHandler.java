package com.zageno.TechAssesment.service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.zageno.TechAssesment.dto.AnalyticsResponse;
import com.zageno.TechAssesment.dto.URLDocument;
import com.zageno.TechAssesment.entities.AnalyticsEntity;
import com.zageno.TechAssesment.entities.URLEntity;
import com.zageno.TechAssesment.repository.AnalyticsRepository;
import com.zageno.TechAssesment.repository.URLRepository;

@Service
public class DAOServiceHandler
{
	@Autowired
	private URLRepository repository;
	@Autowired
	private  AnalyticsRepository analyticsRepository;
	@Autowired
	private MongoOperations mongoOperation;



	public URLEntity saveDocument(URLEntity document){
		return repository.save(document);

	}

	public URLEntity getDocument(String tinyURL) {
		return repository.findById(tinyURL).orElse(null);
	}

	@Scheduled(cron ="0 0/2 * * * * " )
	public void removeExpiredRecord()
	{

		Query deleteQuery=new Query();
		deleteQuery.addCriteria(Criteria.where("expireDate").lte(Instant.now()));
		System.out.println("No of Records TO BE deleted :"+mongoOperation.count(deleteQuery,URLEntity.class));
		List<URLEntity> deleteList=mongoOperation.find(deleteQuery,URLEntity.class);
		List<AnalyticsEntity> analyticsEntities=deleteList.stream().map(x->
		{
			final AnalyticsEntity build = AnalyticsEntity.builder().expireDate(x.getExpireDate()).creationDate(x.getCreationDate())
				.redirectCount(x.getRedirectCount())
				.tinyURL("http://localhost:8082/tiny/" + x.getId())
				.URL(x.getURL())
				.build();
			return build;
		}).collect(Collectors.toList());
		analyticsRepository.saveAll(analyticsEntities);
		mongoOperation.remove(deleteQuery,URLEntity.class);
	}
}
