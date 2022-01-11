package com.wowoni.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wowoni.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
