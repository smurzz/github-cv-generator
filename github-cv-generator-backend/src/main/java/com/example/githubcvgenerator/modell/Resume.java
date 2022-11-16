package com.example.githubcvgenerator.modell;

import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Resume {

	@JsonProperty("nameOwner")
	private String nameOwner;
	@JsonProperty("loginOwner")
	private String loginOwner;
	@JsonProperty("blogOfOwner")
	private String blogOfOwner;
	@JsonProperty("dateOfCreation")
	private String dateOfCreation;
	@JsonProperty("numOfRepos")
	private Integer numOfRepos;
	@JsonProperty("followers")
	private Integer followers;
	@JsonProperty("languages")
	private HashMap<String, Double> languages;
	@JsonProperty("repoDetails")
	private HashMap<Integer, RepoResumeDetails> repoDetails;

	/**
	 *  Default constructor
	 */

	public Resume() {

	}

	/**
	 * 
	 */
	public Resume(String nameOwner, String loginOwner, String blogOfOwner, String dateOfCreation, Integer numOfRepos,
			Integer followers, HashMap<String, Double> languages, HashMap<Integer, RepoResumeDetails> repoDetails) {
		this.nameOwner = nameOwner;
		this.loginOwner = loginOwner;
		this.blogOfOwner = blogOfOwner;
		this.dateOfCreation = dateOfCreation;
		this.numOfRepos = numOfRepos;
		this.followers = followers;
		this.languages = languages;
		this.repoDetails = repoDetails;
	}

	/**
	 * 
	 */
	public Resume(Owner owner, List<Repo> repos) {
		this.nameOwner = owner.getName();
		this.loginOwner = owner.getLogin();
		this.blogOfOwner = owner.getBlog();
		this.dateOfCreation = owner.getCreatedAt();
		this.numOfRepos = owner.getPublicRepos();
		this.followers = owner.getFollowers();
		this.languages = languagesPerc(repos);
		this.repoDetails = reposDetails(repos);
	}
	
	// Getters 
	public String getNameOwner() {
		return nameOwner;
	}
	
	public String getLoginOwner() {
		return loginOwner;
	}
	
	public String getBlogOfOwner() {
		return blogOfOwner;
	}
	
	public String getDateOfCreation() {
		return dateOfCreation;
	}
	
	public Integer getNumOfRepos() {
		return numOfRepos;
	}
	
	public Integer getfollowers() {
		return followers;
	}
	
	public HashMap<String, Double> getLanguages() {
		return languages;
	}
	
	public HashMap<Integer, RepoResumeDetails>  getRepoDetails() {
		return repoDetails;
	}
	
	// Setters
	public void setNameOwner(String nameOwner) {
		this.nameOwner = nameOwner;
	}
	
	public void setLoginOwner(String loginOwner) {
		this.loginOwner = loginOwner;
	}
	
	public void setBlogOfOwner(String blogOfOwner) {
		this.blogOfOwner = blogOfOwner;
	}
	
	public void setDateOfCreation(String dateOfCreation) {
		this.dateOfCreation = dateOfCreation;
	}
	
	public void setNumOfRepos(Integer numOfRepos) {
		this.numOfRepos = numOfRepos;
	}
	
	public void setfollowers(Integer followers) {
		this.followers = followers;
	}
	
	public void setLanguages(HashMap<String, Double> languages) {
		this.languages = languages;
	}
	
	public void setRepoDetails(HashMap<Integer, RepoResumeDetails> repoDetails) {
		this.repoDetails = repoDetails;
	}
	
	public void setOwnerInfo(Owner owner) {
		this.nameOwner = owner.getName();
		this.loginOwner = owner.getLogin();
		this.blogOfOwner = owner.getBlog();
		this.dateOfCreation = owner.getCreatedAt();
		this.numOfRepos = owner.getPublicRepos();
		this.followers = owner.getFollowers();
	}
	
	public void setRepoInfo(List<Repo> repos) {
		this.languages = languagesPerc(repos);
		this.repoDetails = reposDetails(repos);
	}
	

	public HashMap<String, Double> languagesPerc(List<Repo> repos) {
		
		HashMap<String, Double> langs = new HashMap<String, Double>();
		double onePers = (1.0 / Double.valueOf(repos.size())) * 100.0;
		for (int i = 0; i < repos.size(); i++) {
			if (repos.get(i).getLanguage() == null || repos.get(i).getLanguage() == "") {
				langs.put("Others", langs.containsKey("Others") ? (langs.get("Others") + onePers) : onePers);
			} else {
				langs.put(repos.get(i).getLanguage(),
						langs.containsKey(repos.get(i).getLanguage()) ? (langs.get(repos.get(i).getLanguage()) + onePers) : onePers);
			}
		}
		return langs;
	}
	
	public HashMap<Integer, RepoResumeDetails> reposDetails(List<Repo> repos) {
		
		HashMap<Integer, RepoResumeDetails> reposInfos = new HashMap<Integer, RepoResumeDetails>();		
		repos.stream().forEach(repo -> reposInfos.put(repo.getId(), new RepoResumeDetails(repo)));

		return reposInfos;
	}
	
	public String toString() {
		return this.nameOwner + " has " + this.numOfRepos + " reposs"; 
	}

}
