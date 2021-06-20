package com.fruitbox.repository;

import com.fruitbox.model.Fruit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FruitRepository extends JpaRepository<Fruit, Long> {}
