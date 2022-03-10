package com.zageno.TechAssesment.controller;

import java.util.Objects;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.zageno.TechAssesment.dto.URLDocument;
import com.zageno.TechAssesment.processor.URLRequestProcessor;

@RestController
public class URLResource
{
	private String url ="http:////{localhost}:{8080}//tiny";
	@Autowired
	private URLRequestProcessor processor;
	@Autowired
	private ServerProperties serverProperties;
	@PostMapping("/create")
	public String generateTinyURL(@RequestBody @Valid  URLDocument requestDTO){

		return "http://localhost:8082/tiny/"+
		processor.addURLDocument(requestDTO);
	}

	@GetMapping ("/tiny/{tinyurl}")
	public RedirectView redirect(@PathVariable String tinyurl){

		String url=processor.getAcutalURLWithTinyURL(tinyurl);
		if(Objects.isNull(url)){
			return new RedirectView("/errorpage.html");
		}
		else{
			return	new RedirectView(url);
		}

	}
}
