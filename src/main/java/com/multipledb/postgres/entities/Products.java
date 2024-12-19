package com.multipledb.postgres.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Products {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	String productName;
	String productPrice;
	
	public Products() {
		
	}
	
	public Products(Integer id, String productName, String productPrice) {
		this.id = id;
		this.productName = productName;
		this.productPrice = productPrice;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}
	
	
	
}
