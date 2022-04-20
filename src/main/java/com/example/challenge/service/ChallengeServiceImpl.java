package com.example.challenge.service;

import com.example.challenge.dto.ChallengeDto;
import com.example.challenge.model.Challenge;
import com.example.challenge.model.UserChallenge;
import com.example.challenge.repository.ChallengeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChallengeServiceImpl implements ChallengeService{

    private final ChallengeRepository challengeRepository;


    @Transactional
    @Override //챌린지 추가(관리자)
    public void addChallenge(ChallengeDto challengeDto) {
        log.info("add challenge");
        // 전체 기간의 주차별로 실행해야 할 count 수
        int totalWeekCount = (int)Math.floor(challengeDto.getPeriod() / 7) * challengeDto.getWeekCount();
        // 전체 기간에서 일주일 단위로 나누어 떨어지지 않는 나머지 일수
        int remainderDay = challengeDto.getPeriod() - (int)Math.floor(challengeDto.getPeriod() / 7) * 7;
        int totalCount = 0;

        if(challengeDto.getPeriod() % 7 > challengeDto.getWeekCount()) {
            totalCount = totalWeekCount + challengeDto.getWeekCount();
        } else if(challengeDto.getPeriod() % 7 == 0) {
            totalCount = totalWeekCount;
        } else {
            totalCount = totalWeekCount + remainderDay;
        }

        int state = 1;
        if(challengeDto.getStartDay().isEqual(LocalDate.now()) || challengeDto.getStartDay().isAfter(LocalDate.now())) {
            state = 0; // 챌린지 시작일이 현재 날짜와 같거나 그 이후인 경우에만 진행 중으로 표시
        }
//        log.info("totalCount : {}", totalCount);
        challengeRepository.save(Challenge.builder()
                .challengeTitle(challengeDto.getChallengeTitle())
                .challengeDesc(challengeDto.getChallengeDesc())
                .challengeImg(challengeDto.getChallengeImg())
                .startDay(challengeDto.getStartDay())
                .endDay(challengeDto.getEndDay())
                .period(challengeDto.getPeriod())
                .weekCount(challengeDto.getWeekCount())
                .totalCount(totalCount)
                .challengeState(state)
                .build());
    }

    @Transactional
    @Override //챌린지 수정(관리자)
    public void editChallenge(Long id, ChallengeDto challengeDto) {
//        log.info("edit challenge. {}", challengeRepository.findById(challengeDto.getChallengeListId()).get());
        if (challengeRepository.findById(id).isPresent()) {
            Challenge editedChallenge = Challenge
                    .builder()
                    .id(id)
                    .challengeTitle(challengeDto.getChallengeTitle())
                    .challengeDesc(challengeDto.getChallengeDesc())
                    .challengeImg(challengeDto.getChallengeImg())
                    .startDay(challengeDto.getStartDay())
                    .endDay(challengeDto.getEndDay())
                    .period(challengeDto.getPeriod())
                    .weekCount(challengeDto.getWeekCount())
                    .totalCount(challengeDto.getTotalCount())
                    .challengeState(challengeDto.getChallengeState())
                    .build();
            challengeRepository.save(editedChallenge);
        }else {
            log.error("edit challenge error.");
        }
    }

    @Transactional
    @Override //getAll 챌린지
    public List<Challenge> getAllChallenge() {
        log.info("get all challenge");
        // 최신순으로 정렬
        return challengeRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    @Transactional
    @Override //getById 챌린지
    public Optional<Challenge> getChallengeById(Long id) {
        log.info("get challenge by challenge id {}.", id);
        return Optional.ofNullable(challengeRepository.findById(id).get());
    }

    @Transactional
    @Override //챌린지 삭제
    public void delChallenge(Long id) {
        log.info("delete challenge by id {}.", id);
        if(challengeRepository.findById(id).isPresent()) {
            challengeRepository.deleteById(id);
        }
    }


    @Transactional
    @Scheduled(cron = "0 0 0 * * *") // 매일 0시에 실행
    public void scheduler(){
        List<Challenge> list = challengeRepository.findAll();
        LocalDate today = LocalDate.now();

        list.forEach(challenge -> {
            if (challenge.getEndDay().isBefore(today) && challenge.getChallengeState() == 0){
                challenge.setChallengeState(1); //endDay 확인하고 state 변경(종료되면 1)
            }
        });
    }

}
