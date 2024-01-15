package com.project.Callyia.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "plan_detail")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PlanDetail {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long dno;

  @ManyToOne(fetch = FetchType.LAZY)
  private Plan plan;

  @ManyToOne(fetch = FetchType.LAZY)
  private Tour tour;

  private Long day;

  private Long sequence;
}
