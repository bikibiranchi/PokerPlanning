package com.mutual.liberty.sessionmanagement.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mutual.liberty.sessionmanagement.entity.UserStory;

@Repository
public interface UserStoryRepository extends JpaRepository<UserStory, Long>{

}
