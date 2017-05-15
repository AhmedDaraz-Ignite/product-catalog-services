package com.example.category;

import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.example.common.model.BaseEntity;
import com.example.product.ProductEntity;

@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "categoryCache")
@Cacheable
@Entity(name = "CATEGORY")
public class CategoryEntity extends BaseEntity {
	
	@JoinColumn(name = "PARENT_CATEGORY_ID", referencedColumnName = "ID")
	@ManyToOne(fetch = FetchType.LAZY)
	private CategoryEntity parentCategory;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
	private List<ProductEntity> products;

	public CategoryEntity getParentCategory() {
		return parentCategory;
	}

	public void setParentCategory(CategoryEntity parentCategory) {
		this.parentCategory = parentCategory;
	}
	
}
