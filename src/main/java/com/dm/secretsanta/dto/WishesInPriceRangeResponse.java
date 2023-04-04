package com.dm.secretsanta.dto;

import com.dm.secretsanta.entities.Wish;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WishesInPriceRangeResponse {
  Map<String, List<Wish>> wishesForUser;
}

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
class WishesInPriceRangeForSingleUserDto {

  String email;
  List<Wish> wish;
}
