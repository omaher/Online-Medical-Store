package com.cg.oms.main;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cg.oms.beans.Admin;
import com.cg.oms.beans.AdminMessage;
import com.cg.oms.beans.Card;
import com.cg.oms.beans.Cart;
import com.cg.oms.beans.Customer;
import com.cg.oms.beans.CustomerAddress;
import com.cg.oms.beans.CustomerMessage;
import com.cg.oms.beans.Order;
import com.cg.oms.beans.OrderInfo;
import com.cg.oms.beans.Product;
import com.cg.oms.services.NotificationService;
import com.cg.oms.services.OnlineMedicalStoreServices;
import com.cg.oms.services.OnlineMedicalStoreServicesImpl;

@RestController
@ComponentScan(basePackageClasses = NotificationService.class)
@CrossOrigin("http://localhost:4200")
public class OMSApp {

	// db name : onlinemedicalstore_db
	// change hbm2ddl.auto value in persistence.xml file only

	@Autowired
	NotificationService notificationService;

	OnlineMedicalStoreServices service = new OnlineMedicalStoreServicesImpl();
	AnnotationConfigApplicationContext userctx = new AnnotationConfigApplicationContext(Customer.class);

	// Customer API
	// ************************************************************************
	@RequestMapping(value = "/customer/registerCustomer", method = RequestMethod.POST)
	public Customer registerCustomer(@RequestBody Customer customer) {
		System.out.println("Imput" + customer);
		if (service.registerCustomer(customer)) {
			signupSuccess(customer);
			return customer;
		} else {
			return null;
		}
	}

	// cancel order
	@RequestMapping(value = "/order/cancelOrder/{orderId}/{customerId}", method = RequestMethod.DELETE)
	public Boolean cancelOrder(@PathVariable("orderId") int orderId, @PathVariable("customerId") int customerId) {
		return service.cancelOrder(orderId, customerId);
	}

	// display order
	@RequestMapping(value = "/order/displayOrder/{customerId}", method = RequestMethod.GET)
	public List<OrderInfo> displayOrder(@PathVariable("customerId") int customerId) {
		System.out.println(customerId + "-------------------------");
		List<OrderInfo> orderList = service.displayOrder(customerId);
		return orderList;
	}

	@RequestMapping(value = "/cart/displayCart/{customerId}", method = RequestMethod.GET)
	public OrderInfo displayCart(@PathVariable("customerId") int customerId) {
		System.out.println(customerId);
		OrderInfo orderInfo = service.displayCart(customerId);
		System.out.println(orderInfo);
		return orderInfo;
	}

	@RequestMapping(value = "/customer/placeOrder/{cartId}", method = RequestMethod.GET)
	public Order placeOrder(@PathVariable("cartId") int cartId) {
		Order order = service.placeOrder(cartId);
		return order;
	}

	@RequestMapping(value = "/customer/loginCustomer", method = RequestMethod.POST)
	public Customer loginCustomer(@RequestBody Customer customer) {
		Customer loginCustomer = service.loginCustomer(customer.getCustomerId(), customer.getPassword());
		System.out.println(customer);
		if (loginCustomer != null) {
			return loginCustomer;
		} else {
			return null;
		}
	}

	// delete customer

	@RequestMapping(value = "/customer/updateCustomer/{customerId}", method = RequestMethod.PUT)
	public Boolean updateCustomer(@RequestBody Customer customer, @PathVariable("customerId") int custometId) {
		customer.setCustomerId(custometId);
		if (service.updateCustomer(customer)) {
			return true;
		} else {
			return false;
		}

	}

	@RequestMapping(value = "/customer/updatePassword/{customerId}", method = RequestMethod.PUT)

	public Boolean updatePassword(@RequestBody Customer customer, @PathVariable("customerId") int customerId) {

		System.out.println(customer.getNewPassword());

		if (service.updatePassword(customerId, customer.getPassword(), customer.getNewPassword())) {
			return true;
		} else {
			return false;
		}

	}

	@RequestMapping(value = "/customer/addAddress/{customerId}", method = RequestMethod.POST)
	public Boolean addAddress(@RequestBody CustomerAddress customerAddress,
			@PathVariable("customerId") int customerId) {
		customerAddress.setCustomerId(customerId);

		if (service.addAddress(customerAddress)) {
			return true;
		} else {
			return false;
		}
	}

	// update address of Customer
	@RequestMapping(value = "/customer/updateAddress/{customerId}", method = RequestMethod.PUT)

	public Boolean updateAddress(@RequestBody CustomerAddress customerAddress,
			@PathVariable("customerId") int customerId) {

		customerAddress.setCustomerId(customerId);
		System.out.println(customerAddress);
		if (service.updateAddress(customerAddress)) {
			return true;
		} else {
			return false;
		}
	}

	// Add to cart
	@RequestMapping(value = "/customer/addToCart/{productId}/{customerId}", method = RequestMethod.GET)
	public Cart addToCart(@PathVariable("productId") int productId, @PathVariable("customerId") int customerId) {
		System.out.println(productId);
		System.out.println(customerId);
		Cart cart = service.addToCart(productId, customerId);
		System.out.println(cart);
		return cart;
	}

	// search cart
	@RequestMapping(value = "/customer/searchCart/{cartId}", method = RequestMethod.GET)
	public OrderInfo searchCart(@PathVariable("cartId") int cartId) {
		System.out.println(cartId);
		OrderInfo order = service.searchOrder(cartId);
		System.out.println(order);
		orderDone(order);
		return order;
	}

