package com.dm.secretsanta.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Wish {

  @Id
  @GeneratedValue
  private int id;
  private String name;
  private String desc;
  private BigDecimal price;
  private String link;
  private boolean isTaken;


}
