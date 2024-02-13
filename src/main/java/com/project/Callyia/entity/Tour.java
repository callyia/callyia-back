package com.project.Callyia.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Table(name = "tour")
public class Tour {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "place_id", columnDefinition = "BIGINT")
  private Long placeId;

  private String placeName;
  private String address;
  private Double latitude;
  private Double longitude;

  @Column(name = "place_content", length = 10000)
  private String placeContent;

  private String checkColumn;
  private String image;

  @Builder
  public Tour(Long placeId) {
    this.placeId = placeId;
  }
}
