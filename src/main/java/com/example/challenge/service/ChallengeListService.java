package com.example.challenge.service;

import com.example.challenge.dto.ChallengeListDto;
import com.example.challenge.model.ChallengeList;

import java.util.List;
import java.util.Optional;

public interface ChallengeListService {
    ChallengeList addChallengeList(ChallengeListDto challengeListDto);
    void editChallengeList(Long id, ChallengeListDto challengeListDto);
    List<ChallengeList> getAllChallengeList();
    Optional<ChallengeList> getChallengeListById(Long id);
    void delChallengeList(Long id);
}
