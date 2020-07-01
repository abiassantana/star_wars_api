package com.star_wars_resistence.star_wars_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.star_wars_resistence.star_wars_api.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>{

}
