package com.example.challenge.service;

import com.example.challenge.dto.ChallengeDto;
import com.example.challenge.model.Challenge;
import com.example.challenge.repository.ChallengeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChallengeServiceImpl implements ChallengeService{

    private final ChallengeRepository challengeRepository;

    @Transactional
    @Override
    public void addChallenge(ChallengeDto challengeDto) {
        log.info("add challenge");
        int totalCount = 0;
        if(challengeDto.getPeriod() % 7 >= challengeDto.getWeekCount()) {
            totalCount = (int)Math.floor(challengeDto.getPeriod() / 7) * challengeDto.getWeekCount() + challengeDto.getWeekCount();
        } else {
            totalCount = (int)Math.floor(challengeDto.getPeriod() / 7) * challengeDto.getWeekCount();
        }
//        log.info("totalCount : {}", totalCount);
        challengeRepository.save(Challenge.builder()
                .id(null)
                .challengeTitle(challengeDto.getChallengeTitle())
                .challengeDesc(challengeDto.getChallengeDesc())
                .startDay(challengeDto.getStartDay())
                .endDay(challengeDto.getEndDay())
                .period(challengeDto.getPeriod())
                .weekCount(challengeDto.getWeekCount())
                .totalCount(totalCount)
                .challengeCount(challengeDto.getChallengeCount())
                .challengeState(challengeDto.getChallengeState())
////                        .result(challengeDto.)
                .build());
    }

    @Transactional
    @Override
    public void editChallenge(Long id, ChallengeDto challengeDto) {
//        log.info("edit challenge. {}", challengeRepository.findById(challengeDto.getChallengeListId()).get());
        if (challengeRepository.findById(id).isPresent()) {
            Challenge editedChallenge = Challenge
                    .builder()
                    .id(challengeDto.getId())
                    .challengeTitle(challengeDto.getChallengeTitle())
                    .challengeDesc(challengeDto.getChallengeDesc())
                    .startDay(challengeDto.getStartDay())
                    .endDay(challengeDto.getEndDay())
                    .period(challengeDto.getPeriod())
                    .weekCount(challengeDto.getWeekCount())
                    .totalCount(challengeDto.getTotalCount())
                    .challengeCount(challengeDto.getChallengeCount())
                    .challengeState(challengeDto.getChallengeState())
//                    .result(challengeDto.getResult())
                    .build();
            challengeRepository.save(editedChallenge);
        }else {
            log.error("edit challenge error.");
        }
    }
    //
    @Transactional
    @Override
    public List<Challenge> getAllChallenge() {
        log.info("get all challenge");
        return challengeRepository.findAll();
    }

    @Transactional
    @Override
    public Optional<Challenge> getChallengeById(Long id) {
        log.info("get challenge by challenge id {}.", id);
        return Optional.ofNullable(challengeRepository.findById(id).get());
    }

    @Transactional
    @Override
    public void delChallenge(Long id) {
        log.info("delete challenge by id {}.", id);
        challengeRepository.deleteById(id);
    }
}
