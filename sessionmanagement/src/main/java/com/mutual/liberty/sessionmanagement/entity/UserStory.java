package com.mutual.liberty.sessionmanagement.entity;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class UserStory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String userStoryId;
    private String description;
    private String status;
    
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "session_id")
    private PokerSession session;
    
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Vote> emittedVotes ;
    
    public UserStory() {
    	
    }
    
	public UserStory(String userStoryId, String description) {
		this.userStoryId = userStoryId;
		this.description = description;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserStoryId() {
		return userStoryId;
	}
	public void setUserStoryId(String userStoryId) {
		this.userStoryId = userStoryId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Set<Vote> getEmittedVotes() {
		return emittedVotes;
	}

	public void setEmittedVotes(Set<Vote> emittedVotes) {
		this.emittedVotes = emittedVotes;
	}

	public PokerSession getSession() {
		return session;
	}

	public void setSession(PokerSession session) {
		this.session = session;
	}

    
}
