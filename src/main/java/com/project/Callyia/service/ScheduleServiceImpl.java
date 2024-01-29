package com.project.Callyia.service;

import com.project.Callyia.dto.ScheduleDTO;
import com.project.Callyia.entity.Member;
import com.project.Callyia.entity.Schedule;
import com.project.Callyia.repository.MemberRepository;
import com.project.Callyia.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class ScheduleServiceImpl implements ScheduleService{
    //@RequiredArgsConstructor 어노테이션이 사용되었는데,
    // 이 어노테이션은 클래스에 존재하는 모든 final 필드를 포함한 생성자를 자동으로 생성
    private final ScheduleRepository scheduleRepository;

    private final MemberRepository memberRepository;

//    @Override
//    public Long register(ScheduleDTO scheduleDTO) {
//        Schedule schedule = dtoToEntity(scheduleDTO);
//        scheduleRepository.save(schedule);
//
//        return schedule.getSno();
//    }

    @Override
    public ScheduleDTO getSchedule(Long sno) {
        Object result = scheduleRepository.getScheduleBySno(sno);
        Object[] arr = (Object[]) result;
        return entityToDTO((Schedule) arr[0]);
    }

    @Override
    public Long saveSchedule(ScheduleDTO scheduleDTO) {
        Member member = memberRepository.findByEmail(scheduleDTO.getMember_email()).orElse(null);

        Schedule schedule = Schedule.builder()
            .sName(scheduleDTO.getSName())
            .totalDay(scheduleDTO.getTotal_Day())
            .member(member)
            .build();

        return scheduleRepository.save(schedule).getSno();
    }

    @Override
    public List<ScheduleDTO> getAllSchedule() {
        List<Schedule> scheduleList = scheduleRepository.findAll();
        List<ScheduleDTO> scheduleDTOList = (List<ScheduleDTO>) scheduleList.stream()
                .map(schedule -> entityToDTO(schedule)).collect(Collectors.toList());

        return scheduleDTOList;
    }

    @Override
    public List<ScheduleDTO> getRecentSchedule() {
        List<Schedule> scheduleList = scheduleRepository.findTop6ByOrderByRegDateDesc();
        List<ScheduleDTO> scheduleDTOList = (List<ScheduleDTO>) scheduleList.stream()
            .map(schedule -> entityToDTO(schedule)).collect(Collectors.toList());

        return scheduleDTOList;
    }

    @Override
    public List<ScheduleDTO> getMemberSchedule(String email) {
        List<Schedule> memberScheduleList = scheduleRepository.findAllByMember_email(email);
        List<ScheduleDTO> memberScheduleDTOList = (List<ScheduleDTO>) memberScheduleList.stream()
                .map(schedule -> entityToDTO(schedule)).collect(Collectors.toList());

        return memberScheduleDTOList;
    }

    @Override
    public List<ScheduleDTO> getSchedulesByEmail(String email) {
        List<Schedule> schedules = scheduleRepository.findAllByMember_email(email);

        List<ScheduleDTO> scheduleDTOs = schedules.stream().map(schedule -> entityToDTO(schedule)).collect(Collectors.toList());

        return scheduleDTOs;
    }


}
