package com.example.challenge.repository;

import com.example.challenge.model.UserChallenge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserChallengeRepository extends JpaRepository<UserChallenge, Long> {
    Long countByChallengeId(Long challengeId);
    Optional<UserChallenge> findByChallengeIdAndUserId(Long challengeId, Long userId);
    List<UserChallenge> findByUserIdOrderByIdDesc(Long userId);
}
