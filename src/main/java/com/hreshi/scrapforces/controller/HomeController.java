package com.hreshi.scrapforces.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;

import com.hreshi.scrapforces.entity.Submission;
import com.hreshi.scrapforces.service.SubmissionFetcher;

import java.util.List;

@Controller
public class HomeController {
	@Autowired
	SubmissionFetcher fetcher;
	
	@GetMapping("stats")
	@ResponseBody
	public List<Submission> sendRatingStats (@RequestParam String handle) {
		return fetcher.getSubmissions(handle);
	}
}