package com.example.challenge.controller;

import com.example.challenge.dto.UserChallengeDto;
import com.example.challenge.model.UserChallenge;
import com.example.challenge.service.BadgeClient;
import com.example.challenge.service.UserChallengeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ChallengeListController {

    private final UserChallengeService userChallengeService;
    private final BadgeClient badgeClient;

    @PostMapping("/challengeList")
    public void add(@RequestBody UserChallengeDto userChallengeDto){userChallengeService.addChallengeList(userChallengeDto);}

    @PutMapping("/challengeList")
    public void edit(@PathVariable Long id, @RequestBody UserChallengeDto userChallengeDto){userChallengeService.editChallengeList(id, userChallengeDto);}

    @GetMapping("/challengeList")
    public List<UserChallenge> getAll(){return userChallengeService.getAllChallengeList();}

    @GetMapping("/challengeList/{id}")
    public Optional<UserChallenge> getChallengeById(@PathVariable Long id){return userChallengeService.getChallengeListById(id);}

    @DeleteMapping("/challengList/{id}")
    public void del(@PathVariable("id")Long id) {
        userChallengeService.delChallengeList(id);
    }

    @GetMapping("/challenge/badge/{id}")
    public Badge findBadgeById(@PathVariable Long id) {
        return badgeClient.getBadgeById(id);
    }
}

