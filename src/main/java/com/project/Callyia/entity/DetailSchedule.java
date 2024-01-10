package com.project.Callyia.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class DetailSchedule { //세부일정
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dno; //세부일정번호

    private String place; //세부장소이름
    private String content; //세부일정내용
    private String place_id; // 장소id
    private String detailImages; //세부일정사진
    private long day; //몇일차

    @ManyToOne(fetch = FetchType.LAZY)
    private Schedule sno;

}
