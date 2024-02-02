package com.mutual.liberty.sessionmanagement.entity;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //@Column(unique = true)
    private String nickname;

	/*
	 * @ManyToOne
	 * 
	 * @JoinColumn(name = "session_id") private PokerSession session;
	 */
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Vote> emittedVotes;
    
    
    public Member() {
		
	}
    
	/*
	 * public PokerSession getSession() { return session; }
	 * 
	 * public void setSession(PokerSession session) { this.session = session; }
	 */

	public Set<Vote> getEmittedVotes() {
		return emittedVotes;
	}

	public void setEmittedVotes(Set<Vote> emittedVotes) {
		this.emittedVotes = emittedVotes;
	}

	public Member(String nickname) {
		this.nickname = nickname;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

    
    
}
