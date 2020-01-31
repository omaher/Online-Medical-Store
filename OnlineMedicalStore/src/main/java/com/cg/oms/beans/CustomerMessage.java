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
@Table(name = "customer_message")
@Component("customerMessage")
public class CustomerMessage {

	@Id
	@Column(name = "message_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int messageId;

	@Column(name = "customer_id")
	private int customerId;

	@Column(name = "user_message")
	private String userMessage;

	@Temporal(TemporalType.DATE)
	@Column(name = "message_date")
	private Date messageDate;

	public int getMessageId() {
		return messageId;
	}

	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getUserMessage() {
		return userMessage;
	}

	public void setUserMessage(String userMessage) {
		this.userMessage = userMessage;
	}

	public Date getMessageDate() {
		return messageDate;
	}

	public void setMessageDate(Date messageDate) {
		this.messageDate = messageDate;
	}

	@Override
	public String toString() {
		return "CustomerMessage [messageId=" + messageId + ", customerId=" + customerId + ", userMessage=" + userMessage
				+ ", messageDate=" + messageDate + "]";
	}

}
