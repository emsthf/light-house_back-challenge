package com.example.challenge.service;

import com.example.challenge.dto.UserChallengeDto;
import com.example.challenge.model.UserChallenge;

import java.util.List;
import java.util.Optional;

public interface UserChallengeService {
    UserChallenge addChallengeList(UserChallengeDto challengeListDto);
    void editChallengeList(Long id, UserChallengeDto challengeListDto);
    List<UserChallenge> getAllChallengeList();
    Optional<UserChallenge> getChallengeListById(Long id);
    void delChallengeList(Long id);
    Long countByChallengeId(Long challengeId);
}
