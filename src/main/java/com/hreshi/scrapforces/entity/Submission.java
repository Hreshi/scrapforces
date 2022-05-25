package com.hreshi.scrapforces.entity;

public class Submission {
	public Integer id;
	public Integer contestId;
	public Problem problem;
	public String verdict;

	public Submission () {}

	public boolean isAccepted () {
		return verdict.equalsIgnoreCase("ok");
	}
	public Problem getProblem () {
		return problem;
	}
	
}