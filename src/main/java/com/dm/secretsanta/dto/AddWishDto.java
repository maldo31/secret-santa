package com.dm.secretsanta.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddWishDto {

  private String name;
  private String desc;
  private BigDecimal price;
  private String link;

}
