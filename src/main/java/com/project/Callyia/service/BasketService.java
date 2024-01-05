package com.project.Callyia.service;

import com.project.Callyia.dto.BasketDTO;
import com.project.Callyia.entity.Tour;
import com.project.Callyia.entity.Basket;

public interface BasketService {

  default Basket dtoToEntity(BasketDTO basketDTO){
    Basket basket = Basket.builder()
        .bno(basketDTO.getBno())
        .placeId(basketDTO.getPlaceId())
//        .userId(basketDTO.getUserId())
        .build();
    return basket;
  }

  default BasketDTO entityToDTO(Basket basket){
    BasketDTO basketDTO = BasketDTO.builder()
        .bno(basket.getBno())
        .placeId(basket.getPlaceId())
//        .userId(basket.getUserId())
        .build();
    return basketDTO;
  }

  Long handleBasketRegistration(BasketDTO basketDTO);

  boolean isPlaceIdExists(Tour placeId);
}
