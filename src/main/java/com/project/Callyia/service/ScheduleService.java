package com.project.Callyia.service;

import com.project.Callyia.dto.ScheduleDTO;
import com.project.Callyia.entity.Member;
import com.project.Callyia.entity.Schedule;

public interface ScheduleService {
//    Long register(ScheduleDTO scheduleDTO);

    //DTO -> Entity
    default Schedule dtoToEntity(ScheduleDTO scheduleDTO){
        Member member = Member.builder().id(scheduleDTO.getMember()).build();

        Schedule schedule= Schedule.builder().sno(scheduleDTO.getSno())
                .sName(scheduleDTO.getSName())
                .totalDay(scheduleDTO.getTotalDay())
                .member(member)
                .build();
        return schedule;
    }

    //Entity -> DTO
    default ScheduleDTO entityToDTO(Schedule schedule, Member member){
        ScheduleDTO scheduleDTO = ScheduleDTO.builder()
                .sno(schedule.getSno())
                .sName(schedule.getSName())
                .totalDay(schedule.getTotalDay())
                .member(member.getId())
                .build();
        return scheduleDTO;
    }

    //스케쥴 조회
    ScheduleDTO get(Long sno);
}
