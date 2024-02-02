package com.mutual.liberty.sessionmanagement.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mutual.liberty.sessionmanagement.entity.Vote;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long>{
	

}
