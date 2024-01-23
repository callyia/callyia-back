package com.project.Callyia.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberRequestDTO {
  private MemberDTO memberDTO;
  private List<ScheduleDTO> scheduleDTOs;
}