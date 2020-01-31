package com.cg.oms.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name="cart_info")
@Component("cart")
public class Cart {
	
	@Id @Column(name="cart_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int cartId;
	
	@Column(name="customer_id")
	private int customerId;
	
	//product 1 detail
	@Column(name="product1_id")
	private int product1Id;
	
	@Column(name="product1_count")
	private int product1Count;
	
	//product 2 detail
	@Column(name="product2_id")
	private int product2Id;
	
	@Column(name="product2_count")
	private int product2Count;
	
	//product 3 detail
	@Column(name="product3_id")
	private int product3Id;
	
	@Column(name="product3_count")
	private int product3Count;
	
	
	private double total_price;
	
	private boolean active;


	public boolean isActive() {
		return active;
	}


	public void setActive(boolean active) {
		this.active = active;
	}


	public int getCartId() {
		return cartId;
	}


	public void setCartId(int cartId) {
		this.cartId = cartId;
	}


	public int getCustomerId() {
		return customerId;
	}


	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}


	public int getProduct1Id() {
		return product1Id;
	}


	public void setProduct1Id(int product1Id) {
		this.product1Id = product1Id;
	}


	


	public int getProduct1Count() {
		return product1Count;
	}


	public void setProduct1Count(int product1Count) {
		this.product1Count = product1Count;
	}


	public int getProduct2Id() {
		return product2Id;
	}


	public void setProduct2Id(int product2Id) {
		this.product2Id = product2Id;
	}


	public int getProduct2Count() {
		return product2Count;
	}


	public void setProduct2Count(int product2Count) {
		this.product2Count = product2Count;
	}


	public int getProduct3Id() {
		return product3Id;
	}


	public void setProduct3Id(int product3Id) {
		this.product3Id = product3Id;
	}


	public int getProduct3Count() {
		return product3Count;
	}


	public void setProduct3Count(int product3Count) {
		this.product3Count = product3Count;
	}


	public double getTotal_price() {
		return total_price;
	}


	public void setTotal_price(double total_price) {
		this.total_price = total_price;
	}


	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", customerId=" + customerId + ", product1Id=" + product1Id
				+ ", product1Count=" + product1Count + ", product2Id=" + product2Id + ", product2Count=" + product2Count
				+ ", product3Id=" + product3Id + ", product3Count=" + product3Count + ", total_price=" + total_price
				+ ", active=" + active + "]";
	}
}
