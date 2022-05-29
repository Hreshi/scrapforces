package com.hreshi.scrapforces.service;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.List;

import com.hreshi.scrapforces.entity.Submission;

@Component
public class RatingMapService {
	@Autowired
	SubmissionFetcher fetcher;
	@Autowired
	ProblemFilter filter;
	@Autowired
	RatingMapCache ratingMapCache;

	public Map<Integer, Integer> getRatingMap (String handle) {
		Map<Integer, Integer> map;
		map = ratingMapCache.get (handle);
		if (map == null) {
			List<Submission> list = fetcher.getSubmissions (handle);
			map = filter.getRatingMap (list);
			if (map != null) ratingMapCache.put (handle, map);
		}
		return map;
	}
}