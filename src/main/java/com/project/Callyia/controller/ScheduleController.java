package com.project.Callyia.controller;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.project.Callyia.dto.DetailScheduleDTO;
import com.project.Callyia.dto.ScheduleDTO;

import com.project.Callyia.service.DetailScheduleService;
import com.project.Callyia.service.ScheduleService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@Controller
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/Schedule")
public class ScheduleController {
    private final ScheduleService scheduleService;
    private final DetailScheduleService detailScheduleService;
    @Setter
    @Getter
    @JsonSerialize
    public class ScheduleAndDetailDTO {
        private ScheduleDTO scheduleDTO;
        private List<DetailScheduleDTO> detailScheduleDTOList;
    }


    @GetMapping(value = "/posting", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ScheduleAndDetailDTO> posting(@RequestParam("sno") long sno){
        ScheduleDTO scheduleDTO = scheduleService.getSchedule(sno);
        List<DetailScheduleDTO> detailScheduleDTOList = detailScheduleService.getDetailSchedule(sno);

        ScheduleAndDetailDTO responseDTO = new ScheduleAndDetailDTO();
        responseDTO.setScheduleDTO(scheduleDTO);
        responseDTO.setDetailScheduleDTOList(detailScheduleDTOList);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
