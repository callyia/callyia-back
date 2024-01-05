package com.project.Callyia.service;

import com.project.Callyia.dto.BasketDTO;
import com.project.Callyia.entity.Tour;
import com.project.Callyia.entity.Basket;
import com.project.Callyia.repository.BasketRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class BasketServiceImpl implements BasketService {
  private final BasketRepository basketRepository;

  @Override
  public Long handleBasketRegistration(BasketDTO basketDTO) {
    Basket basket = dtoToEntity(basketDTO);
    log.info("basket: ....." + basket);
    basketRepository.save(basket);
    return basket.getBno();
  }

  @Override
  public boolean isPlaceIdExists(Tour placeId) {
    return basketRepository.existsByPlaceId(placeId);
  }
}
