package com.project.Callyia.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Table(name = "Schedule")
public class Schedule { //큰 일정
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sno; //큰일정번호

    private String sName; //큰일정이름

    @ManyToOne(fetch = FetchType.LAZY)
    private Member mno;
}
