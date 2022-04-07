package com.example.challenge.controller;

import com.example.challenge.dto.ChallengeListDto;
import com.example.challenge.model.ChallengeList;
import com.example.challenge.service.ChallengeListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ChallengeListController {

    private final ChallengeListService challengeListService;

    @PostMapping("/challengeList")
    public void add(@RequestBody ChallengeListDto challengeListDto){challengeListService.addChallengeList(challengeListDto);}

    @PutMapping("/challengeList")
    public void edit(@PathVariable Long id, @RequestBody ChallengeListDto challengeListDto){challengeListService.editChallengeList(id, challengeListDto);}

    @GetMapping("/challengeList")
    public List<ChallengeList> getAll(){return challengeListService.getAllChallengeList();}

    @GetMapping("/challengeList/{id}")
    public Optional<ChallengeList> getChallengeById(@PathVariable Long id){return challengeListService.getChallengeListById(id);}

    @DeleteMapping("/challengList/{id}")
    public void del(@PathVariable("id")Long id) {
        challengeListService.delChallengeList(id);
    }
}

