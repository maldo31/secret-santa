package com.dm.secretsanta.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import java.util.Map;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Party {

  @Id
  @GeneratedValue
  private int id;
  @Column(unique = true)
  private String name;
  @ManyToMany
  private Set<Member> userSet;
  @ManyToMany
  private Map<Member, Member> assignedUsers;


}
