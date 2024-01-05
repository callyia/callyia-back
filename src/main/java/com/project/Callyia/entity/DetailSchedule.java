package com.project.Callyia.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Table(name = "DetailSchedule")
public class DetailSchedule { //세부일정
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DetailSchedule")
    private Long dno; //세부일정번호

    private String detailImages; //세부일정사진
    private String detailContents; //세부일정내용

    @ManyToOne(fetch = FetchType.LAZY)
    private Schedule schedule; //큰일정번호

}
