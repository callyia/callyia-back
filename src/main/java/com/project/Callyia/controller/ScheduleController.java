package com.project.Callyia.controller;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.project.Callyia.dto.*;

import com.project.Callyia.entity.Reply;
import com.project.Callyia.service.*;
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
@RequestMapping("/Schedule")
public class ScheduleController {
    private final ScheduleService scheduleService;
    private final DetailScheduleService detailScheduleService;
    private final TourService tourService;
    private final ReplyService replyService;
    private final MemberService memberService;

    @Setter
    @Getter
    @JsonSerialize
    public class postingDTO {
        private ScheduleDTO scheduleDTO;
        private List<DetailScheduleDTO> detailScheduleDTOList;
        private List<ReplyDTO> replyDTOList;
        private List<TourDTO> tourDTOList;
        private MemberDTO memberDTO;
        private List<MemberDTO> memberDTOList;
    }

    @Setter
    @Getter
    @JsonSerialize
    public class scheduleNicknameDTO {
        private List<ScheduleDTO> scheduleDTOList;
        private List<MemberDTO> memberDTOList;
    }

    @GetMapping(value = "/posting", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<postingDTO> posting(@RequestParam long sno){
        ScheduleDTO scheduleDTO = scheduleService.getSchedule(sno);
        MemberDTO memberDTO = memberService.getMember(scheduleDTO.getMember_email());
        List<DetailScheduleDTO> detailScheduleDTOList = detailScheduleService.getFormSno(sno);
        List<ReplyDTO> replyDTOList = new ArrayList<>();
        List<MemberDTO> memberDTOList = memberService.getAllMember();

        detailScheduleDTOList.forEach(detailScheduleDTO -> {
            long dno = detailScheduleDTO.getDno();
            List<ReplyDTO> repliesForDno = replyService.getFormDno(dno);
            replyDTOList.addAll(repliesForDno);  // 현재 dno에 해당하는 replyDTO를 전체 리스트에 추가
        });


        List<TourDTO> tourDTOList = (List<TourDTO>) detailScheduleDTOList.stream()
                .map(detailScheduleDTO -> tourService.detailScheduleToTour(detailScheduleDTO)).collect(Collectors.toList());


        postingDTO responseDTO = new postingDTO();
        responseDTO.setScheduleDTO(scheduleDTO);
        responseDTO.setDetailScheduleDTOList(detailScheduleDTOList);
        responseDTO.setReplyDTOList(replyDTOList);
        responseDTO.setTourDTOList(tourDTOList);
        responseDTO.setMemberDTO(memberDTO);
        responseDTO.setMemberDTOList(memberDTOList);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/getAllSchedule")
    public ResponseEntity<List<ScheduleDTO>> getAllSchedule() {
        List<ScheduleDTO> scheduleDTOList = scheduleService.getAllSchedule();

        return new ResponseEntity<>(scheduleDTOList, HttpStatus.OK);
    }

    @GetMapping("/getMemberSchedule")
    public ResponseEntity<List<ScheduleDTO>> getMemberSchedule(String email) {
        List<ScheduleDTO> scheduleDTOList = scheduleService.getMemberSchedule(email);

        return new ResponseEntity<>(scheduleDTOList, HttpStatus.OK);
    }

    @GetMapping("/getDetailSchedule")
    public ResponseEntity<List<DetailScheduleDTO>> getAllDetailSchedule() {
        List<DetailScheduleDTO> detailScheduleDTOList = detailScheduleService.getAllDetailSchedule();

        return new ResponseEntity<>(detailScheduleDTOList, HttpStatus.OK);
    }

    //댓글 새로고침
    @GetMapping("/update")
    public ResponseEntity<List<ReplyDTO>> updateReply(@RequestParam long dno) {
        // dno에 해당하는 댓글 목록을 가져오는 서비스 메서드 호출
        List<ReplyDTO> updatedReplyList = replyService.getFormDno(dno);
        return new ResponseEntity<>(updatedReplyList, HttpStatus.OK);
    }

    //댓글 등록
    @PostMapping("/register")
    public ResponseEntity<Long> registerReply(@RequestBody ReplyDTO replyDTO) {
        Long rno = replyService.register(replyDTO);
        return new ResponseEntity<>(rno, HttpStatus.OK);
    }


    //댓글 수정
    @PutMapping("/modify")
    public ResponseEntity<Long> modifyReply(@RequestBody ReplyDTO replyDTO) {
        replyService.modify(replyDTO);
        return new ResponseEntity<>(replyDTO.getRno(), HttpStatus.OK);
    }


//    //댓글 삭제
    @DeleteMapping("delete/{rno}")
    public ResponseEntity<String> removeReply(@PathVariable("rno") Long rno) {
        replyService.remove(rno);
        return new ResponseEntity<>(rno+"", HttpStatus.OK);
    }

//    //장바구니 추가
//    @PostMapping("basket")
//    public ResponseEntity<Long> addBasket(@RequestBody BasketDTO basketDTO) {
//
//    }


    //장바구니 삭제


}
