package com.project.Callyia.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TourDTO {
  private Long placeId;
  private String placeName;
  private String address;
  private Double latitude;
  private Double longitude;
  private String placeContent;
  private String checkColumn;
  private String image;
}
