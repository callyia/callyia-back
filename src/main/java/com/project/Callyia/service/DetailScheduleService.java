package com.project.Callyia.service;

import com.project.Callyia.dto.DetailScheduleDTO;
import com.project.Callyia.dto.ScheduleDTO;
import com.project.Callyia.entity.DetailSchedule;
import com.project.Callyia.entity.Schedule;

import java.util.List;

public interface DetailScheduleService {

    //dtoToEntity
    default DetailSchedule dtoToEntity(DetailScheduleDTO detailScheduleDTO) {
        Schedule schedule = Schedule.builder().sno(detailScheduleDTO.getSno()).build();

        DetailSchedule detailSchedule = DetailSchedule.builder()
                .dno(detailScheduleDTO.getDno())
                .place(detailScheduleDTO.getPlace())
                .content(detailScheduleDTO.getContent())
                .place_id(detailScheduleDTO.getPlace_id())
                .detailImages(detailScheduleDTO.getDetailImages())
                .day(detailScheduleDTO.getDay())
                .sno(schedule)
                .build();
        return detailSchedule;
    }

    //entityToDTO
    default DetailScheduleDTO entityToDTO(DetailSchedule detailSchedule, Schedule schedule) {
        DetailScheduleDTO detailScheduleDTO = DetailScheduleDTO.builder()
                .dno(detailSchedule.getDno())
                .place(detailSchedule.getPlace())
                .content(detailSchedule.getContent())
                .place_id(detailSchedule.getPlace_id())
                .detailImages(detailSchedule.getDetailImages())
                .day(detailSchedule.getDay())
                .sno(schedule.getSno())
                .build();
        return detailScheduleDTO;
    }

    //디테일스케줄 조회
    List<DetailScheduleDTO> getDetailSchedule(Long sno);
}