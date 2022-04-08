package com.example.challenge.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="ChallengeListTest")
public class ChallengeList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //    private Long challengeId;
    @ManyToOne
    @JoinColumn(name = "challengeId")
    private Challenge challenge;
    private String userName;
    private String userPhone;
    private String userEmail;
    private int challengeListCount; //
    private int challengeListState; //

}
