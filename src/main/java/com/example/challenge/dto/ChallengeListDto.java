package com.example.challenge.dto;


import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class ChallengeListDto {

    private Long id;
    private Long challengeId;
    private Long userId;
    private int challengeListCount;
    private int challengeListState;
}
