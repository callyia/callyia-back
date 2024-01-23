package com.project.Callyia.security.filter;/*


package com.project.Callyia.security.filter;

import com.project.Callyia.security.dto.AuthMemberDTO;
import com.project.Callyia.security.jwt.JwtTokenProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import java.io.IOException;

@Log4j2
public class ApiLoginFilter extends AbstractAuthenticationProcessingFilter  {
  private JwtTokenProvider jwtTokenProvider;

  // 생성자와 메서드 재정의가 필수!
  public ApiLoginFilter(String defaultFilterProcessesUrl, JwtTokenProvider jwtUtil) {
    super(defaultFilterProcessesUrl);
    this.jwtTokenProvider = jwtUtil;
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
    log.info("ApiLoginFilter ++++++++++++ attemptAuthentication ++++++++++");
    String email = request.getParameter("email");
    String pw = request.getParameter("password");
    //UsernamePasswordAuthenticationToken의 비밀번호는 평문이다.
    //UserDetailsService에서 가져온 실제 사용자 정보의 비밀번호를 Spring Security가 알아서 처리하기에 따로 암호화 할 필요가 없다.
    UsernamePasswordAuthenticationToken authToken =
        new UsernamePasswordAuthenticationToken(email, pw);
    log.info("authToken: " + authToken);
    return getAuthenticationManager().authenticate(authToken);
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
    log.info("ApiLoginFilter... successfulAuthentication: " + authResult);
    log.info("authResult.getPrincipal(): " + authResult.getPrincipal());
    String email = ((AuthMemberDTO) (authResult.getPrincipal())).getUsername();
    String token = null;
    try {
      token = jwtTokenProvider.generateToken(email);
      response.setContentType("text/plain");
      response.getOutputStream().write(token.getBytes());
      log.info(token);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

 */
