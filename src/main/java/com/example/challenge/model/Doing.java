package com.example.challenge.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Doing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_challenge_id")
    private UserChallenge userChallenge;
    private LocalDate checkDate;
    private int week;
    private Long postId;

    public Doing(UserChallenge userChallenge, LocalDate checkDate, int week, Long postId) {
        this.userChallenge = userChallenge;
        this.checkDate = checkDate;
        this.week = week;
        this.postId = postId;
    }
}
