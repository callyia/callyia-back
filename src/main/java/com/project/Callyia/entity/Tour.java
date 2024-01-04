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
  @Column(name = "place_id")
  private Long placeId;

  private String placeName;
  private String address;
  private Double latitude;
  private Double longitude;
  private String placeContent;
  private String checkColumn;
  private String image;


  //삭제 전략의 문제:
  //
  //CascadeType.ALL을 사용하면 부모 엔터티가 삭제될 때 연관된 자식 엔터티도 모두 삭제됩니다. 이는 경우에 따라 의도치 않은 데이터 손실을 초래할 수 있습니다. 예를 들어, 부모 엔터티를 삭제하더라도 특정 자식 엔터티를 삭제하면 안 되는 경우가 있을 수 있습니다.
  //무한 루프의 위험:
  //
  //양방향 연관 관계에서는 주로 mappedBy 속성을 사용하여 연관 관계를 매핑합니다. 그런데 CascadeType.ALL을 사용할 경우 무한 루프에 빠질 수 있습니다. 부모 엔터티를 저장할 때 자식 엔터티에도 저장이 발생하고, 자식 엔터티를 저장할 때 다시 부모 엔터티에 저장이 발생하는 식입니다.
  //의도하지 않은 엔터티 상태 변경:
  //
  //CascadeType.ALL을 사용하면 부모 엔터티에 발생한 변경이 자식 엔터티에도 모두 전파됩니다. 이는 의도치 않은 엔터티 상태 변경으로 이어질 수 있습니다.
  //성능 저하:
  //
  //모든 변경이나 삭제가 연쇄적으로 발생하므로 성능에 영향을 미칠 수 있습니다. 특히 삭제 연산은 많은 양의 데이터를 처리할 때 성능 저하가 발생할 가능성이 큽니다.
  @OneToMany(mappedBy = "placeId", cascade = CascadeType.ALL)  // "tour"는 TourBasket 엔터티에서 선언한 필드명
  private List<TourBasket> tourBaskets;

  // 리액트에서 Json형태로 전송시 bigint로 전송이 안되어 number로 전송하였는데 형태가 맞지 않아 변경한 것
  // 생성자 또는 빌더 메서드 수정
  @Builder
  public Tour(Long placeId) {
    this.placeId = placeId;
  }
}
