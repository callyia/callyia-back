package com.project.Callyia.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Table(name = "basket")
public class Basket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bno")
    private Long bno;

    @ManyToOne
    @JoinColumn(name = "place_id")
    private Tour placeId;

//  @ManyToOne
//  @JoinColumn(name = "user_id", referencedColumnName = "user_id")
//  private Long userId;
}
