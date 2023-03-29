package com.dm.secretsanta.repositories;

import com.dm.secretsanta.entities.Member;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member, Integer> {

  List<Member> findAll();

  Member findByEmailIgnoreCase(String email);

  void deleteMemberByEmailIgnoreCase(String email);

}
