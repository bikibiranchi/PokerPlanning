package com.mutual.liberty.sessionmanagement.ctrl;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import com.mutual.liberty.sessionmanagement.dto.AddUserStoryResponse;
import com.mutual.liberty.sessionmanagement.dto.JoinSessionResponse;
import com.mutual.liberty.sessionmanagement.dto.PokerSessionResponse;
import com.mutual.liberty.sessionmanagement.entity.Member;
import com.mutual.liberty.sessionmanagement.entity.PokerSession;
import com.mutual.liberty.sessionmanagement.entity.UserStory;
import com.mutual.liberty.sessionmanagement.service.SessionService;
import com.mutual.liberty.sessionmanagement.util.CONSTANTS;

/**
 * 
 * @author Biranchi 
 * 
 */
@RestController
@RequestMapping("/session")
public class SessionController {
	
	@Autowired
	private SessionService sessionService;
	
	@Value("${server.port}")
	int port;

	/**
	 * To create a poker session
	 * 
	 * @param pokerSession
	 * @return
	 */
	@PostMapping("/create")
	public ResponseEntity<PokerSessionResponse> createPokerSession(@RequestBody PokerSession pokerSession) {
		PokerSession session = sessionService.createSession(pokerSession);

		UriComponentsBuilder registerMemberToSessionLink = MvcUriComponentsBuilder
				.fromMethodName(SessionController.class, "joinPokerSession", session.getSessionId(), null)
				.scheme(CONSTANTS.HTTP).host(CONSTANTS.HOST).port(port);

		
		UriComponentsBuilder userStoryLink = MvcUriComponentsBuilder
				.fromMethodName(UserStoryController.class, "addUserStoryToSession", session.getSessionId(), null)
				.scheme(CONSTANTS.HTTP).host(CONSTANTS.HOST).port(port);

		PokerSessionResponse response = new PokerSessionResponse();
		response.setAddUserStoryLink(userStoryLink.toUriString());
		response.setSessionId(session.getSessionId());
		response.setRegisterMemberToSessionLink(registerMemberToSessionLink.toUriString());
		return ResponseEntity.ok(response);
	}
	
	/**
	 * To register a member into session
	 * 
	 * @param sessionId
	 * @param member
	 * @return
	 */
	@PostMapping("/{sessionId}/member")
	public ResponseEntity<JoinSessionResponse> joinPokerSession(@PathVariable Long sessionId, @RequestBody Member member) {
		JoinSessionResponse response = sessionService.joinPokerSession(sessionId, member);
		return ResponseEntity.ok(response);
	}
	
	

	/**
	 * To return a session by ID
	 * 
	 * @param sessionId
	 * @return
	 */
	@GetMapping("/{sessionId}")
	public PokerSession getSessionById(@PathVariable Long sessionId) {
		return sessionService.getSessionDetails(sessionId);
	}

	/**
	 * To return all session details
	 * 
	 * @return
	 */
	@GetMapping("all-session")
	public List<PokerSession> getAllSessions() {
		return sessionService.getAllSessions();
	}

	
	
	/**
	 * To get all members by sessionId
	 * 
	 * @param sessionId
	 * @return
	 */
	@GetMapping("/{sessionId}/members")
	public Set<Member> getMembers(@PathVariable Long sessionId) {
		return sessionService.getMembers(sessionId);
	}
	
	/**
	 * Delete session by Session Id
	 * 
	 * @param sessionId
	 * @return
	 */
	@DeleteMapping("/{sessionId}")
	public ResponseEntity<String> deleteSession(@PathVariable Long sessionId) {
		sessionService.deleteSession(sessionId);
		return ResponseEntity.ok("Session deleted successfully");
	}
	
	/**
	 * To remove member from session
	 * 
	 * @param sessionId
	 * @param memberId
	 * @return
	 */
	@DeleteMapping("/{sessionId}/member/{memberId}")
	public ResponseEntity<String> removeMember(@PathVariable Long sessionId, @PathVariable Long memberId) {
		boolean memberRemoved = sessionService.removeMember(sessionId, memberId);
		if (memberRemoved) {
			return ResponseEntity.ok("Member deleted successfully");
		}else {
			return ResponseEntity.ok("Unable to delete member, Please check again!!!");
		}
	}
	
	
}
