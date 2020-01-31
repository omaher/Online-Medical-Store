
package com.cg.oms.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.cg.oms.beans.Customer;
import com.cg.oms.beans.OrderInfo;

@Service
public class NotificationService {
	Customer customer;

	private JavaMailSender javaMailSender;

	@Autowired
	public NotificationService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	public void sendNotificatonTo(Customer customer) throws MailException {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(customer.getEmail());

		mail.setFrom("projecthub10@gmail.com");
		mail.setSubject("Registration");
		String username = customer.getCustomerName();
		mail.setText("Hello" + "" + username + "Welcome to Medicare " + "\n" + "Hope you enjoy our  services");
		javaMailSender.send(mail);

	}

	public void sendOrderNotificationTo(OrderInfo order) throws MailException {
		SimpleMailMessage mail = new SimpleMailMessage();
		System.out.println(order.getEmail());
		mail.setTo(order.getEmail());

		mail.setFrom("projecthub10@gmail.com");
		mail.setSubject("Bill\n");
		String product1Name = order.getProduct1Name();
		String product2Name = order.getProduct2Name();
		String product3Name = order.getProduct3Name();
		Double price = order.getTotalPrice();
		mail.setText("Thank you for using our services\n" + "Your order details are below\n" + "Product1 Name" + " "
				+ product1Name + "\n" + "Product2 Name" + " " + product2Name + "\n" + "Product3 Name" + " "
				+ product3Name + "\n" + "Total price" + " " + price);
		javaMailSender.send(mail);

	}
}
