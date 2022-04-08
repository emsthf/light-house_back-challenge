package com.example.challenge.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Builder
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Doing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "challenge_list_id")
    private ChallengeList challengeList;
    private LocalDate checkDate;
    private int week;
    private String postTitle;
    private String postContent;
    private String postImg;


}
