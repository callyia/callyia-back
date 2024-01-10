package com.project.Callyia.service;

import com.project.Callyia.dto.DetailScheduleDTO;
import com.project.Callyia.entity.DetailSchedule;
import com.project.Callyia.entity.Schedule;
import com.project.Callyia.repository.DetailScheduleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class DetailScheduleServiceImpl implements DetailScheduleService{
    private final DetailScheduleRepository detailScheduleRepository;

    @Override
    public List<DetailScheduleDTO> getDetailSchedule(Long sno) {
        List<Object[]> resultList = detailScheduleRepository.getDetailScheduleBySno(sno);
        List<DetailScheduleDTO> detailScheduleDTOs = new ArrayList<>();

        for (Object[] arr : resultList) {
            DetailScheduleDTO detailScheduleDTO = entityToDTO((DetailSchedule) arr[0], (Schedule) arr[1]);
            detailScheduleDTOs.add(detailScheduleDTO);
        }

        return detailScheduleDTOs;

    }
}