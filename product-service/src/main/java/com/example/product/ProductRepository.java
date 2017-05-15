package com.example.product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Product SpringData Jpa repository, provide basic entity CRUD operations.
 * @author Ahmed.Rabie
 *
 */
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
	
	List<ProductEntity> findByCategoryIdEquals(Long categoryId);
}
