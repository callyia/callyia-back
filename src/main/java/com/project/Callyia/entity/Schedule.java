package com.project.Callyia.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;


@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Schedule extends DateEntity { //큰 일정
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sno; //큰일정번호

    private String sName; //큰일정이름
    private long totalDay; //총 여행 일수

    //외래키 주인이 :N
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE) // 회원탈퇴할 시 탈퇴한 회원이 등록한 스케쥴도 같이 사라지는지 확인필요
    private Member member; // 일정 작성자 이메일

    // DetailSchedule과의 연관 관계에 Cascade 추가
    @OneToMany(mappedBy = "schedule", orphanRemoval = true)
    @JsonBackReference
    private List<DetailSchedule> detailSchedules = new ArrayList<>();

    // ScheduleStar 연관 관계에 Cascade 추가
    @OneToMany(mappedBy = "schedule", orphanRemoval = true)
    @JsonBackReference
    private List<ScheduleStar> scheduleStars = new ArrayList<>();
}
