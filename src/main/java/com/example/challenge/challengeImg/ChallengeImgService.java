package com.example.challenge.challengeImg;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChallengeImgService {

    private S3Service s3Service;
    private final ChallengeImgRepository challengeImgRepository;

    public void saveChallengeImg(ChallengeImgDto challengeImgDto){
        challengeImgRepository.save(challengeImgDto.toEntity());
    }

    public List<ChallengeImgDto> getChallengeImgList() {
        List<ChallengeImg> challengeImgList = challengeImgRepository.findAll();
        List<ChallengeImgDto> challengeImgDtoList = new ArrayList<>();

        for (ChallengeImg challengeImg : challengeImgList) {
            challengeImgDtoList.add(convertEntityToDto(challengeImg));
        }
        return challengeImgDtoList;
    }

    private ChallengeImgDto convertEntityToDto(ChallengeImg challengeImg) {
        return ChallengeImgDto.builder()
                .id(challengeImg.getId())
                .url(challengeImg.getUrl())
                .imgFullPath("https://" + s3Service.CLOUD_FRONT_DOMAIN_NAME + "/" + challengeImg.getUrl())
                .build();
    }

}
