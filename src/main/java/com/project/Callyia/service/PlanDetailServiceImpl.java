package com.project.Callyia.service;

import com.project.Callyia.dto.PlanDetailDTO;
import com.project.Callyia.entity.Plan;
import com.project.Callyia.entity.PlanDetail;
import com.project.Callyia.entity.Tour;
import com.project.Callyia.repository.PlanDetailRepository;
import com.project.Callyia.repository.PlanRepository;
import com.project.Callyia.repository.TourRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class PlanDetailServiceImpl implements PlanDetailService {
  private final PlanDetailRepository planDetailRepository;
  private final PlanRepository planRepository;
  private final TourRepository tourRepository;

  public PlanDetail dtoToEntity(PlanDetailDTO planDetailDTO) {
    PlanDetail planDetail = PlanDetail.builder()
        .day(planDetailDTO.getDay())
        .sequence(planDetailDTO.getSequence())
        .build();


    if(planDetailDTO.getPno() != null) {
      Optional<Plan> optionalPlan = planRepository.findById(planDetailDTO.getPno());
      Plan plan = optionalPlan.orElseThrow(() ->
          new EntityNotFoundException("해당 pno로 Plan을 찾을 수 없습니다."));
      planDetail.setPlan(plan);
    }

    if(planDetailDTO.getPlaceId() != null) {
      Optional<Tour> optionalTour = tourRepository.findById(planDetailDTO.getPlaceId());
      Tour tour = optionalTour.orElseThrow(() ->
          new EntityNotFoundException("해당 placeId로 Tour을 찾을 수 없습니다."));
      planDetail.setTour(tour);
    }

    return planDetail;
  }

  @Override
  public PlanDetailDTO entityToDto(PlanDetail planDetail) {
    PlanDetailDTO planDetailDTO = PlanDetailDTO.builder()
        .dno(planDetail.getDno())
        .pno(planDetail.getPlan().getPno())
        .placeId(planDetail.getTour().getPlaceId())
        .day(planDetail.getDay())
        .sequence(planDetail.getSequence())
        .build();

    return planDetailDTO;
  }

  @Override
  public void savePlanDetails(Long pno, PlanDetailDTO[] planDetailDTOs) {
    for(PlanDetailDTO planDetailDTO : planDetailDTOs) {
      log.info("PlanDetailDTO : " + planDetailDTO);
      planDetailDTO.setPno(pno);
      planDetailRepository.save(dtoToEntity(planDetailDTO));
    }
  }

  @Override
  public void deleteByPno(Long pno) {
    planDetailRepository.deleteByPlan_Pno(pno);
  }

  @Override
  public List<PlanDetailDTO> getFromPno(Long pno) {
    List<PlanDetail> planDetailList = planDetailRepository.findByPlan_Pno(pno);
    List<PlanDetailDTO> planDetailDTOList = (List<PlanDetailDTO>) planDetailList.stream()
        .map(planDetail -> entityToDto(planDetail)).collect(Collectors.toList());

    return planDetailDTOList;
  }

}
