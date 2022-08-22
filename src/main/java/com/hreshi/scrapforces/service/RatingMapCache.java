package com.hreshi.scrapforces.service;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.HashMap;

public class RatingMapCache {
	Map<String, Map<Integer, Integer>> ratingMapCache;
	
	public RatingMapCache () {
		ratingMapCache = new HashMap<String, Map<Integer, Integer>>();
	}

	public synchronized void put (String handle, Map<Integer, Integer> map) {
		if (ratingMapCache.containsKey(handle)) {
			ratingMapCache.replace(handle, map); 
		} else {
			ratingMapCache.put(handle, map);
		}
	}

	public Map<Integer, Integer> get (String handle) {
		if (ratingMapCache.containsKey (handle)) {
			return ratingMapCache.get(handle);
		}
		return null;
	}

	public synchronized void clear () {
		ratingMapCache.clear();
	}
}