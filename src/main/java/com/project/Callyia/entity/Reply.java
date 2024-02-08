package com.project.Callyia.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Reply extends DateEntity{ //세부일정댓글
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno; //댓글번호

    private String replyContents; //댓글내용

    @ManyToOne(fetch = FetchType.LAZY)
    private DetailSchedule detailSchedule; //세부일정번호

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member; //댓글단유저
}
