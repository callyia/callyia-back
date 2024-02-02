package com.project.Callyia.config;

import com.project.Callyia.security.filter.ApiCheckFilter;
import com.project.Callyia.security.jwt.JwtTokenProvider;
import com.project.Callyia.security.service.MemberDetailService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;

@Configuration
@EnableWebSecurity
@Log4j2
public class SecurityConfig {

    @Autowired
    private MemberDetailService memberDetailService;

    // 액세스를 허용하는 주소들을 등록
    private static final String[] AUTH_WHITELIST = {
        "/", "/auth/login", "/member/auth", "/member/upload", "/member/deleteMember", "/Tour/all", "/Tour/search", "/planning/search","/basket",
        "/Tour",
        "/Tour/upload/**",
        "/planning/getDB",
        "/planning/getDay",
        "/planning/getPlan",
        "/planning/save",
        "/planning/update",
        "/planning/post",
        "/Schedule/posting",
        "/Schedule/register",
        "/Schedule/modify",
        "/Schedule/delete",
        "/s3/profile/**",
    };

    private static final String[] USER_AUTH_WHITELIST = {
        "/basket",
        "/Tour",
        "/Tour/upload",
        "/planning/getDB",
        "/planning/getDay",
        "/planning/getPlan",
        "/planning/save",
        "/planning/update",
        "/planning/post",
        "/Schedule/posting",
        "/Schedule/register",
        "/Schedule/modify",
        "/Schedule/delete",
    };

    @Bean
    // 암호화시키기 위한 빈
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean // security 설정, 5.7.x부터 @Bean으로 등록해서 사용(리턴 타입 SecurityFilterChain)
    protected SecurityFilterChain config(HttpSecurity httpSecurity) throws Exception {
        // httpSecurity의 http로 url을 요구할 때 권한을 매치하는 곳
        httpSecurity.authorizeHttpRequests(auth -> {
            log.info("auth>>" + auth);
            auth.requestMatchers(AUTH_WHITELIST).permitAll() // 시큐리티 없이 접근 가능하도록 등록
                .anyRequest().permitAll(); // 그외는 모두 접근 금지
        });
        httpSecurity.csrf(new Customizer<CsrfConfigurer<HttpSecurity>>() {
            @Override  // 서버에 인증정보를 저장하지 않기 때문에 csrf를 사용하지 않는다.
            public void customize(CsrfConfigurer<HttpSecurity> httpSecurityCsrfConfigurer) {
                // disable이 안된 상태에서는 logout도 반드시 post방식으로 처리가 되어야 함.
                httpSecurityCsrfConfigurer.disable();  //disable하면 logout을 get으로 접근해도 처리가 됨.
            }
        });
        // 로그인 후에도 해당 패턴에 접속할 때 토큰이 유효한지 확인하기 위함
        // 확인 안하면 로그인 하여 토큰 발행하여 LocalStorage에 토큰이 있어도 요청 거부됨
        httpSecurity.addFilterBefore(apiCheckFilter(), UsernamePasswordAuthenticationFilter.class);
        //httpSecurity.addFilterBefore(apiLoginFilter(authenticationManager(httpSecurity)), UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }


    @Bean
    public JwtTokenProvider jwtTokenProvider() { return new JwtTokenProvider();}
    @Bean
    public ApiCheckFilter apiCheckFilter() {
        return new ApiCheckFilter(List.of(USER_AUTH_WHITELIST), jwtTokenProvider());
    }


   /* @Bean
   public ApiLoginFilter apiLoginFilter(AuthenticationManager authenticationManager) throws Exception {
        ApiLoginFilter apiLoginFilter = new ApiLoginFilter("/auth/login", jwtTokenProvider());
        apiLoginFilter.setAuthenticationManager(authenticationManager);
        apiLoginFilter.setAuthenticationFailureHandler(new ApiLoginFailHandler());
        return apiLoginFilter;
    }*/

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
            httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(memberDetailService);
        return authenticationManagerBuilder.build();
    }

}
