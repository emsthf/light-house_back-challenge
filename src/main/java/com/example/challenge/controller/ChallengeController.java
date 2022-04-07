package com.example.challenge.controller;



import com.example.challenge.dto.ChallengeDto;
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
    public void add(@RequestBody ChallengeDto challengeDto){challengeService.addChallenge(challengeDto);}

    @PutMapping("/challenge")
    public void edit(@PathVariable Long id, @RequestBody ChallengeDto challengeDto){challengeService.editChallenge(id, challengeDto);}

    @GetMapping("/challenge")
    public List<Challenge> getAll(){return challengeService.getAllChallenge();}

    @GetMapping("/challenge/{id}")
    public Optional<Challenge> getChallengeById(@PathVariable Long id) {return challengeService.getChallengeById(id);}

    @DeleteMapping("/challenge/{id}")
    public void del(@PathVariable("id")Long id){challengeService.delChallenge(id);}
}
