package com.example.challenge.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class Challenge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "challenge_list_id")
    private ChallengeList challengeList;
    private String challengeTitle;
    private LocalDate startDay;
    private LocalDate endDay;
    private int weekCount;
    private int totalCount;
    private int doing;
    private int challengeState;
    private int challengedesc;

    @Builder
    public Challenge(Long id, ChallengeList challengeList, String challengeTitle, LocalDate startDay, LocalDate endDay, int weekCount, int totalCount, int doing, int challengeState, int challengedesc) {
        this.id = id;
        this.challengeList = challengeList;
        this.challengeTitle = challengeTitle;
        this.startDay = startDay;
        this.endDay = endDay;
        this.weekCount = weekCount;
        this.totalCount = totalCount;
        this.doing = doing;
        this.challengeState = challengeState;
        this.challengedesc = challengedesc;
    }
}
