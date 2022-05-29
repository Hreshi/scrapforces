package com.hreshi.scrapforces.service;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

import com.hreshi.scrapforces.entity.Submission;
import com.hreshi.scrapforces.entity.Problem;

@Component
public class ProblemFilter {
	List<Submission> allSubmissions;
	
	public Map<Integer, Integer> getRatingMap (List<Submission> allSubmissions) {
		Map<Integer, Integer> ratingMap = new HashMap<Integer, Integer>();
		Set<String> set = new HashSet<String>();
		for (Submission sub : allSubmissions) {
			if (sub.isAccepted()) {
				Problem problem = sub.getProblem();
				String code = problem.code();
				if (!set.contains(code)) {
					set.add(code);
					updateMap(ratingMap, problem.rating);
				}
			}		
		}
		return ratingMap;
	}
	private void updateMap (Map<Integer,Integer> map, Integer rating) {
		if (rating == null) rating = 0;
		if (map.containsKey(rating)) {
			map.replace(rating, map.get(rating)+1);
		} else {
			map.put(rating, 1);
		}
	}
}