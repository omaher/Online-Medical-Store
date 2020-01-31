package com.cg.oms.beans;

public class OrderInfo {

	private int orderId;
	private int cartId;
	private String product1Name;
	private String product2Name;
	private String product3Name;
	private double totalPrice;
	private CustomerAddress address;
	private int product1Count;
	private int product2Count;
	private int product3Count;
	private int product1Id;
	private int product2Id;
	private int product3Id;
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getProduct1Id() {
		return product1Id;
	}

	public void setProduct1Id(int product1Id) {
		this.product1Id = product1Id;
	}

	public int getProduct2Id() {
		return product2Id;
	}

	public void setProduct2Id(int product2Id) {
		this.product2Id = product2Id;
	}

	public int getProduct3Id() {
		return product3Id;
	}

	public void setProduct3Id(int product3Id) {
		this.product3Id = product3Id;
	}

	public int getProduct1Count() {
		return product1Count;
	}

	public void setProduct1Count(int product1Count) {
		this.product1Count = product1Count;
	}

	public int getProduct2Count() {
		return product2Count;
	}

	public void setProduct2Count(int product2Count) {
		this.product2Count = product2Count;
	}

	public int getProduct3Count() {
		return product3Count;
	}

	public void setProduct3Count(int product3Count) {
		this.product3Count = product3Count;
	}

	public CustomerAddress getAddress() {
		return address;
	}

	public void setAddress(CustomerAddress address) {
		this.address = address;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public String getProduct1Name() {
		return product1Name;
	}

	public void setProduct1Name(String product1Name) {
		this.product1Name = product1Name;
	}

	public String getProduct2Name() {
		return product2Name;
	}

	public void setProduct2Name(String product2Name) {
		this.product2Name = product2Name;
	}

	public String getProduct3Name() {
		return product3Name;
	}

	public void setProduct3Name(String product3Name) {
		this.product3Name = product3Name;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public String toString() {
		return "OrderInfo [orderId=" + orderId + ", cartId=" + cartId + ", product1Name=" + product1Name
				+ ", product2Name=" + product2Name + ", product3Name=" + product3Name + ", totalPrice=" + totalPrice
				+ "]";
	}

}
