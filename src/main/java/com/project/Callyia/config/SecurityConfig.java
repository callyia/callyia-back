package com.project.Callyia.config;

import com.project.Callyia.security.service.MemberDetailsService;
//import com.project.Callyia.service.MemberDetailService;
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
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;

@Configuration
@EnableWebSecurity
@Log4j2
public class SecurityConfig {

    @Autowired
    private MemberDetailsService memberDetailsService;

    // 액세스를 허용하는 주소들을 등록
    private static final String[] AUTH_WHITELIST = {
        "/", "/sample/all", "/auth/login", "/auth/logout", "/auth/accessDenied",
        "/notes/", "/notes/all",
    };

    @Bean
        // 암호화시키기 위한 빈
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
            httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(memberDetailsService);
        return authenticationManagerBuilder.build();
    }

    @Bean // security 설정, 5.7.x부터 @Bean으로 등록해서 사용(리턴 타입 SecurityFilterChain)
    protected SecurityFilterChain config(HttpSecurity httpSecurity) throws Exception {
        // httpSecurity의 http로 url을 요구할 때 권한을 매치하는 곳
        httpSecurity.authorizeHttpRequests(auth -> {
            log.info("auth>>" + auth);
            auth.requestMatchers(AUTH_WHITELIST).permitAll() // 시큐리티 없이 접근 가능하도록 등록
                .requestMatchers("/sample/admin").hasRole("ADMIN")
                .requestMatchers("/sample/member").access(
                    // 복수개의 권한을 등록할 때
                    new WebExpressionAuthorizationManager("hasRole('ADMIN') or hasRole('MANAGER')"))
                //new WebExpressionAuthorizationManager("hasAnyRole('ADMIN','MEMBER')")
                .anyRequest().permitAll(); // 그외는 모두 접근 금지
        });

        httpSecurity.logout(new Customizer<LogoutConfigurer<HttpSecurity>>() {
            @Override
            public void customize(LogoutConfigurer<HttpSecurity> httpSecurityLogoutConfigurer) {
                httpSecurityLogoutConfigurer
                    .logoutUrl("/auth/logout") // csrf사용시 form의 post와 action주소와 "/auth/logout" 일치!
                    .logoutSuccessUrl("/")
//                    .logoutSuccessHandler(customLogoutSuccessHandler())
                    .invalidateHttpSession(true);
            }
        });
        httpSecurity.csrf(new Customizer<CsrfConfigurer<HttpSecurity>>() {
            @Override  // 서버에 인증정보를 저장하지 않기 때문에 csrf를 사용하지 않는다.
            public void customize(CsrfConfigurer<HttpSecurity> httpSecurityCsrfConfigurer) {
                // disable이 안된 상태에서는 logout도 반드시 post방식으로 처리가 되어야 함.
                httpSecurityCsrfConfigurer.disable();  //disable하면 logout을 get으로 접근해도 처리가 됨.
            }
        });

        return httpSecurity.build();
    }

}
