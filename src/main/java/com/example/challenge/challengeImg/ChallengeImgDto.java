package com.example.challenge.challengeImg;

import lombok.*;

@Data
@NoArgsConstructor
@ToString
public class ChallengeImgDto {

    private Long id;
    private String url;
    private String imgFullPath;

    public ChallengeImg toEntity(){
        ChallengeImg build = ChallengeImg.builder()
                .id(id)
                .url(url)
                .build();
        return build;
    }

    @Builder
    public ChallengeImgDto(Long id, String url, String imgFullPath){
        this.id = id;
        this.url = url;
        this.imgFullPath = imgFullPath;
    }
}
