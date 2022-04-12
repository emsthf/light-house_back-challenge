package com.example.challenge.service;

import com.example.challenge.dto.ChallengeCheckDoingDto;
import com.example.challenge.dto.ChallengeDto;
import com.example.challenge.model.Challenge;
import com.example.challenge.model.Doing;
import com.example.challenge.repository.ChallengeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    private ChallengeRepository challengeRepository;
    private DoingService doingService;

    @Transactional
    @Override //챌린지 추가(관리자)
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
                .challengeImg(challengeDto.getChallengeImg())
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
    @Override //챌린지 수정(관리자)
    public void editChallenge(Long id, ChallengeDto challengeDto) {
//        log.info("edit challenge. {}", challengeRepository.findById(challengeDto.getChallengeListId()).get());
        if (challengeRepository.findById(id).isPresent()) {
            Challenge editedChallenge = Challenge
                    .builder()
                    .id(challengeDto.getId())
                    .challengeTitle(challengeDto.getChallengeTitle())
                    .challengeDesc(challengeDto.getChallengeDesc())
                    .challengeImg(challengeDto.getChallengeImg())
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

    @Transactional
    @Override //getAll 챌린지
    public List<Challenge> getAllChallenge() {
        log.info("get all challenge");
        return challengeRepository.findAll();
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
        challengeRepository.deleteById(id);
    }

//    @Transactional
//    @Override
//    public List<Challenge> get3DoingChallenge(int state) {
//        log.info("대시보드에 보여줄 최근 진행중인 챌린지 3개");
//        return challengeRepository.findTop3ByStateOrderByIdDesc(state);
//    }

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

    //총 실행 기간 중 현재 몇 주차인지 확인
    @Transactional
    public int checkWeek(Challenge challenge){
        int now = LocalDate.now().getDayOfYear();
        int start = challenge.getStartDay().getDayOfYear();
        int week = 1;

        for(int i = 1; i <= now - start; i++) {
            if(i % 7 == 0) {
                week++;
            }
        }
        log.info("week : {}", week);
        return week;
    }

    //challenge count and add doing
    //챌린지에서 카운트하고 doing에 추가
    @Transactional
    public Challenge checkDoing(ChallengeCheckDoingDto challengeCheckDoingDto) {
        log.info("checkDoing by challengeId : {}", challengeCheckDoingDto.getId());
        Challenge challenge = challengeRepository.findById(challengeCheckDoingDto.getId()).get();

        int thisWeek = checkWeek(challenge);

        if (challenge.getChallengeState() == 0 && challenge.getChallengeCount() < challenge.getTotalCount()){

            //일주일동안 실천하기로 한 횟수만큼 카운트!
            if (doingService.findAllByWeek(thisWeek).size() < challenge.getTotalCount()) {

                //하루에 한번만 목표 실천 인증
                if (doingService.findByChallengeIdAndCheckDate(challenge.getId(), LocalDate.now()) == null){
                    log.info("checkDoing");
                    challenge.setChallengeCount(challengeCheckDoingDto.getChallengeCount()); //front에서 count +1 put
                    ////포스트맨 테스트 용으로 사용
//                    challenge.setChallengeCount(challenge.getChallengeCount() + challengeCheckDoingDto.getChallengeCount()); //포스트맨 테스트 용으로 사용
                    doingService.addDoing(Doing.builder()
                            .challenge(challenge)
                            .checkDate(LocalDate.now())
                            .week(thisWeek)
                            .postId(challengeCheckDoingDto.getPostId())
                            .build());}}
        }else {
            log.info("check doing error");
        }
        return challengeRepository.save(challenge);
    }
}
