package com.devbu03.base.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductTempDTO {

  private Long idTemp;

  private Long categoryId;

  private Integer status;

  private String image;

  private String name;

  private BigDecimal price;

  private Integer quantity;

  private String description;

  private Integer totalSold;

  private LocalDateTime createdAt;

  private Long createdBy;

  private Long updatedBy;
  private int state;
  private Long productId;


}
