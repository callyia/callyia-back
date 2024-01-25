package com.project.Callyia.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.Callyia.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleDTO {
    private Long sno;
    @JsonProperty("sName")
    private String sName;
    private long total_Day;
    private String member_email;
    private String member_nickname;
    private String member_profile_image;

    private LocalDateTime regDate;
    private LocalDateTime modDate;
}
