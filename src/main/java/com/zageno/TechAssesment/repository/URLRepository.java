package com.zageno.TechAssesment.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.zageno.TechAssesment.entities.URLEntity;
@Repository
public interface URLRepository  extends MongoRepository<URLEntity, String>
{
}
