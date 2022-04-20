package com.example.challenge.challengeImg;

import lombok.*;

@Data
@NoArgsConstructor
@ToString
public class ChallengeImgDto {

    private Long id;
    private String url;
    private String imgFullPath;
    private Long challengeId;

    public ChallengeImg toEntity(){
        ChallengeImg build = ChallengeImg.builder()
                .id(id)
                .url(url)
                .challengeId(challengeId)
                .build();
        return build;
    }

    @Builder
    public ChallengeImgDto(Long id, String url, String imgFullPath, Long challengeId){
        this.id = id;
        this.url = url;
        this.imgFullPath = imgFullPath;
        this.challengeId = challengeId;
    }
}
