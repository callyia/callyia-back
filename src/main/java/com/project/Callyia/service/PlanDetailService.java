package com.project.Callyia.service;

import com.project.Callyia.dto.PlanDetailDTO;
import com.project.Callyia.entity.PlanDetail;


public interface PlanDetailService {

  PlanDetail dtoToEntity(PlanDetailDTO planDetailDTO);

  void savePlanDetails(Long pno, PlanDetailDTO[] planDetailDTOs);

  void deleteByPno(Long pno);
}
