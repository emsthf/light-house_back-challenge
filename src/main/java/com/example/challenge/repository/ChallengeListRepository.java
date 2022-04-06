package com.example.challenge.repository;

import com.example.challenge.model.ChallengeList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChallengeListRepository extends JpaRepository<ChallengeList, Long> {
    List<ChallengeList> findByUserId(String userId);
}

