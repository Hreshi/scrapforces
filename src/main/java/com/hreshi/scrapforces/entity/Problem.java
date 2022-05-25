package com.hreshi.scrapforces.entity;

public class Problem {
	public Integer contestId;
	public String index;
	public String name;
	public Integer rating;
	public String[] tags;
	private static String problemLink = "https://codeforces.com/contest/%s/problem/%s";

	public Problem () {}

	public String getProblemLink () {
		return String.format(problemLink, contestId+"", index);	
	}
	public String code () {
		return index + contestId;
	}
}