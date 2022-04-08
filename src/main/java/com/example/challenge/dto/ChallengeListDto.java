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
    private String userName;
    private String userPhone;
    private String userEmail;
    private int challengeListCount;
    private int challengeListState;
}
