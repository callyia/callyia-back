package com.project.Callyia.dto;

import com.project.Callyia.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleDTO {
    private Long sno;
    private String sName;
    private long total_Day;
    private String member_email;
}
