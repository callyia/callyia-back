package com.project.Callyia.dto;

import com.project.Callyia.entity.Tour;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BasketDTO {
  private Long bno;
  private Long placeId;
  private String userId;
  private String placeName;
  private String image;
  private String address;
}
