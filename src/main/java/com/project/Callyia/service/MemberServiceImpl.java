package com.project.Callyia.service;

import com.project.Callyia.dto.DetailScheduleDTO;
import com.project.Callyia.dto.MemberDTO;
import com.project.Callyia.entity.Member;
import com.project.Callyia.entity.Plan;
import com.project.Callyia.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

  @Override
  public MemberDTO getMember(String email) {
    Member member = memberRepository.findByEmail(email).orElse(null);
    return entityToDTO(member);
  }

  @Override
  public List<MemberDTO> getAllMember() {
    List<Member> memberList = memberRepository.findAll();
    List<MemberDTO> memberDTOList = (List<MemberDTO>) memberList.stream()
            .map(member -> entityToDTO(member)).collect(Collectors.toList());
    return memberDTOList;
  }
}
