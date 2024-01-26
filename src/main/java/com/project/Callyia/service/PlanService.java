package com.project.Callyia.service;

import com.project.Callyia.dto.PlanDTO;
import com.project.Callyia.entity.Member;
import com.project.Callyia.entity.Plan;

public interface PlanService {
  default Plan dtoToEntity(PlanDTO planDTO) {
    Member member = Member.builder().email(planDTO.getUserId()).build();

    Plan plan = Plan.builder()
        .title(planDTO.getTitle())
        .member(member)
        .day(planDTO.getDay())
        .build();

    return plan;
  }

  default PlanDTO entityToDTO(Plan plan) {
    PlanDTO planDTO = PlanDTO.builder()
        .pno(plan.getPno())
        .title(plan.getTitle())
        .userId(plan.getMember().getEmail())
        .day(plan.getDay())
        .regDate(plan.getRegDate())
        .modDate(plan.getModDate())
        .build();

    return planDTO;
  };

  Long savePlan(PlanDTO planDTO);

  Long updatePlan(PlanDTO planDTO);

  PlanDTO getFromPno(Long pno);

}
