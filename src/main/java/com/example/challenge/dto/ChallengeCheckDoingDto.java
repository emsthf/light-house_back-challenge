package com.example.challenge.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class ChallengeCheckDoingDto {

    private Long id;
    private Long challengeId;
    private String challengeTitle;
    private String challengeDesc; //
    private LocalDate startDay;
    private LocalDate endDay;
    private int period; //
    private int weekCount;
    private int totalCount;
    private int challengeCount; //
    private int challengeState; //
    private boolean challengeResult;
    private Long postId;

}
