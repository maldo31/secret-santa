package com.dm.secretsanta.repositories;

import com.dm.secretsanta.entities.Party;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface PartyRepository extends CrudRepository<Party, Integer> {

  List<Party> findAll();

  Party findPartyByName(String name);

  void deletePartyByName(String name);

}
