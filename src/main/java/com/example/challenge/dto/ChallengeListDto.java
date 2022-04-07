package com.example.challenge.dto;

import com.example.challenge.model.Challenge;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class ChallengeListDto {

    private Long id;
    private Long challengeId;
    private String challengeTitle;
    private LocalDate startDay;
    private LocalDate endDay;
    private int weekCount;
    private int period;
    private int totalCount;
    private int count;
    private int doing;
    private int state;
    private int result;
}

