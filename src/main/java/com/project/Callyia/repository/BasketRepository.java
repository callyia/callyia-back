package com.project.Callyia.repository;

import com.project.Callyia.dto.BasketDTO;
import com.project.Callyia.entity.Basket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BasketRepository extends JpaRepository<Basket, Long> {
  List<Basket> findByMember_email(String email);

  void deleteByMember_email(String email);

  @Query("SELECT COUNT(b) > 0 FROM Basket b WHERE b.tour.placeId = :placeId AND b.member.email = :userId")
  boolean existsByBasket(Long placeId, String userId);
}
