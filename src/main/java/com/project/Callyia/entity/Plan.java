package com.project.Callyia.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "plan")
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Plan {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long pno;

  private String title;

  private String userId;
}
