package com.project.Callyia.controller;


import com.project.Callyia.dto.BasketDTO;
import com.project.Callyia.service.BasketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/Basket")
public class BasketController {
  private final BasketService basketService;

  @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Long> handleBasketRegistration(@RequestBody BasketDTO basketDTO){
    try{
      if(basketService.isPlaceIdExists(basketDTO.getPlaceId())){
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
  }
}
