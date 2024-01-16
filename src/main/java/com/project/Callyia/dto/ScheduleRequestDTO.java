package com.project.Callyia.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleRequestDTO {
  private DetailScheduleDTO[] detailScheduleDTOs;
  private ScheduleDTO scheduleDTO;
}
