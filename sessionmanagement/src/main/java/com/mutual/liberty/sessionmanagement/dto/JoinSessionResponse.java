package com.mutual.liberty.sessionmanagement.dto;

import java.util.Set;
import java.util.stream.Collectors;

import com.mutual.liberty.sessionmanagement.entity.Member;
import com.mutual.liberty.sessionmanagement.entity.PokerSession;

public class JoinSessionResponse {

	private PokerSession session;

    private Set<Member> members;

	public JoinSessionResponse(PokerSession pokerSession) {
		this.session = pokerSession;
		this.members = pokerSession.getMembers();
	}

	public PokerSession getSession() {
		return session;
	}


	public void setSession(PokerSession session) {
		this.session = session;
	}



	public Set<Member> getMembers() {
		return members;
	}

	public void setMembers(Set<Member> members) {
		this.members = members;
	}
    
}
