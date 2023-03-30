package com.dm.secretsanta.repositories;

import com.dm.secretsanta.entities.Wish;
import org.springframework.data.repository.CrudRepository;

public interface WishRepository extends CrudRepository<Wish, Integer> {

}
