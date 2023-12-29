package com.project.Callyia.controller;


import com.project.Callyia.dto.TourBasketDTO;
import com.project.Callyia.service.TourBasketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Log4j2
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/TourBasket")
public class TourBasketController {
  private final TourBasketService tourBasketService;

  @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Long> handleTourBasketRegistration(@RequestBody TourBasketDTO tourBasketDTO){
    try{
      Long bno = tourBasketService.handleTourBasketRegistration(tourBasketDTO);
      log.info("장바구니 등록 성공, bno: {}", bno);
      return new ResponseEntity<>(bno, HttpStatus.OK);
    } catch (Exception e){
      log.error("장바구니 등록 실패", e);
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
