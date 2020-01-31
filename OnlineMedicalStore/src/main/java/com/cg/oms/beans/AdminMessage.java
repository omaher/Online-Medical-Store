package com.cg.oms.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.stereotype.Component;

@Entity
@Table(name="admin_message")
@Component("adminMessage")
public class AdminMessage {
	
	@Id @Column(name="message_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int messageId;
	
	@Column(name="admin_id")
	private int adminId;
	
	@Column(name="customer_id")
	private int customerId;
	
	private String message;
	
	
	@Temporal(TemporalType.DATE)
	@Column(name="message_date")
	private Date messageDate;


	public int getMessageId() {
		return messageId;
	}


	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}


	public int getAdminId() {
		return adminId;
	}


	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}


	public int getCustomerId() {
		return customerId;
	}


	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public Date getMessageDate() {
		return messageDate;
	}


	public void setMessageDate(Date messageDate) {
		this.messageDate = messageDate;
	}


	@Override
	public String toString() {
		return "AdminMessage [messageId=" + messageId + ", adminId=" + adminId + ", customerId=" + customerId
				+ ", message=" + message + ", messageDate=" + messageDate + "]";
	}
	
	
	

}
