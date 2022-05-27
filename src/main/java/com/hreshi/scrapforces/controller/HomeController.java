package com.hreshi.scrapforces.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;

import com.hreshi.scrapforces.service.ProblemFilter;

import java.util.Map;

@Controller
public class HomeController {
	@Autowired 
	ProblemFilter filter;
	
	@GetMapping("stats")
	@ResponseBody
	public Map<Integer, Integer> sendRatingStats (@RequestParam String handle) {
		try {
			Map<Integer, Integer> map = filter.getRatingMap(handle);
			return map;
		} catch (Exception e) {
			return null;
		}
	}

	@GetMapping("")
	public String sendHomePage () {
		return "home";
	}
}