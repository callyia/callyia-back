package com.project.Callyia.entity;

import jakarta.persistence.*;
import lombok.*;


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

    @ManyToOne(fetch = FetchType.LAZY) //외래키 주인이 :N이다.
    @JoinColumn(name = "member_id") // 외래 키 컬럼 명시
    private Member member;
}
