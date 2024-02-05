package com.project.Callyia.service;

import com.project.Callyia.dto.MemberDTO;
import com.project.Callyia.dto.ReplyDTO;
import com.project.Callyia.entity.Member;
import com.project.Callyia.entity.MemberRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public interface MemberService {
    private BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    String handleMemberRegistration(MemberDTO memberDTO);

    default Member dtoToEntity(MemberDTO memberDTO){
        Member member = Member.builder()
                .email(memberDTO.getEmail())
                .password(passwordEncoder().encode(memberDTO.getPassword()))
                .gender(memberDTO.getGender())
                .name(memberDTO.getName())
                .nickname(memberDTO.getNickname())
                .phone(memberDTO.getPhone())
                .profileImage(memberDTO.getProfileImage())
                .aboutMe(memberDTO.getAboutMe())
                .joinDate(memberDTO.getJoinDate())
                .roleSet(memberDTO.getRoleSet().isEmpty() ?
                        Collections.singleton(MemberRole.USER) :
                        memberDTO.getRoleSet().stream().map(
                                str -> {
                                    if (str.equals("ROLE_USER")) return MemberRole.USER;
                                    else if (str.equals("ROLE_ADMIN")) return MemberRole.ADMIN;
                                    else return MemberRole.USER;
                                }).collect(Collectors.toSet()))
                .build();
        return member;
    }

    default MemberDTO entityToDTO(Member member){
        MemberDTO memberDTO = MemberDTO.builder()
                .email(member.getEmail())
                .gender(member.getGender())
                .name(member.getName())
                .nickname(member.getNickname())
                .phone(member.getPhone())
                .profileImage(member.getProfileImage())
                .aboutMe(member.getAboutMe())
                .joinDate(member.getJoinDate())
                .roleSet(member.getRoleSet().stream().map(
                        role -> new String("ROLE_" + role.name())).collect(Collectors.toSet()))
                .build();
        return memberDTO;
    }

    public boolean isEmailExists(String email);

    public boolean isNicknameExists(String nickname);

    public boolean isPhoneExists(String phone);

    MemberDTO getMember(String email);

    MemberDTO getNickname(String nickname);

    MemberDTO getPhone(String nickname);

    MemberDTO updateMember(MemberDTO memberDTO);
    List<MemberDTO> getAllMember();


    void deleteMember(String email);

    void modify(MemberDTO memberDTO);

}