package com.example.challenge.service;


import com.example.challenge.model.ChallengeList;
import com.example.challenge.repository.ChallengeListRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Slf4j
@RequiredArgsConstructor
@Service
public class ChallengeListServiceImpl implements ChallengeListService {

    private final ChallengeListRepository challengeListRepository;

    @Transactional
    @Override
    public ChallengeList addChallengeList(ChallengeList challengeList) {
        log.info("add challenge");
        return challengeListRepository.save(challengeList);
    }

    @Transactional
    @Override
    public ChallengeList editChallengeList(ChallengeList challengeList) {
//        log.info("edit challengeList. {}", challengeListRepository.findById(challengeList.getId(getChallengeListById(id).get());
        ChallengeList editedChallengeList = new ChallengeList();
        editedChallengeList = ChallengeList.builder()
                .id(challengeList.getId())
                .userId(challengeList.getUserId())
                .challengeId(challengeList.getChallengeId())
                .challengeTitle(challengeList.getChallengeTitle())
                .startDay(challengeList.getStartDay())
                .endDay(challengeList.getEndDay())
                .weekCount(challengeList.getWeekCount())
                .period(challengeList.getPeriod())
                .totalCount(challengeList.getTotalCount())
                .count(challengeList.getCount())
                .doing(challengeList.getDoing())
                .state(challengeList.getState())
                .result(challengeList.getResult())
                .build();
        challengeListRepository.save(challengeList);
        return editedChallengeList;
    }

    @Transactional
    @Override
    public List<ChallengeList> getAllChallengeList() {
        log.info("get all challengeList");
        return challengeListRepository.findAll();
    }

    @Transactional
    @Override
    public Optional<ChallengeList> getChallengeListById(Long id) {
        log.info("get challengeList by challengeList id {}.", id);
        return Optional.ofNullable(challengeListRepository.findById(id).get());
    }

    @Transactional
    @Override
    public void delChallengeList(Long id) {
        log.info("delete challengeList by id {}.", id);
        challengeListRepository.deleteById(id);
    }
}
