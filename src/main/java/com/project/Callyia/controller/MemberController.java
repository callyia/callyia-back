package com.project.Callyia.controller;

import com.project.Callyia.dto.*;
import com.project.Callyia.entity.DetailSchedule;
import com.project.Callyia.entity.Member;
import com.project.Callyia.entity.Plan;
import com.project.Callyia.entity.Schedule;
import com.project.Callyia.repository.MemberRepository;
import com.project.Callyia.service.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
  private final MemberRepository memberRepository;
  private final MemberService memberService;
  private final ScheduleService scheduleService;
  private final DetailScheduleService detailScheduleService;
  private final PlanService planService;
  private final BasketService basketService;
  private final PlanDetailService planDetailService;
  private final ReplyService replyService;

  @PostMapping("/auth")
  public ResponseEntity<String> auth(@RequestBody MemberDTO memberDTO){
    try{
      if (memberService.isEmailExists(memberDTO.getEmail()) || memberService.isPhoneExists(memberDTO.getPhone()) ||memberService.isNicknameExists(memberDTO.getNickname())){
        return new ResponseEntity<>(HttpStatus.CONFLICT);
      }
      String email = memberService.handleMemberRegistration(memberDTO);
      return new ResponseEntity<>(email, HttpStatus.OK);
    } catch (Exception e){
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PostMapping("/upload")
  public ResponseEntity<Map<String, String>> handleProfileImageUpload(@RequestParam("profileImage")MultipartFile file) {
    try {
      String imagePathDummy = "https://thumb.mtstarnews.com/06/2023/06/2023061710001286729_1.jpg/dims/optimize";
      Map<String, String> responseMap = new HashMap<>();
      responseMap.put("imagePath", imagePathDummy);
      return ResponseEntity.ok(responseMap);
    } catch (Exception e){
      log.error(">>>>>>>>>>>>>>>>>>>>>>>>>>>", e);
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/user")
  public ResponseEntity<MemberDTO> getMember(@RequestParam String email) {
    MemberDTO memberDTO = memberService.getMember(email);

    return new ResponseEntity<>(memberDTO, HttpStatus.OK);
  }
  @GetMapping("/getNickname")
  public ResponseEntity<MemberDTO> getNickname(@RequestParam String nickname) {
    MemberDTO memberDTO = memberService.getNickname(nickname);

    return new ResponseEntity<>(memberDTO, HttpStatus.OK);
  }

  @GetMapping("/getPhone")
  public ResponseEntity<MemberDTO> getPhone(@RequestParam String phone) {
    MemberDTO memberDTO = memberService.getPhone(phone);

    return new ResponseEntity<>(memberDTO, HttpStatus.OK);
  }
  @GetMapping("/getAll")
  public ResponseEntity<List<MemberDTO>> getAllMember() {
    List<MemberDTO> memberDTOList = memberService.getAllMember();

    return new ResponseEntity<>(memberDTOList, HttpStatus.OK);
  }

  @GetMapping("/getMember")
  public ResponseEntity<MemberRequestDTO> getMemberProfile(@RequestParam String email) {
    log.info("email: " + email);

    MemberDTO memberDTO = memberService.getMember(email);
    memberDTO.setPassword(null);
    List<ScheduleDTO> scheduleDTOs = scheduleService.getSchedulesByEmail(email);
    List<Long> snoList = scheduleDTOs.stream().map(scheduleDTO -> scheduleDTO.getSno()).collect(Collectors.toList());
    log.info(snoList);

    List<String> imageList = snoList.stream().map(sno ->
      detailScheduleService.findDetailScheduleFirst(sno).getDetailImages()
    ).collect(Collectors.toList());


    List<ScheduleThumbnailRequestDTO> scheduleThumbnailRequestDTOs = new ArrayList<>();
    for(int i=0;i<scheduleDTOs.size();i++) {
      scheduleThumbnailRequestDTOs.add(new ScheduleThumbnailRequestDTO(scheduleDTOs.get(i), imageList.get(i)));
    }

    for(ScheduleThumbnailRequestDTO s : scheduleThumbnailRequestDTOs) {
      log.info(s.getScheduleDTO());
      log.info(s.getImage());
    }


    MemberRequestDTO memberRequestDTO = new MemberRequestDTO(memberDTO, scheduleThumbnailRequestDTOs);



    return new ResponseEntity<>(memberRequestDTO, HttpStatus.OK);
  }

  @GetMapping("/getMemberPlan")
  public ResponseEntity<List<PlanDTO>> getMemberPlanList(@RequestParam String email) {
    List<PlanDTO> planDTOs = planService.getFromEmail(email);

    return new ResponseEntity<>(planDTOs, HttpStatus.OK);
  }


  @Transactional
  @DeleteMapping("/deleteMember")
  public ResponseEntity<MemberDTO> deleteMember(@RequestParam String email) {
    basketService.deleteBasketByEmail(email);
    replyService.deleteReplyByEmail(email);
    List<Schedule> scheduleList = scheduleService.findByMemberEmail(email);
    for (Schedule schedule : scheduleList) {
      detailScheduleService.deleteDetailScheduleBySno(schedule.getSno());
    }
    for (Schedule schedule : scheduleList) {
      scheduleService.deleteScheduleBySno(schedule.getSno());
    }
    List<Plan> planList = planService.findByMemberEmail(email);
    for (Plan plan : planList) {
      planDetailService.deleteByPno(plan.getPno());
    }
    for (Plan plan : planList) {
      planService.deletePlan(plan.getPno());
    }
    memberService.deleteMember(email);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/getUser")
  public ResponseEntity<MemberDTO> getUser(@RequestParam String email) {
    MemberDTO memberDTO = memberService.getMember(email);

    return new ResponseEntity<>(memberDTO, HttpStatus.OK);
  }

  @PutMapping("/updateMember")
  public ResponseEntity<MemberDTO> PutMemberPage(@RequestBody MemberDTO memberDTO) {
    MemberDTO updateMemberDTO = memberService.updateMember(memberDTO);

    return new ResponseEntity<>(memberDTO, HttpStatus.OK);
  }

  @PutMapping("/modify")
  public ResponseEntity<?> modify(@RequestBody MemberDTO memberDTO, @RequestParam String email) {
    try{
      Map<String, String> responseMap = new HashMap<>();

      if (!memberService.isNoModifyPhone(memberDTO.getPhone(), email)) {
        if(memberService.isPhoneExists(memberDTO.getPhone())) {
          responseMap.put("phone", "Phone number already exists");
        }
      }
      if (!memberService.isNoModifyNickname(memberDTO.getNickname(), email)) {
        if(memberService.isNicknameExists(memberDTO.getNickname())){
          responseMap.put("nickname", "Nickname already exists");
        }
      }

      if (!responseMap.isEmpty()) {
        return new ResponseEntity<>(responseMap, HttpStatus.CONFLICT);
      }

      MemberDTO modifyMemberDTO = memberService.modifyMember(memberDTO);
      return  new ResponseEntity<>(modifyMemberDTO, HttpStatus.OK);
    } catch (Exception e){
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }

  @PutMapping("/modifyPassword")
  public ResponseEntity<String> modifyPassword(@RequestBody MemberDTO memberDTO) {
    memberService.modify(memberDTO);
    return new ResponseEntity<>(memberDTO.getEmail(), HttpStatus.OK);
  }
}
