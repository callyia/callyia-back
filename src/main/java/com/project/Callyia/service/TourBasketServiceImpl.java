package com.project.Callyia.service;

import com.project.Callyia.dto.TourBasketDTO;
import com.project.Callyia.entity.TourBasket;
import com.project.Callyia.repository.TourBasketRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class TourBasketServiceImpl implements TourBasketService{
  private final TourBasketRepository tourBasketRepository;

  @Override
  public Long handleTourBasketRegistration(TourBasketDTO tourBasketDTO) {
    TourBasket tourBasket = dtoToEntity(tourBasketDTO);
    log.info("tourBasket: ....." + tourBasket);
    tourBasketRepository.save(tourBasket);
    return tourBasket.getBno();
  }
}
