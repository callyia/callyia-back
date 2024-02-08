package com.project.Callyia.service;

import com.project.Callyia.dto.ReplyDTO;
import com.project.Callyia.dto.ScheduleDTO;
import com.project.Callyia.dto.ScheduleStarDTO;
import com.project.Callyia.entity.Member;
import com.project.Callyia.entity.Schedule;
import com.project.Callyia.entity.ScheduleStar;

import java.util.List;

public interface ScheduleStarService {

    default ScheduleStar dtoToEntity(ScheduleStarDTO scheduleStarDTO) {
        Schedule schedule = Schedule.builder().sno(scheduleStarDTO.getSno()).build();
        Member member = Member.builder().email(scheduleStarDTO.getEmail()).build();

        ScheduleStar scheduleStar = ScheduleStar.builder()
                .starScore(scheduleStarDTO.getStarScore())
                .schedule(schedule)
                .member(member)
                .build();
        return scheduleStar;
    }

    default ScheduleStarDTO entityToDTO(ScheduleStar scheduleStar) {
        ScheduleStarDTO scheduleStarDTO = ScheduleStarDTO.builder()
                .starNum(scheduleStar.getStarNum())
                .starScore(scheduleStar.getStarScore())
                .sno(scheduleStar.getSchedule().getSno())
                .email(scheduleStar.getMember().getEmail())
                .build();

        return scheduleStarDTO;
    }

    List<ScheduleStarDTO> getScheduleStarList(Long sno);

    long registerStar(ScheduleStarDTO scheduleStarDTO);

    ScheduleStarDTO getStarMember(String email, Long sno);

    void modify(ScheduleStarDTO scheduleStarDTO);
}
