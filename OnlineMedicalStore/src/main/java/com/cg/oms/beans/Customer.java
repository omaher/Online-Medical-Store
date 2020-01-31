package com.cg.oms.beans;

import java.util.Arrays;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "customer_info")
@Component("customer")
public class Customer {

	@Id
	@Column(name = "customer_id")
	private int customerId;
	private String email;
	private String password;

	@Transient
	private String newPassword;

	@Column(name = "customer_name")
	private String customerName;

	private int age;

//	@Column(name = "address_id")
//	private int addressId;

	private long contact;
	private String gender;

	@Column(name = "wallet_balance")
	private double walletBalance;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_id")
	private List<CustomerAddress> address;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_id")
	private Cart cart;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_id")
	private List<Order> order;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_id")
	private List<CustomerMessage> customer_message;

//
	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

//	public int getAddressId() {
//		return addressId;
//	}
//
//	public void setAddressId(int addressId) {
//		this.addressId = addressId;
//	}

	public long getContact() {
		return contact;
	}

	public void setContact(long contact) {
		this.contact = contact;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public double getWalletBalance() {
		return walletBalance;
	}

	public void setWalletBalance(double walletBalance) {
		this.walletBalance = walletBalance;
	}

	public List<CustomerAddress> getCustomerAddress() {
		return address;
	}

	public void setCustomerAddress(List<CustomerAddress> customerAddress) {
		this.address = customerAddress;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public List<Order> getOrder() {
		return order;
	}

	public void setOrder(List<Order> order) {
		this.order = order;
	}

	public List<CustomerMessage> getCustomer_message() {
		return customer_message;
	}

	public void setCustomer_message(List<CustomerMessage> customer_message) {
		this.customer_message = customer_message;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", email=" + email + ", password=" + password + ", newPassword="
				+ newPassword + ", customerName=" + customerName + ", age=" + age + ", contact=" + contact + ", gender="
				+ gender + ", walletBalance=" + walletBalance + ", customerAddress=" + address + ", cart=" + cart
				+ ", order=" + order + ", customer_message=" + customer_message + "]";
	}

}
