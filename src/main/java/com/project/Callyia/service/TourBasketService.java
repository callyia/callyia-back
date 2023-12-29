package com.project.Callyia.service;

import com.project.Callyia.dto.TourBasketDTO;
import com.project.Callyia.entity.TourBasket;

public interface TourBasketService {

  default TourBasket dtoToEntity(TourBasketDTO tourBasketDTO){
    TourBasket tourBasket = TourBasket.builder()
        .bno(tourBasketDTO.getBno())
        .placeId(tourBasketDTO.getPlaceId())
//        .userId(tourBasketDTO.getUserId())
        .build();
    return tourBasket;
  }

  default TourBasketDTO entityToDTO(TourBasket tourBasket){
    TourBasketDTO tourBasketDTO = TourBasketDTO.builder()
        .bno(tourBasket.getBno())
        .placeId(tourBasket.getPlaceId())
//        .userId(tourBasket.getUserId())
        .build();
    return tourBasketDTO;
  }

  Long handleTourBasketRegistration(TourBasketDTO tourBasketDTO);
}
