package com.project.Callyia.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlanRequestDTO {
  private PlanDetailDTO[] planDetailDTOs;
  private PlanDTO planDTO;
}
