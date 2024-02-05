package com.project.Callyia.repository;

import com.project.Callyia.entity.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

// QuerydslPredicateExecutor은 Querydsl 사용하기 위하여 사용
public interface TourRepository extends JpaRepository<Tour, Long> {
  boolean existsByPlaceName(String placeName);

  Tour findByPlaceId(Long placeId);

  @Query("select t from Tour t where t.placeName like %:placeName% ")
  List<Tour> searchTourList(@Param("placeName") String placeName);

}
