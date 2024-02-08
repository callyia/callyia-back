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
@Setter
@ToString
public class DetailSchedule { //세부일정
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dno; //세부일정번호

    private String tip; //세부일정내용
    private String detailImages; //세부일정사진
    private long day; //몇일차


    @ManyToOne(fetch = FetchType.LAZY)
    private Schedule schedule;

    @ManyToOne(fetch = FetchType.LAZY)
    private Tour tour;

    @OneToMany(mappedBy = "detailSchedule", orphanRemoval = true)
    @JsonBackReference
    private List<Reply> replys = new ArrayList<>();
}
