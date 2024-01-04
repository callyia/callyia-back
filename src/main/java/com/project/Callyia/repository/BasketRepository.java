package com.project.Callyia.repository;

import com.project.Callyia.entity.Tour;
import com.project.Callyia.entity.Basket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasketRepository extends JpaRepository<Basket, Long> {
  boolean existsByPlaceId(Tour placeId);
}
