package com.project.Callyia.service;

import com.project.Callyia.dto.DetailScheduleDTO;
import com.project.Callyia.dto.ScheduleDTO;
import com.project.Callyia.entity.DetailSchedule;
import com.project.Callyia.entity.Schedule;
import com.project.Callyia.entity.Tour;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DetailScheduleService {

    //dtoToEntity 장바구니 때 구현일단 보류
    DetailSchedule dtoToEntity(DetailScheduleDTO detailScheduleDTO);

    //entityToDTO
     DetailScheduleDTO entityToDTO(DetailSchedule detailSchedule);



    //디테일스케줄 조회
    List<DetailScheduleDTO> getFormSno(Long sno);

    List<DetailScheduleDTO> getAllDetailSchedule();

    void saveDetailSchedule(List<DetailScheduleDTO> detailScheduleDTOList, Long sno);


    DetailScheduleDTO findDetailScheduleFirst(Long sno);

    Page<DetailScheduleDTO> getTip(String placeId, Pageable pageable);
}