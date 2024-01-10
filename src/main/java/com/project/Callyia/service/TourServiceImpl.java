package com.project.Callyia.service;

import com.project.Callyia.dto.PlanDetailDTO;
import com.project.Callyia.dto.TourDTO;
import com.project.Callyia.entity.Tour;
import com.project.Callyia.repository.TourRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
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

  @PersistenceContext
  private EntityManager entityManager;

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
    String jpql = "SELECT NEW com.project.Callyia.dto.TourDTO("
        + "t.placeId, t.placeName, t.address, t.latitude, t.longitude, t.placeContent, t.checkColumn, t.image)"
        + " FROM Tour t ";

    StringBuilder whereClause = new StringBuilder(" WHERE ");
    boolean isCheckColumnAll = "전체".equals(checkColumn);

    if (isCheckColumnAll) {
      whereClause.append("(t.checkColumn = '관광지' OR t.checkColumn = '음식점')");
    } else {
      whereClause.append("t.checkColumn = :checkColumn");
    }

    if (StringUtils.hasText(keyword)) {
      if (isCheckColumnAll) {
        whereClause.append(" AND ");
      } else {
        whereClause.append(" AND ");
        whereClause.append("t.checkColumn = :checkColumn AND ");
      }
      whereClause.append("(LOWER(t.placeName) LIKE LOWER(:keyword) OR LOWER(t.address) LIKE LOWER(:keyword))");
    }

    jpql += whereClause.toString();

    Query query = entityManager.createQuery(jpql, TourDTO.class);

    if (!isCheckColumnAll) {
      query.setParameter("checkColumn", checkColumn);
    }

    if (StringUtils.hasText(keyword)) {
      query.setParameter("keyword", "%" + keyword + "%");
    }

    TypedQuery<Long> countQuery = entityManager.createQuery("SELECT COUNT(t) FROM Tour t" + whereClause.toString(), Long.class);

    if (!isCheckColumnAll){
      countQuery.setParameter("checkColumn", checkColumn);
    }

    if (StringUtils.hasText(keyword)) {
      countQuery.setParameter("keyword", "%" + keyword + "%");
    }


    long totalCount = countQuery.getSingleResult();

    query.setFirstResult((int) pageable.getOffset());
    query.setMaxResults(pageable.getPageSize());

    List<TourDTO> searchTours = query.getResultList();

    return new PageImpl<>(searchTours, pageable, totalCount);
  }

  @Override
  public List<TourDTO> getSearchTours(String keyword) {
//    List<Tour> tourList = tourRepository.searchTourList(keyword);
    List<Tour> tourList = tourRepository.searchTourList(keyword);
    return tourList.stream().map(tour -> entityToDTO(tour)).collect(Collectors.toList());
  }

  @Override
  public TourDTO planDetailToTour(PlanDetailDTO planDetailDTO) {
    Long placeId = planDetailDTO.getPlaceId();
    Tour tour = tourRepository.findByPlaceId(placeId);
    return entityToDTO(tour);
  }

  @Override
  public boolean isPlaceNameExists(String placeName) {
    return tourRepository.existsByPlaceName(placeName);
  }
}