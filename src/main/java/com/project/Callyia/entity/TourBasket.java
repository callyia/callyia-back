package com.project.Callyia.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Table(name = "tour_basket")
public class TourBasket {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "bno")
  private Long bno;

  @ManyToOne
  @JoinColumn(name = "place_id", referencedColumnName = "place_id")
  private Tour placeId;

//  @ManyToOne
//  @JoinColumn(name = "user_id", referencedColumnName = "user_id")
//  private Long userId;
}
