package com.mutual.liberty.sessionmanagement.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mutual.liberty.sessionmanagement.entity.PokerSession;

@Repository
public interface SessionRepository extends JpaRepository<PokerSession, Long> {
}
