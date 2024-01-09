package com.project.Callyia.repository;

import com.project.Callyia.entity.Tour;
import org.springframework.data.jpa.repository.JpaRepository;

// QuerydslPredicateExecutor은 Querydsl 사용하기 위하여 사용
public interface TourRepository extends JpaRepository<Tour, Long> {
  boolean existsByPlaceName(String placeName);
}
