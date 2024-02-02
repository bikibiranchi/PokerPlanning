package com.mutual.liberty.sessionmanagement.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Vote {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	
	String voteValue;
	
	Long member;
	
	Long userStory;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getVoteValue() {
		return voteValue;
	}

	public void setVoteValue(String voteValue) {
		this.voteValue = voteValue;
	}

	public Long getMember() {
		return member;
	}

	public void setMember(Long member) {
		this.member = member;
	}

	public Long getUserStory() {
		return userStory;
	}

	public void setUserStory(Long userStory) {
		this.userStory = userStory;
	}

	
	
	
}
