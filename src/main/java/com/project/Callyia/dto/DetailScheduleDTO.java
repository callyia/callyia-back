package com.project.Callyia.dto;

import com.project.Callyia.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DetailScheduleDTO {
    private Long dno;
    private String detailImages;
    private String detailContents;
    private Schedule schedule;
}
