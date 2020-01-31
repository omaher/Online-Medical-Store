package com.cg.oms.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.cg.oms.beans.Customer;

@Configuration
@ComponentScan(basePackageClasses = Customer.class)
public class CustomerConfig {

}
