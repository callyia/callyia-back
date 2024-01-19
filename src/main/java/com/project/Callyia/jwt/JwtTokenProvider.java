package com.project.Callyia.security.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.util.Date;

@Component
@Log4j2
public class JwtTokenProvider {

  @Value("${jwt.secret}")
  private String jwtSecret;

  @Value("${jwt.expiration}")
  private int jwtExpiration;

  //JWT 토큰 생성
  public String generateToken(String content) throws Exception {
    return Jwts.builder()
        .issuedAt(new Date())//토큰 발행일
        .expiration(Date.from(ZonedDateTime.now().plusMinutes(jwtExpiration).toInstant())) // 토큰 만료일
        .claim("sub", content) //토큰에 저장되는 클레임
        .signWith(Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8))) //서명 키 설정
        .compact(); // 토큰을 문자열로 변환
  }

  //JWT 토큰 유효성 검사 및 정보 추출
  public String validateAndExtract(String token) throws Exception {
    log.info("Jwts getClass: " +
        Jwts.parser().verifyWith(Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8)))
            .build().parse(token));

    // 토큰 유효성 검사 후 클레임 추출
    Claims claims = (Claims) Jwts.parser().verifyWith(Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8)))
        .build().parse(token).getPayload();
    return (String) claims.get("sub");
  }
}