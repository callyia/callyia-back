package com.project.Callyia.service;

import com.project.Callyia.dto.BasketDTO;
import com.project.Callyia.dto.DetailScheduleDTO;
import com.project.Callyia.dto.PlanDetailDTO;
import com.project.Callyia.dto.TourDTO;
import com.project.Callyia.entity.Tour;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TourService {
  Long handleTourRegistration(TourDTO tourDTO);
  void updateTourDTO(TourDTO tourDTO);

  default Tour dtoToEntity(TourDTO tourDTO){
    Tour tour = Tour.builder()
        .placeId(tourDTO.getPlaceId())
        .placeName(tourDTO.getPlaceName())
        .address(tourDTO.getAddress())
        .latitude(tourDTO.getLatitude())
        .longitude(tourDTO.getLongitude())
        .placeContent(tourDTO.getPlaceContent())
        .checkColumn(tourDTO.getCheckColumn())
        .image(tourDTO.getImage())
        .build();
    return tour;
  }

  default TourDTO entityToDTO(Tour tour){
    TourDTO tourDTO = TourDTO.builder()
        .placeId(tour.getPlaceId())
        .placeName(tour.getPlaceName())
        .address(tour.getAddress())
        .latitude(tour.getLatitude())
        .longitude(tour.getLongitude())
        .placeContent(tour.getPlaceContent())
        .checkColumn(tour.getCheckColumn())
        .image(tour.getImage())
        .build();
    return tourDTO;
  }

  Page<TourDTO> getAllTours(Pageable pageable);

  Page<TourDTO> getSearchTours(String checkColumn, String keyword, Pageable pageable);

  List<TourDTO> getSearchTours(String keyword);

  TourDTO planDetailToTour(PlanDetailDTO planDetailDTO);

  TourDTO basketToTour(BasketDTO basketDTO);

  TourDTO detailScheduleToTour(DetailScheduleDTO detailScheduleDTO);

  boolean isPlaceNameExists(String placeName);
}