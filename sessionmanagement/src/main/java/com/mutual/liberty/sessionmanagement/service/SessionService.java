package com.mutual.liberty.sessionmanagement.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mutual.liberty.sessionmanagement.dto.JoinSessionResponse;
import com.mutual.liberty.sessionmanagement.entity.Member;
import com.mutual.liberty.sessionmanagement.entity.PokerSession;
import com.mutual.liberty.sessionmanagement.entity.UserStory;
import com.mutual.liberty.sessionmanagement.repo.MemberRepository;
import com.mutual.liberty.sessionmanagement.repo.SessionRepository;

@Service
public class SessionService {

	@Autowired
    private SessionRepository sessionRepository;
    
    @Autowired
    private MemberRepository memberRepository;

	/*
	 * @Autowired public SessionService(SessionRepository sessionRepository) {
	 * this.sessionRepository = sessionRepository; }
	 */

    public PokerSession createSession(PokerSession pokerSession) {
        PokerSession session = sessionRepository.save(pokerSession);
        return session;
    }

    public PokerSession getSessionDetails(Long sessionId) {
        return sessionRepository.findById(sessionId).orElse(null);
    }

    public List<PokerSession> getAllSessions() {
        return sessionRepository.findAll();
    }

    public void deleteSession(Long sessionId) {
        sessionRepository.deleteById(sessionId);
    }


	public JoinSessionResponse joinPokerSession(Long sessionId, Member member) {
		PokerSession session = new PokerSession();

		try {
			session = sessionRepository.findById(sessionId)
					.orElseThrow(() -> new Exception("Poker session not found with id: " + sessionId));
			session.getMembers().add(member);
			sessionRepository.save(session);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		JoinSessionResponse jsr = new JoinSessionResponse(session);
		return jsr;
	}
	
	
	
	public Set<Member> getMembers(Long sessionId) {
		PokerSession session = sessionRepository.findById(sessionId).orElse(null);

		if (session != null) {
			return session.getMembers();
		}

		return null;
	}
	
	public boolean removeMember(Long sessionId, Long memberId) {
		PokerSession session = sessionRepository.findById(sessionId).orElse(null);

		if (session != null) {
			Set<Member> members = session.getMembers();

			boolean removed = members.removeIf(member -> member.getId().equals(memberId));
			if (removed)
				sessionRepository.save(session);
			return removed;
		}

		return false;

	}
	
	
	public PokerSession submitVote(Long sessionId, Long userStoryId, Member member) {

		PokerSession session = sessionRepository.findById(sessionId).orElse(null);

		if (session != null) {
			Optional<UserStory> optionalUserStory = session.getUserStories().stream()
					.filter(story -> story.getId().equals(userStoryId) && "VOTING".equals(story.getStatus()))
					.findFirst();

			if (optionalUserStory.isPresent()) {
				UserStory userStory = optionalUserStory.get();
				Optional<Member> findFirst = session.getMembers().stream().filter(m -> m.getNickname().equals(member.getNickname())).findFirst();
				if(findFirst.isPresent()) {
					//userStory.getEmittedVotes().add(findFirst.get().getNickname());
					return sessionRepository.save(session);
				}
			}
		}
		return null;
	}
	
	public Map<String, Object> getVotingStatus(Long sessionId, Long userStoryId) {
		PokerSession session = sessionRepository.findById(sessionId).orElse(null);
		Map<String, Object> votingStatus = new HashMap<>();
		if (session != null) {
			UserStory userStory = session.getUserStories().stream()
					.filter(story -> story.getId().equals(userStoryId) && "VOTING".equals(story.getStatus()))
					.findFirst().orElse(null);

			if (userStory != null) {
				votingStatus.put("emittedVotes", userStory.getEmittedVotes());
				votingStatus.put("totalEmittedVotes", userStory.getEmittedVotes().size());
			}
		}
		return votingStatus;
	}
}

