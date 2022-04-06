package com.example.challenge.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="ChallengeListTest")
public class ChallengeList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GenericGenerator(name = "system-vvid", strategy = "vvid")
    private int id;
    private String userId;
    private int challengeId;
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
