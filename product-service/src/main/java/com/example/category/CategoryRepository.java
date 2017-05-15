package com.example.category;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Category SpringData Jpa repository, provide basic entity CRUD operations.
 * @author Ahmed.Rabie
 *
 */
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
}
