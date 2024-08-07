package com.project.Callyia.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleStarDTO {
    private Long starNum;
    private Long starScore;
    private Long sno;
    private String email;
}
