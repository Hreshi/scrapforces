package com.hreshi.scrapforces.service;

import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Autowired;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;

import com.hreshi.scrapforces.entity.Submission;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.net.URI;

import java.util.List;

public class SubmissionFetcher {
	ObjectMapper mapper;
	static String urlTemplate = "https://codeforces.com/api/user.status?handle=%s&from=1&count=3";

	public SubmissionFetcher (ObjectMapper mapper) {
		this.mapper = mapper;
	}

	public List<Submission> getSubmissions (String username) {
		String url = String.format(urlTemplate, username);
		try {	
			String responseBody = fetch (url);
			return getListFromJson (responseBody);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return null;
	}

	private String fetch (String url) throws Exception {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest req = HttpRequest.newBuilder().uri(URI.create(url)).build();
		HttpResponse<String> res = client.send(req, BodyHandlers.ofString());
		return res.body();
	}
	private List<Submission> getListFromJson (String body) {
		try {
			JsonNode resultNode = mapper.readTree(body).get("result");
			List<Submission> result = mapper.convertValue(resultNode,new TypeReference<List<Submission>>(){});
			return result;
		} catch (Exception e) {
			System.out.println(e.toString() + ":getListFromJson()");
		}
		return null;
	}
}