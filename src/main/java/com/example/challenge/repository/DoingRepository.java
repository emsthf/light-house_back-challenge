package com.example.challenge.repository;


import com.example.challenge.model.Doing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public interface DoingRepository extends JpaRepository<Doing, Long> {
    List<Doing> findAllByChallengeId(Long challengeId);
    List<Doing> findAllByWeek(int week);
    Doing findByChallengeIdAndCheckDate(Long challengeId, LocalDate localDate);
}
