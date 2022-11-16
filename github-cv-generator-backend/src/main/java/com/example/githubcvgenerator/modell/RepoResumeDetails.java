package com.example.githubcvgenerator.modell;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RepoResumeDetails {
	
	@JsonProperty("repoId")
	private Integer repoId;
	
	@JsonProperty("repoName")
	private String repoName;
	
	@JsonProperty("repoDescription")
	private String repoDescription;
	
	@JsonProperty("repoSvnUrl")
	private String repoSvnUrl;
	
	@JsonProperty("repoLanguage")
	private String repoLanguage;
	
	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public RepoResumeDetails() {
	}
	
	/**
	 * 
	 */
	public RepoResumeDetails(Integer repoId, String repoName, String repoDescription, String repoSvnUrl, String repoLanguage) {
		this.repoId = repoId;
		this.repoName = repoName;
		this.repoDescription = repoDescription;
		this.repoSvnUrl = repoSvnUrl;
		this.repoLanguage = repoLanguage;
	}
	
	/**
	 * 
	 */
	public RepoResumeDetails(Repo repo) {
		this.repoId = repo.getId();
		this.repoName = repo.getName();
		this.repoDescription = repo.getDescription();
		this.repoSvnUrl = repo.getSvnUrl();
		this.repoLanguage = repo.getLanguage();
	}
	
	// Getters
	public Integer getRepoId() {
		return repoId;
	}
	
	public String getRepoName() {
		return repoName;
	}
	
	public String getRepoDescription() {
		return repoDescription;
	}
	
	public String getRepoSvnUrl() {
		return repoSvnUrl;
	}
	
	public String setRepoLanguage() {
		return repoLanguage;
	}
	
	// Setters
	public void setRepoId(Integer repoId) {
		this.repoId = repoId;
	}
	
	public void setRepoName(String repoName ) {
		this.repoName = repoName;
	}
	
	public void setRepoDescription(String repoDescription) {
		this.repoDescription = repoDescription;
	}
	
	public void setRepoSvnUrl(String repoSvnUrl) {
		this.repoSvnUrl = repoSvnUrl;
	}
	
	public void setRepoLanguage(String repoLanguage) {
		this.repoLanguage = repoLanguage;
	}
	

}
