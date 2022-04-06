package com.example.challenge.controller;


import com.example.challenge.model.Challenge;
import com.example.challenge.service.ChallengeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ChallengeController {

    private final ChallengeService challengeService;

    @PostMapping("/challenge")
    public void add(@RequestBody Challenge challenge){challengeService.addChallenge(challenge);}

    @PutMapping("/challenge")
    public void edit(@RequestBody Challenge challenge){challengeService.editChallenge(challenge);}

    @GetMapping("/challenge")
    public List<Challenge> getAll(){return challengeService.getAllChallenge();}

    @GetMapping("/challenge/{id}")
    public Optional<Challenge> getChallengeById(@PathVariable Long id) {return challengeService.getChallengeById(id);}

    @DeleteMapping("/challenge/{id}")
    public void del(@PathVariable("id")Long id){challengeService.delChallenge(id);}
}
