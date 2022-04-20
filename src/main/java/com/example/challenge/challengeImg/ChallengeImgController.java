package com.example.challenge.challengeImg;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@AllArgsConstructor
public class ChallengeImgController {

    private S3Service s3Service;
    private ChallengeImgService challengeImgService;


    @GetMapping("/challengeImg")
    public String dispWrite(Model model) {
        List<ChallengeImgDto> challengeImgDtoList = challengeImgService.getChallengeImgList();

        model.addAttribute("challengeImgList", challengeImgDtoList);

        return challengeImgDtoList.get(challengeImgDtoList.size() -1).getImgFullPath();
    }

    @PostMapping("/challengeImg")
    public String execWrite(ChallengeImgDto challengeImgDto, MultipartFile file) throws IOException {
        String imgPath = s3Service.upload(challengeImgDto.getUrl(), file);
        challengeImgDto.setUrl(imgPath);

        challengeImgService.saveChallengeImg(challengeImgDto);

        return "https://" + s3Service.CLOUD_FRONT_DOMAIN_NAME + "/" + challengeImgDto.getUrl();
    }
}

