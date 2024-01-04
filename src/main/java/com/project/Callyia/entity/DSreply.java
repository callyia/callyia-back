package com.project.Callyia.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Table(name = "DSrelpy")
public class DSreply { //세부일정댓글
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno; //댓글번호
    private String replyContents; //댓글내용

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member; //댓글단유저

    @ManyToOne(fetch = FetchType.LAZY)
    private DetailSchedule detailSchedule; //세부일정
}
