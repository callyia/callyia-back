package com.project.Callyia.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Schedule { //큰 일정
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sno; //큰일정번호

    private String sName; //큰일정이름
    private long totalDay; //총 여행 일수

    //외래키 주인이 :N
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member; // 일정 작성자 이메일

}
