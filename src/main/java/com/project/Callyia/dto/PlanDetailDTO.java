package com.project.Callyia.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlanDetailDTO {
  private Long dno;
  private Long pno;
  private Long placeId;
  private Long day;
  private Long sequence;
}
