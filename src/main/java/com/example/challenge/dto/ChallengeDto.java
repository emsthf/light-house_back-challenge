package com.example.challenge.dto;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@Data
public class ChallengeDto {

    private Long id;
    private String challengeTitle;
    private String challengeDesc;
    private String challengeImg;
    private LocalDate startDay;
    private LocalDate endDay;
    private int period;
    private int weekCount;
    private int totalCount;
    private int challengeCount;
    private int challengeState;

    @Builder
    public ChallengeDto(Long id,
                        String challengeTitle, String challengeDesc, String challengeImg, LocalDate startDay, LocalDate endDay, int period, int weekCount, int totalCount, int challengeCount, int challengeState) {
        this.id = id;
        this.challengeTitle = challengeTitle;
        this.challengeDesc = challengeDesc;
        this.challengeImg = challengeImg;
        this.startDay = startDay;
        this.endDay = endDay;
        this.period = period;
        this.weekCount = weekCount;
        this.totalCount = totalCount;
        this.challengeCount = challengeCount;
        this.challengeState = challengeState;
    }

}
