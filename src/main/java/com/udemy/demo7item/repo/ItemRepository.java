package com.udemy.demo7item.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.udemy.demo7item.model.Item;

@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {
	
}
