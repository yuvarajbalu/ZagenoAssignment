package com.zageno.TechAssesment.processor;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zageno.TechAssesment.dto.URLDocument;
import com.zageno.TechAssesment.dto.Unit;
import com.zageno.TechAssesment.entities.URLEntity;
import com.zageno.TechAssesment.service.DAOServiceHandler;
import com.zageno.TechAssesment.utils.CommonUtils;

@Service
public class URLRequestProcessor
{
	@Autowired
	private DAOServiceHandler daoService;

	public String addURLDocument(URLDocument requestObject){

		URLEntity entity=new URLEntity();
		entity.setId(getNextRandomSequence());
		if(Objects.isNull(requestObject.getCreateDate())){
			requestObject.setCreateDate(Instant.now());
		}
		entity.setCreationDate(requestObject.getCreateDate());
		entity.setURL(requestObject.getActualURL());
		entity.setExpireDate(calculateExpireTime(requestObject));
		entity.setActive(true);
		daoService.saveDocument(entity);
		return entity.getId();
	}

	public String getAcutalURLWithTinyURL(String tinyURL){
		URLEntity entity=daoService.getDocument(tinyURL);
		if(Objects.isNull(entity))
			return null;
		else
		{
			if(entity.getExpireDate().isAfter(Instant.now())&& entity.isActive()){
				entity.setRedirectCount(entity.getRedirectCount()+1);
				daoService.saveDocument(entity);
				return entity.getURL();
			}

			else{
				entity.setActive(false);
				daoService.saveDocument(entity);
				daoService.removeExpiredRecord();
				return null;
			}

		}
	}

	private Instant calculateExpireTime(URLDocument requestObject)
	{
		if(requestObject.getDuration().getUnit().equals(Unit.MINUTES)){
			return requestObject.getCreateDate().plus(requestObject.getDuration().getNumber(), ChronoUnit.MINUTES);
		}
		if(requestObject.getDuration().getUnit().equals(Unit.HOURS)){
			return requestObject.getCreateDate().plus(requestObject.getDuration().getNumber(), ChronoUnit.HOURS);
		}
		if(requestObject.getDuration().getUnit().equals(Unit.DAYS)){
			return requestObject.getCreateDate().plus(requestObject.getDuration().getNumber(), ChronoUnit.DAYS);
		}
		return requestObject.getCreateDate().plus(24,ChronoUnit.HOURS);
	}

	private String getNextRandomSequence(){

		String nextSequence=null;
		int counter=0;
		do{
			if(counter>1)
			{
				CommonUtils.randomStringLength++;
			}
			counter++;
			nextSequence=CommonUtils.generateRandomString();
		}while(Objects.nonNull(daoService.getDocument(nextSequence)));
		return nextSequence;
	}
}
