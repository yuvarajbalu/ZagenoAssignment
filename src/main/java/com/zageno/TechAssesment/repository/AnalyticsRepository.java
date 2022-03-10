package com.zageno.TechAssesment.repository;

import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.zageno.TechAssesment.entities.AnalyticsEntity;

@Repository
public interface AnalyticsRepository extends MongoRepository<AnalyticsEntity, UUID>
{
}
