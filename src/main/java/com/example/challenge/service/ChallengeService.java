package com.example.challenge.service;

import com.example.challenge.dto.ChallengeDto;
import com.example.challenge.model.Challenge;

import java.util.List;
import java.util.Optional;

public interface ChallengeService {

    void addChallenge(ChallengeDto challengeDto);
    void editChallenge(Long id, ChallengeDto challengeDto);
    List<Challenge> getAllChallenge();
    Optional<Challenge> getChallengeById(Long id);
    void delChallenge(Long id);
}
