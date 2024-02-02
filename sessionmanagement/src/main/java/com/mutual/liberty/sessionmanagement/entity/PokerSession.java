package com.mutual.liberty.sessionmanagement.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class PokerSession {
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long sessionId;

    private String title;
    
    private String deckType;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Member> members = new HashSet<>();
    
    
    @OneToMany(cascade = CascadeType.ALL)
    private Set<UserStory> userStories = new HashSet<>();

	

	public Long getSessionId() {
		return sessionId;
	}

	public void setSessionId(Long sessionId) {
		this.sessionId = sessionId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDeckType() {
		return deckType;
	}

	public void setDeckType(String deckType) {
		this.deckType = deckType;
	}

	public Set<Member> getMembers() {
		return members;
	}

	public void setMembers(Set<Member> members) {
		this.members = members;
	}

	public Set<UserStory> getUserStories() {
		return userStories;
	}

	public void setUserStories(Set<UserStory> userStories) {
		this.userStories = userStories;
	}

    
    
}
