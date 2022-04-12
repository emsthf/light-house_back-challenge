package com.example.challenge.repository;



import com.example.challenge.model.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//@RepositoryRestResource  //spring rest
public interface ChallengeRepository extends JpaRepository<Challenge, Long> {

//    //state 값에 따라 내림차순으로 3개만 조회하는 쿼리
//    List<Challenge> findTop3ByStateOrderByIdDesc(int state);
}
