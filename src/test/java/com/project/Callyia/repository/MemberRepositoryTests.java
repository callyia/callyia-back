package com.project.Callyia.repository;

import com.project.Callyia.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.stream.IntStream;

@SpringBootTest
public class MemberRepositoryTests {

    @Autowired
    private MemberRepository memberRepository;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    @Test
    public void insertMembers() {
        IntStream.rangeClosed(1,50).forEach(i -> {
            Member members = Member.builder()
                    .id("asdasd" + i)
                    .password("1"+i)
                    .name("사용자" + i)
                    .profileImage("")
                    .aboutMe("자기소개" + i)
                    .joinDate(LocalDate.of((int)(Math.random()*20)+1990, (int)(Math.random()*12)+1, (int)(Math.random()*28)+1))
                    .gender(new String[]{"여성", "남성"}[(int) (Math.random() * 2)]) //0:femail, 1:male
                    .nickname("zㅣ존"+ i)
                    .email("user" + i + "@example.com")
                    .phone("010-1234-1234")
                    .profileImage("ImageLink")
                    .build();
            memberRepository.save(members);
        });
    }
}
