package com.project.Callyia.service;

import com.project.Callyia.dto.MemberDTO;
import com.project.Callyia.dto.ScheduleDTO;
import com.project.Callyia.entity.DetailSchedule;
import com.project.Callyia.entity.Member;
import com.project.Callyia.entity.Schedule;

import java.util.List;

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
    default ScheduleDTO entityToDTO(Schedule schedule){
        ScheduleDTO scheduleDTO = ScheduleDTO.builder()
                .sno(schedule.getSno())
                .sName(schedule.getSName())
                .total_Day(schedule.getTotalDay())
                .member_email(schedule.getMember().getEmail())
                .member_nickname(schedule.getMember().getNickname())
                .member_profile_image(schedule.getMember().getProfileImage())
                .regDate(schedule.getRegDate())
                .modDate(schedule.getModDate())
                .build();
        return scheduleDTO;
    }

    //스케줄 조회
    ScheduleDTO getSchedule(Long sno);

    public Long saveSchedule(ScheduleDTO scheduleDTO);

    List<ScheduleDTO> getAllSchedule();

    List<ScheduleDTO> getRecentSchedule();

    List<ScheduleDTO> getMemberSchedule(String email);

    List<ScheduleDTO> getSchedulesByEmail(String email);

    int getScheduleCountByEmail(String email);
}
