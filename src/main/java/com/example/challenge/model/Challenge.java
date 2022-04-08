package com.example.challenge.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Challenge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //    @OneToMany(mappedBy = "challenge")
//    private List<ChallengeList> challengeList = new ArrayList<ChallengeList>();
    private String challengeTitle;
    private String challengeDesc; //
    private LocalDate startDay;
    private LocalDate endDay;
    private int period; //
    private int weekCount;
    private int totalCount;
    private int challengeCount; //
    private int challengeState; //
    private boolean challengeResult; //

}
