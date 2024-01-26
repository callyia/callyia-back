package com.project.Callyia.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlanDTO {
  private Long pno;
  private String title;
  private String userId;
  private Long day;

  LocalDateTime regDate;
  LocalDateTime modDate;
}
