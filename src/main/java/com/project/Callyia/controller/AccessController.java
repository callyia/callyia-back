package com.project.Callyia.controller;

import com.project.Callyia.security.dto.AuthMemberDTO;
import com.project.Callyia.service.PlanService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/access")
public class AccessController {
  private final PlanService planService;

  @GetMapping("/resource-by-pno/{pno}")
  public ResponseEntity<String> getResourceByPno(@PathVariable Long pno, Authentication authentication) {
    String author = planService.getFromPno(pno).getUserId();

    log.info("resource-by-pno");
    log.info(authentication);
//    AuthMemberDTO userDetails = (AuthMemberDTO) authentication.getPrincipal();
//
//    if(author.equals(userDetails.getEmail())) {
//      return ResponseEntity.ok("Access granted");
//    } else {
//      return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied");
//    }

    return null;
  }
}
