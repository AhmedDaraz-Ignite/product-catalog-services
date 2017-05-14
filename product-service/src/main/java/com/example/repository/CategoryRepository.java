package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
}
