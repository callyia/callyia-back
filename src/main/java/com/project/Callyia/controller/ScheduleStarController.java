package com.project.Callyia.controller;


import com.project.Callyia.dto.ReplyDTO;
import com.project.Callyia.dto.ScheduleStarDTO;
import com.project.Callyia.service.ScheduleStarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@Controller
@RequiredArgsConstructor
@RequestMapping("/Star")
public class ScheduleStarController {
    private final ScheduleStarService scheduleStarService;

    @GetMapping(value = "/getStar")
    public ResponseEntity<List<ScheduleStarDTO>> getStar(@RequestParam Long sno) {
        List<ScheduleStarDTO> scheduleStarDTOList = scheduleStarService.getScheduleStarList(sno);

        return new ResponseEntity<>(scheduleStarDTOList, HttpStatus.OK);
    }

    @PostMapping("/registerStar")
    public ResponseEntity<Long> registerStar(@RequestBody ScheduleStarDTO scheduleStarDTO) {
        Long starNum = scheduleStarService.registerStar(scheduleStarDTO);
        return new ResponseEntity<>(starNum, HttpStatus.OK);
    }

    @GetMapping(value = "/getStarMember")
    public ResponseEntity<ScheduleStarDTO> getStarMember(@RequestParam String email, @RequestParam Long sno) {
        ScheduleStarDTO scheduleStarDTO = scheduleStarService.getStarMember(email, sno);

        return new ResponseEntity<>(scheduleStarDTO, HttpStatus.OK);
    }



    @PutMapping("/modifyStar")
    public ResponseEntity<Long> modifyStar(@RequestBody ScheduleStarDTO scheduleStarDTO) {
        scheduleStarService.modify(scheduleStarDTO);
        return new ResponseEntity<>(scheduleStarDTO.getStarNum(), HttpStatus.OK);
    }
}
