package com.dm.secretsanta.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddMembersToPartyDto {
  private List<String> memberEmails;
  private String partyName;
}
