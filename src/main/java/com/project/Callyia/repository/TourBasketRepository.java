package com.project.Callyia.repository;

import com.project.Callyia.entity.Tour;
import com.project.Callyia.entity.TourBasket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourBasketRepository extends JpaRepository<TourBasket, Long> {
  boolean existsByPlaceId(Tour placeId);
}
