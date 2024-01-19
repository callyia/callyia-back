package com.project.Callyia.security.service;

import com.project.Callyia.entity.Member;
import com.project.Callyia.repository.MemberRepository;
import com.project.Callyia.security.dto.AuthMemberDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class MemberDetailsService implements UserDetailsService {
  private final MemberRepository memberRepository;
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    log.info("MemberDetailsService : " + username);
    Optional<Member> result = memberRepository.findByEmail(username);

    if(!result.isPresent()) throw new UsernameNotFoundException("유저 없음");
    else {log.info("유저있음");}

    Member member = result.get();
    AuthMemberDTO authMemberDTO = new AuthMemberDTO(
        member.getEmail(),
        member.getPassword(),
        member.getGender(),
        member.getName(),
        member.getNickname(),
        member.getPhone(),
        member.getProfileImage(),
        member.getRoleSet().stream().map(
            role -> new SimpleGrantedAuthority("ROLE_" + role.name())).collect(Collectors.toList())

    );

    authMemberDTO.setName(member.getName());
    log.info(">>> " + authMemberDTO);
    return authMemberDTO;
  }
}
