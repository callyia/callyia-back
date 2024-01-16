package com.project.Callyia.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Member {
    @Id
    private String email; //아이디

    private String password; //비밀번호
    private String gender; //성별
    private String name; //이름
    private String nickname; //닉네임
    private String phone; //전화번호
    private String profileImage; //프로필이미지
    private String aboutMe; //자기소개글
    private LocalDate joinDate; //가입일자

    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private Set<MemberRole> roleSet = new HashSet<>();

    public void addMemberRole(MemberRole memberRole) {
        roleSet.add(memberRole);
    }
}
