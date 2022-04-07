package com.example.challenge.service;



import com.example.challenge.dto.ChallengeListDto;
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
    private final ChallengeService challengeService;

    @Transactional
    @Override
    public ChallengeList addChallengeList(ChallengeListDto challengeListDto) {
        log.info("add challenge");
        return challengeListRepository.save(ChallengeList.builder()
                .id(null)
                .challenge(challengeService.getChallengeById(challengeListDto.getChallengeId()).get())
                .challengeTitle(challengeListDto.getChallengeTitle())
                .startDay(challengeListDto.getStartDay())
                .endDay(challengeListDto.getEndDay())
                .weekCount(challengeListDto.getWeekCount())
                .period(challengeListDto.getPeriod())
                .totalCount(challengeListDto.getTotalCount())
                .count(challengeListDto.getCount())
                .doing(challengeListDto.getDoing())
                .state(challengeListDto.getState())
                .result(challengeListDto.getResult())
                .build());
    }

    @Transactional
    @Override
    public void editChallengeList(Long id, ChallengeListDto challengeListDto) {

        log.info("edit challengeList. {}", challengeListRepository.findById(challengeListDto.getId()));
        if(challengeListRepository.findById(id).isPresent()){ //id 값이 있는지 먼저 확인하기
            ChallengeList editedChallengeList = ChallengeList.builder()
                    .id(challengeListDto.getId())
                    .challenge(challengeService.getChallengeById(challengeListDto.getChallengeId()).get())
                    .challengeTitle(challengeListDto.getChallengeTitle())
                    .startDay(challengeListDto.getStartDay())
                    .endDay(challengeListDto.getEndDay())
                    .weekCount(challengeListDto.getWeekCount())
                    .period(challengeListDto.getPeriod())
                    .totalCount(challengeListDto.getTotalCount())
                    .count(challengeListDto.getCount())
                    .doing(challengeListDto.getDoing())
                    .state(challengeListDto.getState())
                    .result(challengeListDto.getResult())
                    .build();
            challengeListRepository.save(editedChallengeList);
        } else{
            log.error("edit challengeList error.");
        }
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
        if (challengeListRepository.findById(id).isPresent()){
            log.info("delete challengeList by id {}.", id);
            challengeListRepository.deleteById(id);
        } else {
            log.error("delete challengeList error.");
        }
    }
}
