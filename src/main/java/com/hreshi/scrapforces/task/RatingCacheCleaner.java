package com.hreshi.scrapforces.task;

import org.springframework.stereotype.Component;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.beans.factory.annotation.Autowired;

import com.hreshi.scrapforces.service.RatingMapCache;

@Component
public class RatingCacheCleaner {
	@Autowired 
	RatingMapCache cache;

	@Scheduled(cron = "0 0 6 * * *")
	public void cleanCacheFromMemory () {
		cache.clear();
	}
}