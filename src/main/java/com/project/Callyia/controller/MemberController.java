package com.project.Callyia.controller;

import com.project.Callyia.dto.MemberDTO;
import com.project.Callyia.dto.MemberRequestDTO;
import com.project.Callyia.dto.ScheduleDTO;
import com.project.Callyia.entity.Member;
import com.project.Callyia.repository.MemberRepository;
import com.project.Callyia.service.MemberService;
import com.project.Callyia.service.ScheduleService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
  private final MemberRepository memberRepository;
  private final MemberService memberService;
  private final ScheduleService scheduleService;

  @PostMapping("/auth")
  public ResponseEntity<String> auth(@RequestBody MemberDTO memberDTO){
    try{
      if(memberService.isEmailExists(memberDTO.getEmail())){
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

  @GetMapping("/getAll")
  public ResponseEntity<List<MemberDTO>> getAllMember() {
    List<MemberDTO> memberDTOList = memberService.getAllMember();

    return new ResponseEntity<>(memberDTOList, HttpStatus.OK);
  }

  @GetMapping("getMember")
  public ResponseEntity<MemberRequestDTO> getMemberProfile(@RequestParam String email) {
    log.info("email: " + email);

    MemberDTO memberDTO = memberService.getMember(email);
    memberDTO.setPassword(null);
    List<ScheduleDTO> scheduleDTOs = scheduleService.getSchedulesByEmail(email);
    MemberRequestDTO memberRequestDTO = new MemberRequestDTO(memberDTO, scheduleDTOs);

    return new ResponseEntity<>(memberRequestDTO, HttpStatus.OK);
  }
}
