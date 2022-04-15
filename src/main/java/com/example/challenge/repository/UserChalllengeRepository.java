package com.example.challenge.repository;

import com.example.challenge.model.UserChallenge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserChalllengeRepository extends JpaRepository<UserChallenge, Long> {
    Long countByChallengeId(Long challengeId);
}

