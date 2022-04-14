package com.example.challenge.controller;

import com.example.challenge.dto.UserChallengeDto;
import com.example.challenge.model.UserChallenge;
import com.example.challenge.service.UserChallengeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class UserChallengeController {

    private final UserChallengeService challengeListService;

    @PostMapping("/userChallenge")
    public void add(@RequestBody UserChallengeDto challengeListDto){challengeListService.addChallengeList(challengeListDto);}

    @PutMapping("/userChallenge")
    public void edit(@PathVariable Long id, @RequestBody UserChallengeDto challengeListDto){challengeListService.editChallengeList(id, challengeListDto);}

    @GetMapping("/userChallenge")
    public List<UserChallenge> getAll(){return challengeListService.getAllChallengeList();}

    @GetMapping("/userChallenge/{id}")
    public Optional<UserChallenge> getChallengeById(@PathVariable Long id){return challengeListService.getChallengeListById(id);}

    @DeleteMapping("/userChallenge/{id}")
    public void del(@PathVariable("id")Long id) {
        challengeListService.delChallengeList(id);
    }
}

