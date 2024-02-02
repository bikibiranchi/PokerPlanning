package com.mutual.liberty.sessionmanagement.ctrl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mutual.liberty.sessionmanagement.entity.Member;
import com.mutual.liberty.sessionmanagement.entity.Vote;
import com.mutual.liberty.sessionmanagement.service.VoteService;

@RestController
public class VoteController {

	@Autowired
	private VoteService voteService;

	/**
	 * To emit vote to a story
	 * 
	 * @param sessionId
	 * @param userStoryId
	 * @param member
	 * @return
	 */
	@PostMapping("/session/{sessionId}/userstory/{userStoryId}/vote")
    public ResponseEntity<String> emitVote(@PathVariable Long sessionId,@PathVariable Long userStoryId,@RequestBody Member member){
        Vote submitVote = voteService.submitVote(sessionId, userStoryId, member);
        if(submitVote != null) {
        	return ResponseEntity.ok("Vote submitted successfully");
		}else {
			return ResponseEntity.ok("Something went wrong, Please check again!!!");
		}
    }
	
	/**
	 * To get the vote status of a story
	 * 
	 * @param sessionId
	 * @param userStoryId
	 * @return
	 */
	@GetMapping("/session/{sessionId}/userstory/{userStoryId}/votestatus")
	public ResponseEntity<Map<String, Object>> getVotingStatus(@PathVariable Long sessionId, @PathVariable Long userStoryId) {
		Map<String, Object> votingStatus = voteService.getVotingStatus(sessionId, userStoryId);
        return ResponseEntity.ok(votingStatus);
	}
	
	
	@PutMapping("/session/{sessionId}/userstory/{userStoryId}/endvote")
    public ResponseEntity<String> endVote(@PathVariable Long sessionId,@PathVariable Long userStoryId){
        boolean endVote = voteService.endVote(sessionId, userStoryId);
        if(endVote) {
        	return ResponseEntity.ok("Vote Ended successfully");
		}else {
			return ResponseEntity.ok("Something went wrong, Please check again!!!");
		}
    }
}
