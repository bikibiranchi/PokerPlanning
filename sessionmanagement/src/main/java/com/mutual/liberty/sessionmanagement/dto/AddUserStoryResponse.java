package com.mutual.liberty.sessionmanagement.dto;

import java.util.HashSet;
import java.util.Set;

import com.mutual.liberty.sessionmanagement.entity.PokerSession;
import com.mutual.liberty.sessionmanagement.entity.UserStory;

public class AddUserStoryResponse {

	private String title;

    private String deckType;
    
    private Set<UserStory> userStories = new HashSet<>();
    
    public AddUserStoryResponse(PokerSession pokerSession) {
    	 this.title = pokerSession.getTitle();
         this.deckType = pokerSession.getDeckType();
         this.userStories = pokerSession.getUserStories();
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

	public Set<UserStory> getUserStories() {
		return userStories;
	}

	public void setUserStories(Set<UserStory> userStories) {
		this.userStories = userStories;
	}
    
    
}
