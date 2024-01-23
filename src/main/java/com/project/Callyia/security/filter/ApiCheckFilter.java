package com.project.Callyia.security.filter;

import com.project.Callyia.security.jwt.JwtTokenProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.json.simple.JSONObject;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Log4j2
public class ApiCheckFilter extends OncePerRequestFilter {
  private AntPathMatcher antPathMatcher;
  private List<String> patterns;
  private JwtTokenProvider jwtTokenProvider;

  public ApiCheckFilter(List<String> patterns, JwtTokenProvider jwtTokenProvider){
    antPathMatcher = new AntPathMatcher();
    this.patterns = patterns;
    this.jwtTokenProvider = jwtTokenProvider;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    boolean shouldFilter = patterns.stream()
        .anyMatch(pattern -> antPathMatcher.match(request.getContextPath() + pattern, request.getRequestURI()));

    if (shouldFilter) {
      boolean checkHeader = checkAuthHeader(request);
      log.info(checkHeader);


      if(checkHeader){
        filterChain.doFilter(request, response);
        log.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        return;
      } else {
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json;charest=utf-8");
        JSONObject jsonObject = new JSONObject();
        String message = "FAIL CHECK API TOKEN";
        jsonObject.put("code", "403");
        jsonObject.put("message", message);
        PrintWriter printWriter = response.getWriter();
        printWriter.print(jsonObject);
        return;
      }
    }
    filterChain.doFilter(request, response);
  }

  private boolean checkAuthHeader(HttpServletRequest request){
    boolean checkResult = false;
    String authHeader = request.getHeader("Authorization");
    if(StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) {
      try{
        // email이 있다면 권한이 있다고 판단하여 true를 return 한다.
        String email = jwtTokenProvider.validateAndExtract(authHeader.substring(7));
        checkResult = StringUtils.hasText(email);
        log.info("-----------------" + email);
        log.info("=======================" + checkResult);
      } catch (Exception e){
        e.printStackTrace();
      }
    }
    return checkResult;
  }
}
