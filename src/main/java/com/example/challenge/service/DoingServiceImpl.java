package com.example.challenge.service;


import com.example.challenge.model.Doing;
import com.example.challenge.repository.DoingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class DoingServiceImpl implements DoingService{

    private final DoingRepository doingRepository;

    @Override
    public Doing addDoing(Doing doing) {
        log.info("add Doing");
        return doingRepository.save(doing);
    }

    @Override
    public Doing editDoing(Doing doing) {
        log.info("edit Doing");
        return doingRepository.save(doing);
    }

    @Override
    public List<Doing> getAllDoing() {
        log.info("get All Doing");
        return doingRepository.findAll();
    }

    @Override
    public Optional<Doing> getDoingById(Long id) {
        log.info("get Doing by {id}", id);
        return Optional.ofNullable(doingRepository.findById(id).get());
    }

    @Override
    public void delDoing(Long id) {
        log.info("delete Doing by {id}", id);
        doingRepository.deleteById(id);
    }

    @Override
    public List<Doing> findAllByUserChallengeId(Long userChallengeId) {
        return doingRepository.findAllByUserChallengeId(userChallengeId);
    }

    @Override
    public List<Doing> findByAllByWeek(int week) {
        return doingRepository.findAllByWeek(week);
    }

    @Override
    public Doing findByChallengeIdAndCheckDate(Long userChallengeId, LocalDate localDate) {
        return doingRepository.findByUserChallengeIdAndCheckDate(userChallengeId, localDate);
    }
}
