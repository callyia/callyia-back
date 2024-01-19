package com.project.Callyia.controller;

import com.project.Callyia.security.util.JwtTokenProvider;
import com.project.Callyia.dto.LoginDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/auth")
public class AuthController {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private JwtTokenProvider jwtTokenProvider;

  @PostMapping("/login")
  public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) throws Exception {

    try {

      Authentication authentication = authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));

      String token = jwtTokenProvider.generateToken(String.valueOf(authentication));
      return new ResponseEntity<>(token, HttpStatus.OK);

    } catch(AuthenticationException authenticationException){
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
    }
  }
}
