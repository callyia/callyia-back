package com.project.Callyia.security.service;

import com.project.Callyia.entity.Member;
import com.project.Callyia.repository.MemberRepository;
import com.project.Callyia.security.dto.AuthMemberDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class MemberDetailService implements UserDetailsService {
  private final MemberRepository memberRepository;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

    Optional<Member> result = memberRepository.findById(email);
    log.info(">>>>>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<: " + result);

    if(result.isEmpty()) {
      throw new UsernameNotFoundException("유저 없음");
    }

    Member member = result.get();
    List<GrantedAuthority> authorities = member.getRoleSet().stream()
        .map(role -> new SimpleGrantedAuthority("ROLE_" + role.name()))
        .collect(Collectors.toList());

    AuthMemberDTO authMemberDTO = new AuthMemberDTO(
        member.getEmail(),
        member.getPassword(),
        authorities
    );
    log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + authorities);
    return authMemberDTO;
  }
}
