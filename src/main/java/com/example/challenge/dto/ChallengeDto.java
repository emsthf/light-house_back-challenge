package com.example.challenge.dto;

import com.example.challenge.model.ChallengeList;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@NoArgsConstructor
@Data
public class ChallengeDto {

    private Long id;
    private Long challengeListId;
    private String challengeTitle;
    private LocalDate startDay;
    private LocalDate endDay;
    private int weekCount;
    private int totalCount;
    private int doing;
    private int challengeState;
    private int challengedesc;

}
