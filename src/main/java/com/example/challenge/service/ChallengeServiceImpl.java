package com.example.challenge.service;

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
    public Challenge addChallenge(Challenge challenge) {
        log.info("add challenge");
        return challengeRepository.save(challenge);
    }

    @Transactional
    @Override
    public Challenge editChallenge(Challenge challenge) {
        log.info("edit challenge. {}", challengeRepository.findById(challenge.getId()).get());
        Challenge editedChallenge = new Challenge();
        editedChallenge= Challenge.builder()
                .id(challenge.getId())
                .challengeTitle(challenge.getChallengeTitle())
                .startDay(challenge.getStartDay())
                .endDay(challenge.getEndDay())
                .weekCount(challenge.getWeekCount())
                .totalCount(challenge.getTotalCount())
                .doing(challenge.getDoing())
                .challengeState(challenge.getChallengeState())
                .challengedesc(challenge.getChallengedesc())
                .build();
        challengeRepository.save(challenge);
        return editedChallenge;
    }

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
