package com.example.model;

import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "productCache")
@Cacheable
@Entity(name = "PRODUCT")
public class ProductEntity extends BaseEntity {
	
	@Column(name = "PRICE", nullable = false)
	private double price;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "product")
	private List<ProductPriceEntity> targetPrices;
	
	@Column(name = "ON_SALE", columnDefinition="tinyint(1) default 0")
	private boolean onSale;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CATEGORY_ID")
	private CategoryEntity category;
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public boolean isOnSale() {
		return onSale;
	}
	
	public void setOnSale(boolean onSale) {
		this.onSale = onSale;
	}
	
	public CategoryEntity getCategory() {
		return category;
	}
	
	public void setCategory(CategoryEntity category) {
		this.category = category;
	}

	public List<ProductPriceEntity> getTargetPrices() {
		return targetPrices;
	}

	public void setTargetPrices(List<ProductPriceEntity> targetPrices) {
		this.targetPrices = targetPrices;
	}
}
