package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.CategoryEntity;

/**
 * Category SpringData Jpa repository, provide basic entity CRUD operations.
 * @author Ahmed.Rabie
 *
 */
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
}