	// update cart
	@RequestMapping(value = "/customer/updateCart", method = RequestMethod.GET)
	public Cart updateCart(@RequestBody Cart cart) {
		Cart tempcart = service.updateCart(cart);
		return tempcart;
	}

	// Remove from cart
	@RequestMapping(value = "/customer/removeFromCart/{productId}/{customerId}", method = RequestMethod.GET)
	public Cart removeFromCart(@PathVariable("productId") int productId, @PathVariable("customerId") int customerId) {
		Cart cart = service.removeFromCart(productId, customerId);
		return cart;
	}

	// search product by search_Keyword
	@RequestMapping(value = "/admin/searchproductbykeyword", method = RequestMethod.POST)
	public List<Product> searchProduct(@RequestBody Product product) {

		return service.searchProduct(product.getSearchKeyword());
	}

	// validate Card
	@RequestMapping(value = "/card/validate", method = RequestMethod.POST)
	public Boolean validateCard(@RequestBody Card card) {

		return service.validateCard(card);
	}

	// send msg to admin
	@RequestMapping(value = "/customer/sendMessage", method = RequestMethod.POST)
	public Boolean sendMessageToAdmin(@RequestBody CustomerMessage message) {
		message.setMessageDate(new java.util.Date());
		if (service.sendMessageToAdmin(message)) {
			return true;
		} else {
			return false;
		}
	}
	// get msg from Admin

	@RequestMapping(value = "/customer/getMessage", method = RequestMethod.POST)
	public List<AdminMessage> getMessage(@RequestBody CustomerMessage message) {
		return service.getAdminMSG(message.getCustomerId());
	}

	// Admin API
	// ****************************************************************************************************************************
	// get all customer List
	@RequestMapping(value = "/admin/allCustomers", method = RequestMethod.GET)
	public List<Customer> getCustomers() {
		return service.getAllCustomer();
	}

	// admin login
	@RequestMapping(value = "/admin/adminLogin", method = RequestMethod.POST)
	public Admin adminLogin(@RequestBody Admin admin) {

		admin = service.adminLogin(admin.getAdminId(), admin.getPassword());
		System.out.println(admin);
		if (admin != null) {

			return admin;

		}
		return null;
	}

	// search customer by id
	@RequestMapping(value = "/customer/searchCustomer", method = RequestMethod.POST)
	public Customer searchCustomer(@RequestBody Customer customer) {

		Customer searchCustomer = service.searchCustomer(customer.getCustomerId());
		System.out.println(searchCustomer);
		if (searchCustomer != null) {
			return searchCustomer;
		} else {
//			searchfailedexception searchfailedexception = new SearchFailedException(
//					"SearchFailedException : User not Found");
//			searchfailedexception.getMessage();
		}
		return null;
	}

	// delete customer by Admin
	@RequestMapping(value = "/customer/deleteCustomer/{id}", method = RequestMethod.DELETE)
	public Boolean deleteCustomer(@PathVariable("id") int customerId) {
		if (service.deleteCustomer(customerId)) {
			return true;
		} else {
			return false;
		}

	}

	// Create Product
	@RequestMapping(value = "/admin/addproduct", method = RequestMethod.POST)
	public Product createProduct(@RequestBody Product inputproduct) {
		Product product = service.createProduct(inputproduct);
		return product;
	}

	// Search Product
	@RequestMapping(value = "/admin/searchproduct/{productId}", method = RequestMethod.GET)
	public Product searchProductById(@PathVariable("productId") int productId) {
		Product product = service.searchProduct(productId);
		return product;

	}

	// delete product
	@RequestMapping(value = "/admin/deleteproduct/{productId}", method = RequestMethod.DELETE)
	public Boolean deleteProduct(@PathVariable("productId") int productId) {
		return service.deleteProduct(productId);

	}

	// search product by search_Keyword
	@RequestMapping(value = "/admin/searchproductbykeyword/{searchKeyword}", method = RequestMethod.GET)
	public List<Product> searchProduct(@PathVariable("searchKeyword") String searchKeyword) {
		System.out.println(searchKeyword);
		List<Product> productList = new ArrayList<Product>();
		productList = service.searchProduct(searchKeyword);
		return productList;
	}

	// getAllProduct
	@RequestMapping(value = "/admin/searchallproduct", method = RequestMethod.GET)
	public List<Product> getAllProduct() {
		List<Product> productList = new ArrayList<Product>();
		productList = service.getAllProduct();
		return productList;
	}

	// update product
	@RequestMapping(value = "/admin/updateproduct", method = RequestMethod.PUT)
	public Product updateProduct(@RequestBody Product inputproduct) {
		Product product = service.updateProduct(inputproduct);
		return product;
	}

	// get Customer message
	@RequestMapping(value = "/admin/getCustomerMSG", method = RequestMethod.GET)
	public List<CustomerMessage> getCustomerMSG() {
		return service.getCustomerMSG();

	}

	// send msg to user
	@RequestMapping(value = "/admin/sendMessage", method = RequestMethod.POST)
	public Boolean sendMessageToCustomer(@RequestBody AdminMessage message) {
		message.setMessageDate(new java.util.Date());
		if (service.sendMessageToCustomer(message)) {
			return true;
		} else {
			return false;
		}
	}

	public String signupSuccess(Customer customer) {

		try {
			notificationService.sendNotificatonTo(customer);
		} catch (MailException e) {
			e.printStackTrace();
		}
		return "Thankyou";
	}

	public String orderDone(OrderInfo order) {
		try {
			notificationService.sendOrderNotificationTo(order);
		} catch (MailException e) {
			e.printStackTrace();
		}
		return "Order done";
	}

}
