package com.project.Callyia.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {
    private Long mno;

    private String id; //아이디
    private String password; //비밀번호
    private String gender; //성별
    private String name; //이름
    private String nickname; //닉네임
    private String phone; //전화번호
    private String email; //이메일
    private String profileImage; //프로필이미지
    private String aboutMe; //자기소개글
    private LocalDate joinDate; //가입일자
}
