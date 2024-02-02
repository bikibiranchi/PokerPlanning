package com.mutual.liberty.sessionmanagement.ctrl;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mutual.liberty.sessionmanagement.dto.AddUserStoryResponse;
import com.mutual.liberty.sessionmanagement.entity.PokerSession;
import com.mutual.liberty.sessionmanagement.entity.UserStory;
import com.mutual.liberty.sessionmanagement.service.UserStoryService;

@RestController
public class UserStoryController {

	@Autowired
	private UserStoryService userStoryService;
	
	/**
	 * To add a user story to session
	 * 
	 * @param sessionId
	 * @param story
	 * @return
	 */
	@PostMapping("/session/{sessionId}/userstory")
	public ResponseEntity<UserStory> addUserStoryToSession(@PathVariable Long sessionId,
			@RequestBody UserStory story) {
		UserStory userstory = userStoryService.addUserStoryToSession(sessionId, story);
		return ResponseEntity.ok(userstory);
	}
	
	/**
	 * To get user stories by session ID
	 * 
	 * @param sessionId
	 * @return
	 */
	@GetMapping("/session/{sessionId}/userstories")
	public ResponseEntity<Set<UserStory>> getUserStories(@PathVariable Long sessionId) {
		 Set<UserStory> userStories = userStoryService.getUserStories(sessionId);
		 return ResponseEntity.ok(userStories);
	}
	
	/**
	 * 
	 * @param sessionId
	 * @param storyId
	 * @return
	 */
	@DeleteMapping("/session/{sessionId}/userstory/{storyId}")
	public ResponseEntity<String> removeUserStory(@PathVariable Long sessionId, @PathVariable Long storyId) {
		boolean userStoryRemoved = userStoryService.removeUserStory(sessionId, storyId);
		if (userStoryRemoved) {
			return ResponseEntity.ok("UserStory deleted successfully");
		}else {
			return ResponseEntity.ok("Unable to delete user story, Please check again!!!");
		}
	}
	
	/**
	 * To update the story status
	 * 
	 * @param sessionId
	 * @param userStoryId
	 * @return
	 */
	@PutMapping("/session/{sessionId}/userstory/{userStoryId}")
	public ResponseEntity<String> updateStoryStatus(@PathVariable Long sessionId, @PathVariable Long userStoryId) {
		UserStory userStory = userStoryService.updateStoryStatus(sessionId, userStoryId);
		if (userStory != null) {
			return ResponseEntity.ok("Voting started for the user story : " + userStory.getUserStoryId());
		} else {
			return ResponseEntity.ok("Something went wrong, Please try again!!!");
		}
	}
	
}
