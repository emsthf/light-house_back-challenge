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
    private ChallengeListService challengeListService;

    //challengeListService랑 연결하면 터짐... 확인해볼것
    //뭐지?? 왜 final로 challengeListService랑 연결하면 루프가 뜨지???

    @Transactional
    @Override
    public void addChallenge(ChallengeDto challengeDto) {
        log.info("add challenge");
        challengeRepository.save(Challenge.builder()
                .id(null)
                .challengeTitle(challengeDto.getChallengeTitle())
                .challengeDesc(challengeDto.getChallengeDesc())
                .startDay(challengeDto.getStartDay())
                .endDay(challengeDto.getEndDay())
                .period(challengeDto.getPeriod())
                .weekCount(challengeDto.getWeekCount())
                .totalCount(challengeDto.getTotalCount())
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

//    @Override
//    public List<ChallengeDto> getAllChallenges() {
//        return ;
//    }

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
