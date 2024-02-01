package com.project.Callyia.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/s3")
public class S3Controller {

  @Autowired
  private S3Client s3Client;

  //aws s3 사용시 활성화
  @PostMapping("/upload")
  public ResponseEntity<Map<String, String>> handleImageUpload(@RequestParam("file") MultipartFile file) {
    String bucketName = "callyia";
    String folderName = "Image_Upload";

// 고유한 파일 이름 생성
    String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

    uploadFileToS3(bucketName, folderName, fileName, file);

    String imagePath = generateImageUrl(bucketName, folderName, fileName);

    Map<String, String> responseMap = new HashMap<>();
    responseMap.put("imagePath", imagePath);



    return ResponseEntity.ok(responseMap);
  }

  @PostMapping("/profile")
  public ResponseEntity<Map<String, String>> handleImageProfile(@RequestParam("file") MultipartFile file){
    String bucketName = "callyia";
    String folderName = "Image_Profile";

    String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

    uploadFileToS3(bucketName, folderName, fileName, file);

    String imagePath = generateImageUrl(bucketName, folderName, fileName);

    Map<String, String> responseMap = new HashMap<>();
    responseMap.put("imagePath", imagePath);

    return ResponseEntity.ok(responseMap);
  }

  @PostMapping("/posting")
  public ResponseEntity<List<String>> handleImagePosting(@RequestParam("file") List<MultipartFile> files){
    String bucketName = "callyia";
    String folderName = "Image_Posting";

    List<String> responsePath = new ArrayList<>();

    for (MultipartFile file : files) {
      String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

      uploadFileToS3(bucketName, folderName, fileName, file);

      String imagePath = generateImageUrl(bucketName, folderName, fileName);

      responsePath.add(imagePath);
    }

    log.info(responsePath);
    return ResponseEntity.ok(responsePath);
  }



  private String generateImageUrl(String bucketName, String folderName, String fileName) {
    // S3에 업로드된 이미지 URL 생성
    return "https://" + bucketName + ".s3.amazonaws.com/" + folderName + "/" + fileName;
  }

  private void uploadFileToS3(String bucketName, String folderName, String fileName, MultipartFile file) {
    try {
      PutObjectRequest putObjectRequest = PutObjectRequest.builder()
          .bucket(bucketName)
          .key(folderName + "/" + fileName)
          .build();

      // 파일 데이터를 S3에 업로드
      s3Client.putObject(putObjectRequest, software.amazon.awssdk.core.sync.RequestBody.fromBytes(file.getBytes()));
    } catch (IOException e){
      e.printStackTrace();
    }
  }
}
