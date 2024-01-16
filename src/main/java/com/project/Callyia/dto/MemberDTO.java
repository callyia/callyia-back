package com.project.Callyia.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {
    private String email; //이메일
    private String password; //비밀번호
    private String gender; //성별
    private String name; //이름
    private String nickname; //닉네임
    private String phone; //전화번호
    private String profileImage; //프로필이미지
    private String aboutMe; //자기소개글
    private LocalDate joinDate; //가입일자

    @Builder.Default
    private Set<String> roleSet = new HashSet<>();
}
