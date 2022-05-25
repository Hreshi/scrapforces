package com.hreshi.scrapforces.configuration;

import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.DeserializationFeature;

import com.hreshi.scrapforces.service.SubmissionFetcher;
import com.hreshi.scrapforces.service.ProblemFilter;

@Component
public class Configuration {
	
	@Bean
	public ObjectMapper configObjectMapper () {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.configure(DeserializationFeature.ACCEPT_FLOAT_AS_INT, true);
		return mapper;
	}

	@Bean
	@Scope("prototype")
	public SubmissionFetcher getSubmissionFetcher (@Autowired ObjectMapper mapper) {
		SubmissionFetcher fetcher = new SubmissionFetcher(mapper);
		return fetcher;
	}

	@Bean
	@Scope("prototype")
	public ProblemFilter getProblemFilter (@Autowired SubmissionFetcher fetcher) {
		ProblemFilter filter = new ProblemFilter(fetcher);
		return filter;
	}
}