package com.project.Callyia.service;

import com.project.Callyia.dto.BasketDTO;
import com.project.Callyia.entity.Member;
import com.project.Callyia.entity.Tour;
import com.project.Callyia.entity.Basket;

import java.util.List;

public interface BasketService {

  default Basket dtoToEntity(BasketDTO basketDTO){
    Member member = Member.builder().email(basketDTO.getUserId()).build();
    Tour tour = Tour.builder().placeId(basketDTO.getPlaceId()).build();

    Basket basket = Basket.builder()
        .bno(basketDTO.getBno())
        .tour(tour)
        .member(member)
        .build();
    return basket;
  }

  default BasketDTO entityToDTO(Basket basket){


    BasketDTO basketDTO = BasketDTO.builder()
        .bno(basket.getBno())
        .placeId(basket.getTour().getPlaceId())
        .userId(basket.getMember().getEmail())
        .placeName(basket.getTour().getPlaceName())
        .image(basket.getTour().getImage())
        .address(basket.getTour().getAddress())
        .build();
    return basketDTO;
  }

  Long handleBasketRegistration(BasketDTO basketDTO);

  List<BasketDTO> getFromEmail(String email);

  void remove(Long bno);

  void deleteBasketByEmail(String email);

  boolean isPlaceIdExists(Long placeId, String userId);
}
