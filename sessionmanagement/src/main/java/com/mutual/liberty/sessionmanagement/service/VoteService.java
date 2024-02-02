package com.mutual.liberty.sessionmanagement.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mutual.liberty.sessionmanagement.entity.Member;
import com.mutual.liberty.sessionmanagement.entity.PokerSession;
import com.mutual.liberty.sessionmanagement.entity.UserStory;
import com.mutual.liberty.sessionmanagement.entity.Vote;
import com.mutual.liberty.sessionmanagement.repo.SessionRepository;
import com.mutual.liberty.sessionmanagement.repo.VoteRepository;
import com.mutual.liberty.sessionmanagement.util.VOTINGSTATUS;

@Service
public class VoteService {

	@Autowired
	private SessionRepository sessionRepository;
	
	@Autowired
	private VoteRepository voteRepository;
	
	public Vote submitVote(Long sessionId, Long userStoryId, Member member) {
		Vote vote = new Vote();
		PokerSession session = sessionRepository.findById(sessionId).orElse(null);

		if (session != null) {
			Optional<UserStory> optionalUserStory = session.getUserStories().stream()
					.filter(story -> story.getId().equals(userStoryId) && "VOTING".equals(story.getStatus()))
					.findFirst();

			if (optionalUserStory.isPresent()) {
				UserStory userStory = optionalUserStory.get();
				vote.setUserStory(userStoryId);
				Optional<Member> optionalMember = session.getMembers().stream()
						.filter(m -> m.getNickname().equals(member.getNickname())).findFirst();
				if (optionalMember.isPresent()) {
					Member member2 = optionalMember.get();
					vote.setMember(member2.getId());
					vote.setVoteValue("VOTED");
					//Vote save = voteRepository.save(vote);
					member2.getEmittedVotes().add(vote);
					userStory.getEmittedVotes().add(vote);
					session.getMembers().add(member2);
					session.getUserStories().add(userStory);
					sessionRepository.save(session);
					return vote;
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
					.filter(story -> story.getId().equals(userStoryId) && "VOTED".equals(story.getStatus()))
					.findFirst().orElse(null);

			if (userStory != null) {
				Set<Vote> emittedVotes = userStory.getEmittedVotes();
				votingStatus.put("Vote count : ", emittedVotes.size());
				votingStatus.put("Emitted Votes : ", emittedVotes);
				
			}
		}
		return votingStatus;
	}

	public boolean endVote(Long sessionId, Long userStoryId) {
		PokerSession session = sessionRepository.findById(sessionId).orElse(null);
		UserStory userStory;
		if (session != null) {
			Optional<UserStory> optionalUserStory = session.getUserStories().stream()
					.filter(story -> story.getId().equals(userStoryId) && "VOTING".equals(story.getStatus()))
					.findFirst();
			
			if(optionalUserStory.isPresent()) {
				userStory = optionalUserStory.get();
				userStory.setStatus(VOTINGSTATUS.VOTED.toString());
				sessionRepository.save(session);
				return true;
			}
		}
		return false;
	}
}
