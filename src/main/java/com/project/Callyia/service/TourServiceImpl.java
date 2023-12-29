package com.project.Callyia.service;

import com.project.Callyia.dto.TourDTO;
import com.project.Callyia.entity.QTour;
import com.project.Callyia.entity.Tour;
import com.project.Callyia.repository.TourRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class TourServiceImpl implements TourService{
  private final TourRepository tourRepository;
  @Autowired
  private JPAQueryFactory jpaQueryFactory;

  @Override
  public Long handleTourRegistration(TourDTO tourDTO) {
    Tour tour = dtoToEntity(tourDTO);
    log.info("tour: ....." + tour);
    tourRepository.save(tour);
    return tour.getPlaceId();
  }

  @Override
  public void updateTourDTO(TourDTO tourDTO) {
    Tour tour = tourRepository.save(dtoToEntity(tourDTO));
    log.info("Update Tour : " + tour);
  }

  @Override
  public Page<TourDTO> getAllTours(Pageable pageable) {
    Page<Tour> allTours = tourRepository.findAll(pageable);
    List<TourDTO> tourDTOList = allTours.getContent().stream()
        .map(this::entityToDTO)
        .collect(Collectors.toList());

    return new PageImpl<>(tourDTOList, allTours.getPageable(), allTours.getTotalElements());
  }

  @Override
  public Page<TourDTO> getSearchTours(String checkColumn, String keyword, Pageable pageable) {
    QTour qTour = QTour.tour;

    BooleanBuilder booleanBuilder = new BooleanBuilder();

    if ("전체".equals(checkColumn)) {
      // "전체"인 경우 "관광지" 또는 "음식점" 데이터를 찾음
      booleanBuilder.andAnyOf(qTour.checkColumn.eq("관광지"), qTour.checkColumn.eq("음식점"));
    } else {
      // "전체"가 아닌 경우 지정된 checkColumn에 따라 필터링
      booleanBuilder.and(qTour.checkColumn.eq(checkColumn));
    }

    //keyword가 0이 아닐때 1을 반환
    if (StringUtils.hasText(keyword)) {
      // keyword가 비어있지 않은 경우 placeName 또는 address에 keyword를 포함하는 데이터를 찾음
      booleanBuilder.and(qTour.placeName.containsIgnoreCase(keyword).or(qTour.address.containsIgnoreCase(keyword)));
    }

    // Count 쿼리를 실행하여 전체 항목 수 가져오기
    long totalCount = jpaQueryFactory
        .selectFrom(qTour)
        .where(booleanBuilder)
        .fetchCount();

    // 실제 데이터를 가져오는 쿼리
    List<TourDTO> searchTours = jpaQueryFactory
        .select(Projections.bean(TourDTO.class, qTour.placeId, qTour.placeName, qTour.address, qTour.latitude,
            qTour.longitude, qTour.placeContent, qTour.checkColumn, qTour.image))
        .from(qTour)
        .where(booleanBuilder)
        .offset(pageable.getOffset()) // pageable에서 offset을 가져옴
        .limit(pageable.getPageSize()) // pageable에서 pageSize를 가져옴
        .fetch();

    return new PageImpl<>(searchTours, pageable, totalCount);
  }
}