package com.project.Callyia.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ScheduleStar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long starNum; //평가번호

    private Long starScore; //평가별점

    @ManyToOne(fetch = FetchType.LAZY)
    private Schedule schedule;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;
}
