package com.example.model;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "productCache")
@Cacheable
@Entity(name = "PRODUCT")
public class ProductEntity extends BaseEntity {
	
	@Column(name = "PRICE", nullable = false)
	private double price;
	
	@Column(name = "SALE_PRICE", nullable = true)
	private Double salePrice;
	
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
	
	public Double getSalePrice() {
		return salePrice;
	}
	
	public void setSalePrice(Double salePrice) {
		this.salePrice = salePrice;
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
}
