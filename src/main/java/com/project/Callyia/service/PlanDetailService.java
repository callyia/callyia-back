package com.project.Callyia.service;

import com.project.Callyia.dto.PlanDetailDTO;
import com.project.Callyia.entity.PlanDetail;

import java.util.List;


public interface PlanDetailService {

  PlanDetail dtoToEntity(PlanDetailDTO planDetailDTO);
  PlanDetailDTO entityToDto(PlanDetail planDetail);

  void savePlanDetails(Long pno, PlanDetailDTO[] planDetailDTOs);

  void deleteByPno(Long pno);

  List<PlanDetailDTO> getFromPno(Long pno);
}
