package com.example.challenge.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@Builder
public class Challenge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String challengeTitle;
    private LocalDate startDay;
    private LocalDate endDay;
    private int weekCount;
    private int totalCount;
    private int doing;
    private int challengeState;
    private int challengedesc;

    @Builder
    public Challenge(Long id, String challengeTitle, LocalDate startDay, LocalDate endDay, int weekCount, int totalCount, int doing, int challengeState, int challengedesc) {
        this.id = id;
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
