package com.mutual.liberty.sessionmanagement.dto;

/**
 * 
 * @author Biranchi
 * 
 */
public class PokerSessionResponse {

	String registerMemberToSessionLink;
	
	String addUserStoryLink;
	
	Long sessionId;

	
	public String getRegisterMemberToSessionLink() {
		return registerMemberToSessionLink;
	}

	public void setRegisterMemberToSessionLink(String registerMemberToSessionLink) {
		this.registerMemberToSessionLink = registerMemberToSessionLink;
	}

	public String getAddUserStoryLink() {
		return addUserStoryLink;
	}

	public void setAddUserStoryLink(String addUserStoryURL) {
		this.addUserStoryLink = addUserStoryURL;
	}

	public Long getSessionId() {
		return sessionId;
	}

	public void setSessionId(Long sessionId) {
		this.sessionId = sessionId;
	}
	
	
	
}
