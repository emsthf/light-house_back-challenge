package com.example.challenge.service;

import com.example.challenge.model.Doing;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DoingService {
    Doing addDoing(Doing doing);
    Doing editDoing(Doing doing);
    List<Doing> getAllDoing();
    Optional<Doing> getDoingById(Long id);
    void delDoing(Long id);
    List<Doing> findAllByChallengeListId(Long challengeList);
    List<Doing> findAllByWeek(int week);
    Doing findByChallengeListIdAndCheckDate(Long challengeListId, LocalDate localDate);
}
