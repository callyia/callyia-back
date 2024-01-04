package com.project.Callyia.controller;

import com.project.Callyia.dto.TourDTO;
import com.project.Callyia.entity.Tour;
import com.project.Callyia.service.TourService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Log4j2
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/Tour")
public class TourController {
  private final TourService tourService;

  @GetMapping("/all")
  public ResponseEntity<Page<TourDTO>> getAllTours(@RequestParam(defaultValue = "1") int page,
                                                   @RequestParam(defaultValue = "12") int size) {
    try {
      Pageable pageable = PageRequest.of(page - 1, size);
      Page<TourDTO> allTours = tourService.getAllTours(pageable);
      log.info("allTours : ", allTours);
      return new ResponseEntity<>(allTours, HttpStatus.OK);
    } catch (Exception e) {
      log.error("전체 투어 조회 실패", e);
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/search")
  public ResponseEntity<Page<TourDTO>> getSearchTours(@RequestParam String checkColumn,
                                                      @RequestParam String keyword,
                                                      @RequestParam(defaultValue = "1") int page,
                                                      @RequestParam(defaultValue = "12") int size) {
    try {
      log.info("Received request with checkColumn: {}, keyword: {}", checkColumn, keyword);
      Pageable pageable = PageRequest.of(page - 1, size);
      Page<TourDTO> searchTours = tourService.getSearchTours(checkColumn, keyword, pageable);
      return new ResponseEntity<>(searchTours, HttpStatus.OK);
    } catch (Exception e) {
      log.error("검색 투어 조회 실패", e);
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Long> handleTourRegistration(@RequestBody TourDTO tourDTO) {
    try {
      if(tourService.isPlaceNameExists(tourDTO.getPlaceName())){
        log.warn("투어 등록 실패: 중복된 placeName입니다.");
        return new ResponseEntity<>(HttpStatus.CONFLICT);
      }
      Long placeId = tourService.handleTourRegistration(tourDTO);
      log.info("투어 등록 성공, placeId: {}", placeId);
      return new ResponseEntity<>(placeId, HttpStatus.OK);
    } catch (Exception e) {
      log.error("투어 등록 실패", e);
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PostMapping("/upload")
  public ResponseEntity<Map<String, String>> handleImageUpload(@RequestParam("file") MultipartFile file) {
    // 이미지를 저장할 경로 설정
    // aws s3 쓰면 수정할 예정
    String uploadPath = "src/main/resources/static/uploads/";

    // 고유한 파일 이름 생성
    String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();


    // 이미지 저장 경로 반환
    // aws s3 경로로 변경할 예정
//    String imagePath = "/Callyia/static/uploads/" + fileName;
    String imagePathDummy = "https://deepdaive.com/wp-content/uploads/2023/10/image-12.png";
    Map<String, String> responseMap = new HashMap<>();
//    responseMap.put("imagePath", imagePath);
    responseMap.put("imagePath", imagePathDummy);

    return ResponseEntity.ok(responseMap);
  }
}