package com.example.challenge.challengeImg;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class ChallengeImg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "TEXT")
    private String url;
    private Long challengeId;

    @Builder
    public ChallengeImg(Long id, String url, Long challengeId){
        this.id = id;
        this.url = url;
        this.challengeId = challengeId;
    }
}
