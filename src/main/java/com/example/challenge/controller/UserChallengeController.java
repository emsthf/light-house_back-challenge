package com.example.challenge.controller;

import com.example.challenge.dto.UserChallengeDto;
import com.example.challenge.model.UserChallenge;
import com.example.challenge.service.UserChallengeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class UserChallengeController {

    private final UserChallengeService userChallengeService;

    @PostMapping("/mychallenge")
    public String add(@RequestBody UserChallengeDto challengeListDto){
        if(userChallengeService.addChallengeList(challengeListDto)) {
            return "신청이 완료되었습니다.";
        } else {
            return "챌린지 신청이 취소되었습니다.";
        }
    }

    @PutMapping("/mychallenge/{id}")
    public void edit(@PathVariable Long id, @RequestBody UserChallengeDto challengeListDto){
        userChallengeService.editChallengeList(id, challengeListDto);
    }

    @GetMapping("/mychallenge")
    public List<UserChallenge> getAll(){return userChallengeService.getAllChallengeList();}

    @GetMapping("/mychallenge/{id}")
    public Optional<UserChallenge> getChallengeById(@PathVariable Long id){return userChallengeService.getChallengeListById(id);}

    @DeleteMapping("/mychallenge/{id}")
    public void del(@PathVariable("id")Long id) {
        userChallengeService.delChallengeList(id);
    }

    @GetMapping("/mychallenge/all/{challengeId}")
    public Long countByChallengeId(@PathVariable Long challengeId) {
        return userChallengeService.countByChallengeId(challengeId);
    }

    @GetMapping("/mychallenge/list/{userId}")
    public List<UserChallenge> findAllByUserId(@PathVariable Long userId) {
        return userChallengeService.findByUserIdOrderByIdDesc(userId);
    }
}

