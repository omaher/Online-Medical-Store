package com.cg.oms;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.cg.oms.beans.Customer;
import com.cg.oms.main.OMSApp;

@SpringBootApplication
@ComponentScan(basePackageClasses = OMSApp.class)
public class OnlineMedicalStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineMedicalStoreApplication.class, args);

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("MySQLUnit");

	}

}
