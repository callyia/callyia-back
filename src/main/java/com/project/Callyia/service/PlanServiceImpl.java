package com.project.Callyia.service;

import com.project.Callyia.dto.PlanDTO;
import com.project.Callyia.entity.Plan;
import com.project.Callyia.repository.PlanRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class PlanServiceImpl implements PlanService {
  private final PlanRepository planRepository;

  @Override
  public Long savePlan(PlanDTO planDTO) {
    Plan savedPlan = planRepository.save(dtoToEntity(planDTO));
    Long savedPno = savedPlan.getPno();

    return savedPno;
  }

  @Override
  public Long updatePlan(PlanDTO planDTO) {

    Plan plan = planRepository.findById(planDTO.getPno()).orElse(null);
    Long updatedPno = null;
    if(plan != null) {
      plan.setTitle(planDTO.getTitle());
      plan.setModDate();
      Plan uplan = planRepository.save(plan);
      updatedPno = uplan.getPno();
    }
    return updatedPno;
  }

  @Override
  public PlanDTO getFromPno(Long pno) {
    Plan plan = planRepository.findById(pno).orElse(null);
    return entityToDTO(plan);
  }

  @Override
  public List<PlanDTO> getFromEmail(String email) {
    List<Plan> plans = planRepository.findByMember_Email(email);
    List<PlanDTO> planDTOs = plans.stream().map(plan -> entityToDTO(plan)).collect(Collectors.toList());

    return planDTOs;
  }

  @Override
  public boolean deletePlan(Long pno) {

    Optional<Plan> optionalPlan = planRepository.findById(pno);

    if(!optionalPlan.isPresent())
      return false;

    planRepository.deleteById(pno);
    return true;
  }

  @Override
  public List<Plan> findByMemberEmail(String email) {
    return planRepository.findByMember_Email(email);
  }
}
