package com.project.Callyia.service;

import com.project.Callyia.dto.DetailScheduleDTO;
import com.project.Callyia.dto.MemberDTO;
import com.project.Callyia.entity.Member;
import com.project.Callyia.entity.Plan;
import com.project.Callyia.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
  private final MemberRepository memberRepository;
  private final BCryptPasswordEncoder passwordEncoder;

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
  public boolean isNicknameExists(String nickname) {
    return memberRepository.existsByNickname(nickname);
  }

  @Override
  public boolean isPhoneExists(String phone) {
    return memberRepository.existsByPhone(phone);
  }

  @Override
  public MemberDTO getMember(String email) {
    Member member = memberRepository.findByEmail(email).orElse(null);
    return entityToDTO(member);
  }

  @Override
  public MemberDTO updateMember(MemberDTO memberDTO) {
    Member member = memberRepository.findById(memberDTO.getEmail())
            .orElseThrow(() -> new RuntimeException("멤버 없음"));

    member.setProfileImage(memberDTO.getProfileImage());
    member.setAboutMe(memberDTO.getAboutMe());
    member = memberRepository.save(member);
    return toMemberDTO(member);
  }

  private MemberDTO toMemberDTO(Member member) {
    return MemberDTO.builder()
            .email(member.getEmail())
            .password(member.getPassword())
            .gender(member.getGender())
            .name(member.getName())
            .nickname(member.getNickname())
            .phone(member.getPhone())
            .profileImage(member.getProfileImage())
            .aboutMe(member.getAboutMe())
            .joinDate(member.getJoinDate())
            .build();
  }

  @Override
  public List<MemberDTO> getAllMember() {
    List<Member> memberList = memberRepository.findAll();
    List<MemberDTO> memberDTOList = (List<MemberDTO>) memberList.stream()
            .map(member -> entityToDTO(member)).collect(Collectors.toList());
    return memberDTOList;
  }


  @Override
  public void deleteMember(String email) {
    Member member = memberRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("삭제할 회원이 존재하지 않습니다.."));
    memberRepository.delete(member);
  }

  @Override
  public MemberDTO modifyMember(MemberDTO memberDTO) {
    Member member = memberRepository.findById(memberDTO.getEmail())
        .orElseThrow(() -> new RuntimeException("멤버 없음"));

    member.setNickname(memberDTO.getNickname());
    member.setName(memberDTO.getName());
    member.setPhone(memberDTO.getPhone());
    member = memberRepository.save(member);
    return entityToDTO(member);
  }


  @Override
  public MemberDTO getNickname(String nickname) {
    Member member = memberRepository.findByNickname(nickname).orElse(null);
    return entityToDTO(member);
  }

  @Override
  public MemberDTO getPhone(String phone) {
    Member member = memberRepository.findByPhone(phone).orElse(null);
    return entityToDTO(member);
  }

  @Override
  public void modify(MemberDTO memberDTO) {
    Member member = memberRepository.findByEmail(memberDTO.getEmail()).orElse(null);

    member.setPassword(passwordEncoder.encode(memberDTO.getPassword()));
//    member.setPassword(memberDTO.getPassword());
    memberRepository.save(member);
  }
}
