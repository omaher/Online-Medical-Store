package com.cg.oms.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

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
import com.cg.oms.dao.OnlineMedicalStoreDAO;
import com.cg.oms.dao.OnlineMedicalStoreDAOImpl;

@Service
public class OnlineMedicalStoreServicesImpl implements OnlineMedicalStoreServices {

	OnlineMedicalStoreDAO dao = new OnlineMedicalStoreDAOImpl();

	@Override
	public Boolean registerCustomer(Customer customer) {
		return dao.registerCustomer(customer);
	}

	@Override
	public Customer loginCustomer(int customerId, String password) {
		return dao.loginCustomer(customerId, password);
	}

	@Override
	public Boolean updateCustomer(Customer customer) {
		return dao.updateCustomer(customer);
	}

	@Override
	public Boolean deleteCustomer(int customerId) {
		return dao.deleteCustomer(customerId);
	}

	@Override
	public Boolean updatePassword(int customerId, String oldPassword, String newPassword) {
		return dao.updatePassword(customerId, oldPassword, newPassword);
	}

	@Override
	public Boolean addAddress(CustomerAddress address) {
		return dao.addAddress(address);
	}

	@Override
	public Boolean updateAddress(CustomerAddress address) {
		return dao.updateAddress(address);
	}

	@Override
	public Admin adminLogin(int adminId, String password) {
		return dao.adminLogin(adminId, password);
	}

	@Override
	public List<Customer> getAllCustomer() {
		return dao.getAllCustomer();
	}

	@Override
	public Customer searchCustomer(int customerId) {
		return dao.searchCustomer(customerId);
	}

	@Override
	public List<Product> getAllProduct() {
		return dao.getAllProduct();
	}

	@Override
	public Product createProduct(Product product) {
		return dao.createProduct(product);
	}

	@Override
	public Product updateProduct(Product product) {
		return dao.updateProduct(product);
	}

	@Override
	public Boolean deleteProduct(int productId) {
		return dao.deleteProduct(productId);
	}

	@Override
	public Product searchProduct(int productId) {
		return dao.searchProduct(productId);
	}

	@Override
	public List<Product> searchProduct(String keyword) {
		List<Product> actualProduct = new ArrayList<Product>();

		List<Product> productList = dao.searchProduct(keyword);
		if (productList.size() > 0) {
			for (Product product : productList) {
				if (product.getSearchKeyword().contains(keyword)) {
					actualProduct.add(product);
				}
			}
		}

		return actualProduct;
	}

	@Override
	public Order placeOrder(int cartId) {
		return dao.placeOrder(cartId);
	}

	@Override
	public Boolean cancelOrder(int orderId, int customerId) {
		return dao.cancelOrder(orderId, customerId);
	}

	@Override
	public Cart addToCart(int productId, int customerId) {
		return dao.addToCart(productId, customerId);
	}

	@Override
	public Cart removeFromCart(int productId, int customerId) {
		return dao.removeFromCart(productId, customerId);
	}

	@Override
	public OrderInfo displayCart(int customerId) {
		return dao.displayCart(customerId);
	}

	@Override
	public Boolean sendMessageToCustomer(AdminMessage msg) {
		return dao.sendMessageToCustomer(msg);
	}

	@Override
	public List<CustomerMessage> getCustomerMSG() {
		return dao.getCustomerMSG();
	}

	@Override
	public Boolean sendMessageToAdmin(CustomerMessage msg) {
		return dao.sendMessageToAdmin(msg);
	}

	@Override
	public List<AdminMessage> getAdminMSG(int customerId) {
		return dao.getAdminMSG(customerId);
	}

	@Override
	public Boolean validateCard(Card card) {
		String cardNumber = card.getCard_number();
		Pattern pattern = Pattern.compile("\\d{4}\\-\\d{4}\\-\\d{4}\\-\\d{4}"); // Pattern matching for 16 digits.
		Matcher matcher = pattern.matcher(cardNumber);
		if (matcher.matches()) {
			String cvvNumber = Integer.toString(card.getCvv_number());
			Pattern pattern1 = Pattern.compile("\\d{3}"); // Pattern Matching for cvv.
			Matcher matcher1 = pattern1.matcher(cvvNumber);
			if (matcher1.matches()) {
				Date date = card.getExpiry_date();
				DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
				String stringdate = dateformat.format(date);

				String month = stringdate.substring(5, 7);
				String year = stringdate.substring(0, 4);

				System.out.println(month + " " + year + ".............");
				boolean validMonth = validateMonth(Integer.parseInt(month));
				boolean validYear = validateYear(Integer.parseInt(year));

				if (validMonth && validYear) {
					Date currentDate = new Date();
					if (card.getExpiry_date().after(currentDate))
						return true;

				}
			}

		}
		return false;
	}

	@Override
	public Double calculatePrice(Cart cart) {
		double price = 0;
		if (cart.getProduct1Id() != 0) {
			price += searchProduct(cart.getProduct1Id()).getPrice() * cart.getProduct1Count();
		}
		if (cart.getProduct2Id() != 0) {
			price += searchProduct(cart.getProduct2Id()).getPrice() * cart.getProduct2Count();
		}
		if (cart.getProduct3Id() != 0) {
			price += searchProduct(cart.getProduct3Id()).getPrice() * cart.getProduct3Count();
		}
		return price;
	}

	@Override
	public Boolean validateMonth(int month) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean validateYear(int year) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cart updateCart(Cart cart) {
		return dao.updateCart(cart);
	}

	@Override
	public List<OrderInfo> displayOrder(int customerId) {
		return dao.displayOrder(customerId);
	}

	@Override
	public Cart searchCart(int cartId) {
		return dao.searchCart(cartId);
	}

	@Override
	public OrderInfo searchOrder(int cartId) {

		return dao.searchOrder(cartId);
	}
}
