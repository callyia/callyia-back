package com.project.Callyia.service;

import com.project.Callyia.dto.MemberDTO;
import com.project.Callyia.entity.Member;
import com.project.Callyia.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
  private final MemberRepository memberRepository;

  @Override
  public String handleMemberRegistration(MemberDTO memberDTO) {
    Member member = dtoToEntity(memberDTO);
    memberRepository.save(member);
    return member.getEmail();
  }

  @Override
  public boolean isEmailExists(String email) {
    return memberRepository.existsByEmail(email);
  }
}
