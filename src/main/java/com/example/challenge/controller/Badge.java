package com.example.challenge.controller;

import lombok.Data;

@Data
public class Badge {

    private Long id;
    private String badgeName;
    private String badgeDesc;
    private String badgeImg;
    private int point;
    private String type;

}
