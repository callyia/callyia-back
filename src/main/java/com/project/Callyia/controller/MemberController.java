package com.project.Callyia.controller;

import com.project.Callyia.dto.MemberDTO;
import com.project.Callyia.entity.Member;
import com.project.Callyia.repository.MemberRepository;
import com.project.Callyia.service.MemberService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
  private final MemberRepository memberRepository;
  private final MemberService memberService;

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
}
