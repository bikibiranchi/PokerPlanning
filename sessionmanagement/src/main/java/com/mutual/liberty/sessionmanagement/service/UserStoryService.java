package com.mutual.liberty.sessionmanagement.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mutual.liberty.sessionmanagement.entity.PokerSession;
import com.mutual.liberty.sessionmanagement.entity.UserStory;
import com.mutual.liberty.sessionmanagement.repo.SessionRepository;
import com.mutual.liberty.sessionmanagement.repo.UserStoryRepository;
import com.mutual.liberty.sessionmanagement.util.VOTINGSTATUS;

@Service
public class UserStoryService {

	@Autowired
	private UserStoryRepository userStoryRepository;
	
	@Autowired
	private SessionRepository sessionRepository;
	
	public UserStory addUserStoryToSession(Long sessionId, UserStory story) {
		PokerSession session = sessionRepository.findById(sessionId).orElse(null);

		story.setStatus(VOTINGSTATUS.PENDING.toString());
		
		session.getUserStories().add(story);
		PokerSession save = sessionRepository.save(session);
		
		return story;
	}
	
	public Set<UserStory> getUserStories(Long sessionId) {
		PokerSession session = sessionRepository.findById(sessionId).orElse(null);

		if (session != null) {
			return session.getUserStories();
		}

		return null;
	}
	public UserStory updateStoryStatus( Long sessionId, Long userStoryId) {
		
		PokerSession session = sessionRepository.findById(sessionId).orElse(null);
		if (session != null) {
			UserStory userStory = session.getUserStories().stream().filter(story -> story.getId().equals(userStoryId))
					.findFirst().orElse(null);
			if (userStory != null && (VOTINGSTATUS.PENDING.toString().equals(userStory.getStatus())
					|| VOTINGSTATUS.VOTED.toString().equals(userStory.getStatus()))) {
				userStory.setStatus(VOTINGSTATUS.VOTING.toString());
				return userStoryRepository.save(userStory);
			}
		}
		return null;
	
	}
	
	public boolean removeUserStory(Long sessionId,Long userStoryId) {
		PokerSession session = sessionRepository.findById(sessionId).orElse(null);
		if (session != null) {
			  Set<UserStory> userStories = session.getUserStories();
			  boolean removed = userStories.removeIf(userStory -> userStory.getId().equals(userStoryId));
			  if (removed) {
				  sessionRepository.save(session); 
			  }
			  return removed;
		}
		return false;
	}
	
	

}
