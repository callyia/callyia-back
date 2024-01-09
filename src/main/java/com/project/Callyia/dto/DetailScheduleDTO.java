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
    private String place;
    private String content;
    private double lat; //위도
    private double lng; //경도
    private String detailImages;
    private Schedule schedule;
}
