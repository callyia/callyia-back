package com.project.Callyia.service;

import com.project.Callyia.dto.ScheduleDTO;
import com.project.Callyia.entity.DetailSchedule;
import com.project.Callyia.entity.Member;
import com.project.Callyia.entity.Schedule;

public interface ScheduleService {
//    Long register(ScheduleDTO scheduleDTO);

    //dtoToEntity
    default Schedule dtoToEntity(ScheduleDTO scheduleDTO){
        Member member = Member.builder().email(scheduleDTO.getMember_email()).build();

        Schedule schedule= Schedule.builder()
                .sName(scheduleDTO.getSName())
                .totalDay(scheduleDTO.getTotal_Day())
                .member(member)
                .build();
        return schedule;
    }

    //entityToDTO
    default ScheduleDTO entityToDTO(Schedule schedule, Member member){
        ScheduleDTO scheduleDTO = ScheduleDTO.builder()
                .sno(schedule.getSno())
                .sName(schedule.getSName())
                .total_Day(schedule.getTotalDay())
                .member_email(member.getEmail())
                .build();
        return scheduleDTO;
    }

    //스케줄 조회
    ScheduleDTO getSchedule(Long sno);
}
