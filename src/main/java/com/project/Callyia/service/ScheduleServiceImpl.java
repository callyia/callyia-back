package com.project.Callyia.service;

import com.project.Callyia.dto.ScheduleDTO;
import com.project.Callyia.entity.DetailSchedule;
import com.project.Callyia.entity.Member;
import com.project.Callyia.entity.Schedule;
import com.project.Callyia.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class ScheduleServiceImpl implements ScheduleService{
    //@RequiredArgsConstructor 어노테이션이 사용되었는데,
    // 이 어노테이션은 클래스에 존재하는 모든 final 필드를 포함한 생성자를 자동으로 생성
    private final ScheduleRepository scheduleRepository;

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
        return entityToDTO((Schedule) arr[0], (Member) arr[1]);
    }

}
