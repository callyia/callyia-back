package com.project.Callyia.service;

import com.project.Callyia.dto.PlanDTO;
import com.project.Callyia.entity.Plan;

public interface PlanService {
  default Plan dtoToEntity(PlanDTO planDTO) {
    Plan plan = Plan.builder()
        .title(planDTO.getTitle())
        .userId(planDTO.getUserId())
        .build();

    return plan;
  }

  Long savePlan(PlanDTO planDTO);

  Long updatePlan(PlanDTO planDTO);
}
