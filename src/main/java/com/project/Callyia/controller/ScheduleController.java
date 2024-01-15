package com.project.Callyia.controller;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.project.Callyia.dto.DetailScheduleDTO;
import com.project.Callyia.dto.ReplyDTO;
import com.project.Callyia.dto.ScheduleDTO;

import com.project.Callyia.dto.TourDTO;
import com.project.Callyia.service.DetailScheduleService;
import com.project.Callyia.service.ReplyService;
import com.project.Callyia.service.ScheduleService;
import com.project.Callyia.service.TourService;
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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Controller
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/Schedule")
public class ScheduleController {
    private final ScheduleService scheduleService;
    private final DetailScheduleService detailScheduleService;
    private final TourService tourService;
    private final ReplyService replyService;

    @Setter
    @Getter
    @JsonSerialize
    public class postingDTO {
        private ScheduleDTO scheduleDTO;
        private List<DetailScheduleDTO> detailScheduleDTOList;
        private List<ReplyDTO> replyDTOList;
//        private List<TourDTO> tourDTOList;
    }


    @GetMapping(value = "/posting", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<postingDTO> posting(@RequestParam long sno){
        ScheduleDTO scheduleDTO = scheduleService.getSchedule(sno);
        List<DetailScheduleDTO> detailScheduleDTOList = detailScheduleService.getFormSno(sno);
        List<ReplyDTO> replyDTOList = new ArrayList<>();

        detailScheduleDTOList.forEach(detailScheduleDTO -> {
            long dno = detailScheduleDTO.getDno();
            List<ReplyDTO> repliesForDno = replyService.getFormDno(dno);
            replyDTOList.addAll(repliesForDno);  // 현재 dno에 해당하는 replyDTO를 전체 리스트에 추가
        });


        //TourDTO는 굳이 필요없을 수 있으나 혹시 모르니 일단 주석처리, 필요없을 시 관련 함수 TourService, TourServiceImpl에서 삭제
//        List<TourDTO> tourDTOList = (List<TourDTO>) detailScheduleDTOList.stream()
//                .map(detailScheduleDTO -> tourService.detailScheduleToTour(detailScheduleDTO)).collect(Collectors.toList());


        postingDTO responseDTO = new postingDTO();
        responseDTO.setScheduleDTO(scheduleDTO);
        responseDTO.setDetailScheduleDTOList(detailScheduleDTOList);
        responseDTO.setReplyDTOList(replyDTOList);
//        responseDTO.setTourDTOList(tourDTOList);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }



    //댓글 등록
    @PostMapping("register")
    public ResponseEntity<Long> registerReply(@RequestBody ReplyDTO replyDTO) {
        Long rno = replyService.register(replyDTO);
        return new ResponseEntity<>(rno, HttpStatus.OK);
    }


    //댓글 수정
    @PutMapping("modify")
    public ResponseEntity<Long> modifyReply(@RequestBody ReplyDTO replyDTO) {
        replyService.modify(replyDTO);
        return new ResponseEntity<>(replyDTO.getRno(), HttpStatus.OK);
    }


    //댓글 삭제
    @DeleteMapping("delete")
    public ResponseEntity<String> removeReply(@PathVariable("rno") Long rno) {
        replyService.remove(rno);
        return new ResponseEntity<>(rno+"", HttpStatus.OK);
    }



}
