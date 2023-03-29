package com.dm.secretsanta.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateMemberDto {

  private String firstname;
  private String lastname;
  private String email;
}
