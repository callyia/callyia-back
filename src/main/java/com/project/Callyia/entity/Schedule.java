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
public class Schedule { //큰일정
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Schedule")
    private Long sno; //큰일정번호

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;
}
