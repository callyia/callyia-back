package com.project.Callyia.dto;

import com.project.Callyia.entity.DetailSchedule;
import com.project.Callyia.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReplyDTO {
    private Long rno;
    private String replyContents;
    private long dno;
    private String replyer;
}
