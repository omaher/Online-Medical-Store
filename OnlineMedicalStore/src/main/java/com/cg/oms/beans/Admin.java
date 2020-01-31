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
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "admin_info")
@Component("admin")
public class Admin {

	@Id
	@Column(name = "admin_id")
	private int adminId;

	@Column(name = "admin_name")
	private String admiName;
	

	private String password;
	private String email;
	private long contact;

	

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "admin_id")
	private List<AdminMessage> admin_message;

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	public String getAdmiName() {
		return admiName;
	}

	public void setAdmiName(String admiName) {
		this.admiName = admiName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getContact() {
		return contact;
	}

	public void setContact(long contact) {
		this.contact = contact;
	}

	
	public List<AdminMessage> getAdmin_message() {
		return admin_message;
	}

	public void setAdmin_message(List<AdminMessage> admin_message) {
		this.admin_message = admin_message;
	}

	@Override
	public String toString() {
		return "Admin [adminId=" + adminId + ", admiName=" + admiName + ", password=" + password + ", email=" + email
				+ ", contact=" + contact + ", admin_message="
				+ admin_message + "]";
	}
	
	

}
