package com.example.challenge.challengeImg;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ChallengeImgRepository extends JpaRepository<ChallengeImg, Long> {
    List<ChallengeImg> findAll();
}
