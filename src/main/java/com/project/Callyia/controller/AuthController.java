package com.project.Callyia.controller;

import com.project.Callyia.dto.MemberDTO;
import com.project.Callyia.security.dto.AuthMemberDTO;
import com.project.Callyia.security.jwt.JwtTokenProvider;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Log4j2
@RestController
@RequestMapping("/auth")
public class AuthController {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private JwtTokenProvider jwtTokenProvider;

  @PostMapping("/login")
  public ResponseEntity<Map<String, Object>> login(@RequestBody MemberDTO memberDTO){
    try {

      log.info("실행 AuthController");
      Authentication authentication = authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(memberDTO.getEmail(), memberDTO.getPassword()));
      SecurityContextHolder.getContext().setAuthentication(authentication);

      String token = jwtTokenProvider.generateToken(String.valueOf(authentication));
      AuthMemberDTO authMemberDTO = (AuthMemberDTO) authentication.getPrincipal();
      String email = authMemberDTO.getEmail();
      Collection <? extends GrantedAuthority > authorities = authentication.getAuthorities();
      log.info(">>>>>>>>>>>>>>>>>>>" + authorities);
      log.info(":::::::::::::::" + email);

      Map<String, Object> response = new HashMap<>();
      response.put("token", token);
      response.put("email", email);
      response.put("authorities", authorities);

      return new ResponseEntity<>(response, HttpStatus.OK);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}