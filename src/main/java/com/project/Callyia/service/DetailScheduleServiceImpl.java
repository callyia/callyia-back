package com.project.Callyia.service;

import com.project.Callyia.dto.DetailScheduleDTO;
import com.project.Callyia.entity.DetailSchedule;
import com.project.Callyia.entity.Schedule;
import com.project.Callyia.entity.Tour;
import com.project.Callyia.repository.DetailScheduleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class DetailScheduleServiceImpl implements DetailScheduleService{
    private final DetailScheduleRepository detailScheduleRepository;
//
//    @Override
//    public DetailSchedule dtoToEntity(DetailScheduleDTO detailScheduleDTO) {
//        Schedule schedule = Schedule.builder().sno(detailScheduleDTO.getSno()).build();
//
//        DetailSchedule detailSchedule = DetailSchedule.builder()
//                .dno(detailScheduleDTO.getDno())
//                .content(detailScheduleDTO.getContent())
//                .detailImages(detailScheduleDTO.getDetailImages())
//                .day(detailScheduleDTO.getDay())
//                .schedule(schedule)
//                .build();
//        return detailSchedule;
//    }

    @Override
    public DetailScheduleDTO entityToDTO(DetailSchedule detailSchedule) {
        DetailScheduleDTO detailScheduleDTO = DetailScheduleDTO.builder()
                .dno(detailSchedule.getDno())
                .tip(detailSchedule.getTip())
                .detailImages(detailSchedule.getDetailImages())
                .day(detailSchedule.getDay())
                .sno(detailSchedule.getSchedule().getSno())
                .place_id(detailSchedule.getTour().getPlaceId())
                .build();
        return detailScheduleDTO;
    }




    @Override
    public List<DetailScheduleDTO> getFormSno(Long sno) {
        List<DetailSchedule> detailScheduleList = detailScheduleRepository.findBySchedule_sno(sno);
        List<DetailScheduleDTO> detailScheduleDTOList = (List<DetailScheduleDTO>) detailScheduleList.stream()
                .map(detailSchedule -> entityToDTO(detailSchedule)).collect(Collectors.toList());
        

        return detailScheduleDTOList;

    }
}