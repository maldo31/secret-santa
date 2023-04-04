package com.dm.secretsanta.dto;

import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetWishesInPriceRangeRequest {
  List<String> memberEmails;
  BigDecimal lowerLimit;
  BigDecimal upperLimit;

}
