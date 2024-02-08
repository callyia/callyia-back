package com.project.Callyia.controller;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.project.Callyia.dto.*;
import com.project.Callyia.service.BasketService;
import com.project.Callyia.service.TourService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/Basket")
public class BasketController {
  private final BasketService basketService;
  private final TourService tourService;

  @Setter
  @Getter
  @JsonSerialize
  public class basketTourDTO {
    private List<BasketDTO> basketDTOList;
    private List<TourDTO> tourDTOList;
  }

  @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Long> handleBasketRegistration(@RequestBody BasketDTO basketDTO){
    log.info(basketDTO.getPlaceId());
    log.info(basketDTO);
    try{
      if(basketService.isPlaceIdExists(basketDTO.getPlaceId(), basketDTO.getUserId())){
        log.warn("장바구니 등록 실패: 중복된 placeId입니다.");
        return new ResponseEntity<>(HttpStatus.CONFLICT);
      }
      Long bno = basketService.handleBasketRegistration(basketDTO);
      log.info("장바구니 등록 성공, bno: {}", bno);
      return new ResponseEntity<>(bno, HttpStatus.OK);
    } catch (Exception e){
      log.error("장바구니 등록 실패", e);
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
//    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping("/getBasket")
  public ResponseEntity<List<TourDTO>> getBasketFromEmail(@RequestParam String email) {
    log.info("/getBasket");
    List<BasketDTO> basketDTOs = basketService.getFromEmail(email);
    List<TourDTO> tourDTOs = basketDTOs.stream().map(basketDTO -> tourService.basketToTour(basketDTO)).collect(Collectors.toList());
    log.info(tourDTOs);

    return new ResponseEntity<>(tourDTOs, HttpStatus.OK);
  }

  @GetMapping("/getBasketPosting")
  public ResponseEntity<List<BasketDTO>> getBasketPosting(@RequestParam String email) {
    List<BasketDTO> basketDTOs = basketService.getFromEmail(email);
    return new ResponseEntity<>(basketDTOs, HttpStatus.OK);
  }

  @PostMapping("/register")
  public ResponseEntity<Long> registerBasket(@RequestBody BasketDTO basketDTO) {
    Long bno = basketService.handleBasketRegistration(basketDTO);
    return new ResponseEntity<>(bno, HttpStatus.OK);
  }

  @DeleteMapping("delete/{bno}")
  public ResponseEntity<String> removeReply(@PathVariable("bno") Long bno) {
    basketService.remove(bno);
    return new ResponseEntity<>(bno+"", HttpStatus.OK);
  }
}
