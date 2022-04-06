package com.example.challenge.service;


import com.example.challenge.model.Challenge;

import java.util.List;
import java.util.Optional;

public interface ChallengeService {
    Challenge addChallenge(Challenge challenge);
    Challenge editChallenge(Challenge challenge);
    List<Challenge> getAllChallenge();
    Optional<Challenge> getChallengeById(Long id);
    void delChallenge(Long id);
}
