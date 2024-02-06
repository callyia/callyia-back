package com.project.Callyia.controller;

import com.project.Callyia.dto.TourDTO;
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

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/Tour")
public class TourController {
  private final TourService tourService;

  @GetMapping("/all")
  public ResponseEntity<Page<TourDTO>> getAllTours(@RequestParam(defaultValue = "1") int page,
                                                   @RequestParam(defaultValue = "12") int size) {
    try {
      Pageable pageable = PageRequest.of(page - 1, size);
      Page<TourDTO> allTours = tourService.getAllTours(pageable);
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
      if(tourService.isPlaceNameExists(tourDTO.getPlaceName()) && tourService.isAddressExists(tourDTO.getAddress())){
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

  @GetMapping("/getCount")
  public ResponseEntity<Long> getCount() {
    return new ResponseEntity<>(tourService.getTourCount(), HttpStatus.OK);
  }
}