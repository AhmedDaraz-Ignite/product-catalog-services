package com.example.model;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "pricesCache")
@Cacheable
@Entity(name = "PRODUCT_PRICE")
public class ProductPriceEntity extends BaseEntity {
	
	@Column(name = "PRICE", nullable = true)
	private Double amount;
	
	@Column(name = "CURRENCY", nullable = true)
	private String currency;
	
	@ManyToOne
	@JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID")
	private ProductEntity product;

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public ProductEntity getProduct() {
		return product;
	}

	public void setProduct(ProductEntity product) {
		this.product = product;
	}

}
