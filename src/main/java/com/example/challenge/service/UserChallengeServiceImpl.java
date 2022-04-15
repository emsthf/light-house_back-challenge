package com.example.challenge.service;

import com.example.challenge.dto.UserChallengeDto;
import com.example.challenge.model.Challenge;
import com.example.challenge.model.Doing;
import com.example.challenge.model.UserChallenge;
import com.example.challenge.repository.UserChalllengeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Slf4j
@RequiredArgsConstructor
@Service
public class UserChallengeServiceImpl implements UserChallengeService {

    private final UserChalllengeRepository userChallengeRepository;
    private final ChallengeService challengeService;
    private final DoingService doingService;

    @Transactional
    @Override
    public UserChallenge addChallengeList(UserChallengeDto userChallengeDto) {
        log.info("add challenge");
        if(userChallengeRepository.findByChallengeIdAndUserId(
                userChallengeDto.getChallengeId(),
                userChallengeDto.getUserId()).isEmpty()) {
            return userChallengeRepository.save(UserChallenge.builder()
                    .userId(userChallengeDto.getUserId())
                    .challenge(challengeService.getChallengeById(userChallengeDto.getChallengeId()).get())
//                    .userChallengeCount(userChallengeDto.getUserChallengeCount())
//                    .userChallengeState(userChallengeDto.getUserChallengeState())
                    .build());
        } else {
            log.error("already exists.");
            return null;
        }
    }

    //총 실행 기간 중 현재 몇 주차인지 확인
    int checkWeek(Challenge challenge){
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

    //챌린지에서 카운트하고 doing에 추가
    UserChallenge checkDoing(UserChallengeDto userChallengeDto) {
        log.info("checkDoing by userChallengeId : {}", userChallengeDto.getId());
        UserChallenge userChallenge = userChallengeRepository.findById(userChallengeDto.getId()).get();
        Challenge challenge = challengeService.getChallengeById(userChallengeDto.getChallengeId()).get();

        int thisWeek = checkWeek(challenge);

        if (userChallenge.getUserChallengeState() == 0 && userChallenge.getUserChallengeCount() < challenge.getTotalCount()){
            //일주일동안 실천하기로 한 횟수만큼만 카운트
            if (doingService.findAllByWeekAndUserChallengeId(
                    thisWeek, userChallengeDto.getChallengeId()).size() < challenge.getWeekCount()) {
                //하루에 한번만 목표 실천 인증
                if (doingService.findByChallengeIdAndCheckDate(userChallenge.getId(), LocalDate.now()) == null){
                    log.info("checkDoing");
                    userChallenge.setUserChallengeCount(userChallengeDto.getUserChallengeCount());
                    // 포스트맨 테스트 용으로 사용
//                    challenge.setChallengeCount(challenge.getChallengeCount() + challengeCheckDoingDto.getChallengeCount());
                    doingService.addDoing(
                            new Doing(userChallenge, LocalDate.now(), thisWeek,userChallengeDto.getPostId())
                    );
                }
            }
        }else {
            log.info("check doing error");
        }
        return userChallengeRepository.save(userChallenge);
    }

    @Transactional
    @Override
    public void editChallengeList(Long id, UserChallengeDto userChallengeDto) {
        log.info("edit user challenge count. user challenge id = {}", userChallengeRepository.findById(userChallengeDto.getId()));
        UserChallenge userChallenge = checkDoing(userChallengeDto);
        userChallengeRepository.save(userChallenge);
    }

    @Transactional
    @Override
    public List<UserChallenge> getAllChallengeList() {
        log.info("get all challengeList");
        return userChallengeRepository.findAll();
    }

    @Transactional
    @Override
    public Optional<UserChallenge> getChallengeListById(Long id) {
        log.info("get challengeList by challengeList id {}.", id);
        return Optional.ofNullable(userChallengeRepository.findById(id).get());
    }

    @Transactional
    @Override
    public void delChallengeList(Long id) {
        if (userChallengeRepository.findById(id).isPresent()){
            log.info("delete challengeList by id {}.", id);
            userChallengeRepository.deleteById(id);
        } else {
            log.error("delete challengeList error.");
        }
    }

    @Override
    public Long countByChallengeId(Long challengeId) { // 챌린지를 신청한 총 인원 수
        return userChallengeRepository.countByChallengeId(challengeId);
    }

}
