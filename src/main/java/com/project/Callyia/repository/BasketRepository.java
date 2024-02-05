package com.project.Callyia.repository;

import com.project.Callyia.entity.Tour;
import com.project.Callyia.entity.Basket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BasketRepository extends JpaRepository<Basket, Long> {
  boolean existsByTourPlaceId(Long placeId);

  List<Basket> findByMember_email(String email);
}
