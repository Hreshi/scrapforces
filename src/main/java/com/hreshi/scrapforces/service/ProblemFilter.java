package com.hreshi.scrapforces.service;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

import com.hreshi.scrapforces.entity.Submission;
import com.hreshi.scrapforces.entity.Problem;

public class ProblemFilter {
	List<Submission> allSubmissions;
	List<Problem> problems;
	SubmissionFetcher fetcher;
	public ProblemFilter (SubmissionFetcher fetcher) {
		this.fetcher = fetcher;
	}

	public Map<Integer, Integer> getRatingMap (String handle) {
		Map<Integer, Integer> ratingMap = new HashMap<Integer, Integer>();
		Set<String> set = new HashSet<String>();

		allSubmissions = fetcher.getSubmissions(handle);
		long t3 = System.nanoTime();
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