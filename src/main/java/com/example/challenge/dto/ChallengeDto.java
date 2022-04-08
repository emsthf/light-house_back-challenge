package com.example.challenge.dto;


import com.example.challenge.model.ChallengeList;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@Data
public class ChallengeDto {

    private Long id;
    private String challengeTitle;
    private String challengeDesc; //
    private LocalDate startDay;
    private LocalDate endDay;
    private int period; //
    private int weekCount;
    private int totalCount;
    private int challengeCount; //
    private int challengeState; //
//    private boolean challengeResult;

    @Builder
    public ChallengeDto(Long id,
                        String challengeTitle, String challengeDesc, LocalDate startDay, LocalDate endDay, int period, int weekCount, int totalCount, int challengeCount, int challengeState) {
        this.id = id;
        this.challengeTitle = challengeTitle;
        this.challengeDesc = challengeDesc;
        this.startDay = startDay;
        this.endDay = endDay;
        this.period = period;
        this.weekCount = weekCount;
        this.totalCount = totalCount;
        this.challengeCount = challengeCount;
        this.challengeState = challengeState;
//        this.challengeResult = challengeResult;
    }

}
