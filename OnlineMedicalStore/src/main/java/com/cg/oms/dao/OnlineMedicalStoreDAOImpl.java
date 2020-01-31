package com.cg.oms.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.cg.oms.beans.Admin;
import com.cg.oms.beans.AdminMessage;
import com.cg.oms.beans.Cart;
import com.cg.oms.beans.Customer;
import com.cg.oms.beans.CustomerAddress;
import com.cg.oms.beans.CustomerMessage;
import com.cg.oms.beans.Order;
import com.cg.oms.beans.OrderInfo;
import com.cg.oms.beans.Product;
import com.cg.oms.services.OnlineMedicalStoreServices;
import com.cg.oms.services.OnlineMedicalStoreServicesImpl;

@Transactional
@Repository
public class OnlineMedicalStoreDAOImpl implements OnlineMedicalStoreDAO {

	public static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("MySQLUnit");

	@Override
	public Boolean registerCustomer(Customer customer) {
		Boolean state = false;
		try {
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			// create user
			em.persist(customer);
			em.getTransaction().commit();
			em.close();
			state = true;
		} catch (Exception e) {
			// throw new RegisterException("Registration Exception Occured");
		}
		return state;
	}

	@Override
	public Customer loginCustomer(int customerId, String password) {
		Customer customer = null;
		try {
			EntityManager em = emf.createEntityManager();
			TypedQuery<Customer> query = em.createQuery("from Customer  where customerId= :id and password= :password",
					Customer.class);

			// setting paramters
			query.setParameter("id", customerId);
			query.setParameter("password", password);
			List<Customer> customers = query.getResultList();
			if (customers.size() > 0) {
				customer = customers.get(0);
			}
			System.out.println(customers);
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return customer;
	}

	@Override
	public Boolean updateCustomer(Customer customer) {
		Boolean state = false;
		try {
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();

			// Getting user's details
			Customer customers = em.find(Customer.class, customer.getCustomerId());

			// updating details
			em.detach(customers);
			customers.setCustomerName(customer.getCustomerName());
			customers.setEmail(customer.getEmail());
			customers.setAge(customer.getAge());
			customers.setContact(customer.getContact());
			customers.setGender(customer.getGender());
			customers.setPassword(customer.getPassword());
			customers.setWalletBalance(customer.getWalletBalance());

			em.merge(customers);

			em.getTransaction().commit();
			em.close();
			state = true;
		} catch (Exception e) {
			// Custom Exception
			e.printStackTrace();
		}
		return state;
	}

	@Override
	public Boolean deleteCustomer(int customerId) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Customer customer = em.find(Customer.class, customerId);

		// Deleting user
		em.remove(customer);
		em.getTransaction().commit();
		em.close();
		return true;
	}

	@Override
	public Boolean updatePassword(int customerId, String oldPassword, String newPassword) {
		Boolean state = false;
		try {
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			Customer customers = em.find(Customer.class, customerId);
			System.out.println(customers + "++++++++++++++++++++++++++++++++++++++++++++++");
			if (customers.getPassword().equals(oldPassword)) {
				System.out.println(customers + "===========================================");
				customers.setPassword(newPassword);
				em.getTransaction().commit();
				em.close();
				state = true;
			}
		} catch (Exception e) {
			// Custom Exception
			e.printStackTrace();
		}
		return state;

	}

	@Override
	public Boolean addAddress(CustomerAddress address) {
		Boolean state = false;
		try {
			EntityManager em = emf.createEntityManager();
			Customer customer = em.find(Customer.class, address.getCustomerId());

			em.getTransaction().begin();
			// create user
			em.persist(address);
			// customer.setAddressId(address.getAddressId());
			em.getTransaction().commit();
			em.close();

			updateCustomer(customer);
			state = true;
		} catch (Exception e) {
			// throw new RegisterException("Registration Exception Occured");
		}
		return state;
	}

	@Override
	public Boolean updateAddress(CustomerAddress address) {

		Boolean state1 = false;
		try {
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();

			// Getting user's address

			TypedQuery<CustomerAddress> query = em.createQuery("from CustomerAddress where customerId=:cid",
					CustomerAddress.class);
			query.setParameter("cid", address.getCustomerId());
			List<CustomerAddress> customeraddressList = query.getResultList();
			if (customeraddressList.size() > 0) {
				// updating details
				CustomerAddress customeraddress = customeraddressList.get(0);
				em.detach(customeraddress);

				customeraddress.setApartment(address.getApartment());
				customeraddress.setStreet(address.getStreet());
				customeraddress.setCity(address.getCity());
				customeraddress.setPincode(address.getPincode());
				customeraddress.setState(address.getState());

				em.merge(customeraddress);
			}
			em.getTransaction().commit();
			em.close();
			state1 = true;
		} catch (Exception e) {
			// Custom Exception
			e.printStackTrace();
		}
		return state1;
	}

	@Override
	public Admin adminLogin(int adminId, String password) {
		try {
			System.out.println(adminId);
			System.out.println(password);
			EntityManager em = emf.createEntityManager();
			TypedQuery<Admin> query = em.createQuery("from Admin a where a.adminId= :id and a.password= :passwd",
					Admin.class);
			query.setParameter("id", adminId);
			query.setParameter("passwd", password);
			List<Admin> admin1 = query.getResultList();
			System.out.println(admin1);
			if (admin1.get(0) != null) {
				Admin admin = admin1.get(0);
				em.close();
				return admin;
			}
		} catch (Exception e) {
		}
		return null;
	}

	@Override
	public List<Customer> getAllCustomer() {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Customer> query = em.createQuery("Select c from Customer c", Customer.class);
		List<Customer> customers = query.getResultList();
		if (customers != null) {
			return customers;
		} else {
			return null;
		}
	}

	@Override
	public Customer searchCustomer(int customerId) {
		try {
			EntityManager em = emf.createEntityManager();
			Customer customer = em.find(Customer.class, customerId);
			System.out.println(customer);
			em.close();
			return customer;

		} catch (Exception e) {

		}
		return null;
	}

	@Override
	public List<Product> getAllProduct() {
		List<Product> productList = new ArrayList<Product>();
		try {
			EntityManager em = emf.createEntityManager();
			TypedQuery<Product> query = em.createQuery("from Product", Product.class);
			productList = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return productList;
	}

	@Override
	public Product createProduct(Product product) {
		try {
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			em.persist(product);
			em.getTransaction().commit();
		} catch (Exception e) {

		}
		return product;
	}

	@Override
	public Product updateProduct(Product product) {
		Product productone = null;
		try {
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			productone = em.find(Product.class, product.getProductId());
			productone.setProductName(product.getProductName());
			productone.setCategory(product.getCategory());
			productone.setQuantity(product.getQuantity());
			productone.setPrice(product.getPrice());
			productone.setDescription(product.getDescription());
			productone.setSearchKeyword(product.getSearchKeyword());
			em.getTransaction().commit();
		} catch (Exception e) {

		}
		return productone;
	}

	@Override
	public Boolean deleteProduct(int productId) {
		Boolean state = false;
		try {
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			Product product = em.find(Product.class, productId);
			em.remove(product);
			em.getTransaction().commit();
			state = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return state;
	}

	@Override
	public Product searchProduct(int productId) {
		Product product = null;
		try {
			EntityManager em = emf.createEntityManager();
			product = em.find(Product.class, productId);

		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		return product;
	}

	@Override
	public List<Product> searchProduct(String keyword) {
		List<Product> productList = null;
		try {
			EntityManager em = emf.createEntityManager();
			TypedQuery<Product> query = em.createQuery("from Product", Product.class);
			productList = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return productList;
	}

	@Override
	public Order placeOrder(int cartId) {
		OnlineMedicalStoreServices service = new OnlineMedicalStoreServicesImpl();

		try {
			Order order = new Order();
			EntityManager em = emf.createEntityManager();
			Cart cart = em.find(Cart.class, cartId);
			order.setCartId(cartId);
			order.setCustomerId(cart.getCustomerId());
			order.setTotalPrice(cart.getTotal_price());

			cart.setCustomerId(cart.getCustomerId());
			em.getTransaction().begin();
			em.persist(order);
			order.setTotalPrice(cart.getTotal_price());
			System.out.println(order + "orderrrrrrrrrrrr");
			System.out.println(cart + "............");
			System.out.println(service.calculatePrice(cart));

			// after placing order reset customer cart
			Cart orderedCart = em.find(Cart.class, cart.getCartId());
			orderedCart.setActive(false);

			em.getTransaction().commit();
			em.close();
			return order;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Boolean cancelOrder(int orderId, int customerId) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Order order = em.find(Order.class, orderId);
		Cart cart = em.find(Cart.class, order.getCartId());
		em.remove(cart);
		javax.persistence.Query query = em
				.createQuery("Delete from Order o where o.orderId=:orderId and o.customerId=:customerId");
		query.setParameter("orderId", orderId);
		query.setParameter("customerId", customerId);
		int a = query.executeUpdate();
		em.getTransaction().commit();
		if (a > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Cart addToCart(int productId, int customerId) {
		// search if the user's cart already exists
		OnlineMedicalStoreServices service = new OnlineMedicalStoreServicesImpl();
		Cart cart = null;
		int slotNo = getCartAvailability(customerId);
		try {
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			TypedQuery<Cart> query = em.createQuery("from Cart c where customerId= :cId and active=true", Cart.class);
			query.setParameter("cId", customerId);
			List<Cart> cartList = query.getResultList();
			if (cartList.size() > 0) {
				cart = cartList.get(0);
				cart.setProduct1Count(1);
				if (slotNo == 1) {
					cart.setProduct1Id(productId);
					// cart.setProduct1Count(1);
				} else if (slotNo == 2) {
					cart.setProduct2Id(productId);
					cart.setProduct2Count(1);
				} else if (slotNo == 3) {
					cart.setProduct3Id(productId);
					cart.setProduct3Count(1);
				}
				cart.setProduct1Count(1);
				cart.setTotal_price(service.calculatePrice(cart));
				System.out.println(cart + "----------------------------");
			}

			em.getTransaction().commit();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// if the customer doesn't have cart create new cart
		if (cart == null) {
			cart = new Cart();
			try {
				EntityManager em = emf.createEntityManager();
				em.getTransaction().begin();
				cart.setCustomerId(customerId);
				cart.setProduct1Id(productId);
				cart.setActive(true);
				cart.setProduct2Id(0);
				cart.setProduct3Id(0);
				cart.setTotal_price(service.calculatePrice(cart));
				// TO-DO
				// cart.setTotal_price(service.calculatePrice(cart));
				em.persist(cart);
				em.getTransaction().commit();
				em.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return cart;
	}

	@Override
	public Cart removeFromCart(int productId, int customerId) {
		Cart cart = null;
		try {
			EntityManager em = emf.createEntityManager();
			OnlineMedicalStoreServices service = new OnlineMedicalStoreServicesImpl();
			em.getTransaction().begin();
			TypedQuery<Cart> query = em.createQuery("From Cart where customerId = :id and active=1", Cart.class);
			query.setParameter("id", customerId);
			List<Cart> cartList = query.getResultList();
			if (cartList.size() > 0) {
				cart = cartList.get(0);
				System.out.println(cart + "++++++++++++++++++++++++++++++++++++++++++++++");
				if (productId == cart.getProduct1Id()) {
					cart.setProduct1Id(0);
					cart.setProduct1Count(0);
				} else if (productId == cart.getProduct2Id()) {
					cart.setProduct2Id(0);
					cart.setProduct2Count(0);
				} else if (productId == cart.getProduct3Id()) {
					cart.setProduct3Id(0);
					cart.setProduct3Count(0);
				}
				cart.setTotal_price(service.calculatePrice(cart));
			}
			em.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return cart;
	}

	@Override
	public OrderInfo displayCart(int customerId) {
		Cart cart = null;
		Product product = null;
		OrderInfo orderInfo = new OrderInfo();
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			TypedQuery<Cart> query = em.createQuery("from Cart c where customerId= :cId and active=1", Cart.class);
			query.setParameter("cId", customerId);
			List<Cart> cartList = query.getResultList();

			if (cartList.size() > 0) {
				cart = cartList.get(0);
				orderInfo.setTotalPrice(cart.getTotal_price());
				orderInfo.setCartId(cart.getCartId());
				if (cart.getProduct1Id() != 0) {
					product = em.find(Product.class, cart.getProduct1Id());
					orderInfo.setProduct1Name(product.getProductName());
					orderInfo.setProduct1Count(cart.getProduct1Count());
					orderInfo.setProduct1Id(product.getProductId());
				}
				if (cart.getProduct2Id() != 0) {
					product = em.find(Product.class, cart.getProduct2Id());
					orderInfo.setProduct2Name(product.getProductName());
					orderInfo.setProduct2Count(cart.getProduct2Count());
					orderInfo.setProduct2Id(product.getProductId());
				}
				if (cart.getProduct3Id() != 0) {
					product = em.find(Product.class, cart.getProduct3Id());
					orderInfo.setProduct3Name(product.getProductName());
					orderInfo.setProduct3Count(cart.getProduct3Count());
					orderInfo.setProduct3Id(product.getProductId());
				}
				if (customerId != 0) {
					TypedQuery<CustomerAddress> query1 = em
							.createQuery("From CustomerAddress a where a.customerId=:cId", CustomerAddress.class);
					query1.setParameter("cId", cart.getCustomerId());
					CustomerAddress address = query1.getSingleResult();
					System.out.println(address);
					orderInfo.setAddress(address);
				}
			}
			em.getTransaction().commit();
		} catch (Exception e) {
		}
		return orderInfo;
	}

	@Override
	public Integer getCartAvailability(int customerId) {
		// search if the user's cart already exists
		// then return the available slot
		Cart cart = null;
		// 0 means no slot available
		int slotNo = 0;
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			TypedQuery<Cart> query = em.createQuery("from Cart c where customerId= :cId and active=true", Cart.class);
			query.setParameter("cId", customerId);
			List<Cart> cartList = query.getResultList();

			if (cartList.size() > 0) {
				cart = cartList.get(0);
			}
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		if (cart != null) {
			if (cart.getProduct1Id() == 0) {
				slotNo = 1;
			} else if (cart.getProduct2Id() == 0) {
				slotNo = 2;
			} else if (cart.getProduct3Id() == 0) {
				slotNo = 3;
			}
		}
		return slotNo;
	}

	@Override
	public Boolean sendMessageToCustomer(AdminMessage msg) {
		boolean state = false;
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(msg);
			em.getTransaction().commit();
			state = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return state;
	}

	@Override
	public List<CustomerMessage> getCustomerMSG() {
		List<CustomerMessage> messageList = null;

		try {
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			TypedQuery<CustomerMessage> query = em.createQuery("from CustomerMessage", CustomerMessage.class);
			messageList = query.getResultList();
			em.getTransaction().commit();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return messageList;
	}

	@Override
	public Boolean sendMessageToAdmin(CustomerMessage msg) {
		boolean state = false;
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(msg);
			em.getTransaction().commit();
			state = true;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return state;
	}

	@Override
	public List<AdminMessage> getAdminMSG(int customerId) {
		EntityManager em = emf.createEntityManager();
		TypedQuery<AdminMessage> query = em.createNamedQuery("from AdminMessage", AdminMessage.class);
		List<AdminMessage> messages = query.getResultList();
		em.close();
		return messages;
	}

	@Override
	public Cart updateCart(Cart cart) {
		// TO-DO
		List<Cart> cartList = null;
		Cart tempcart = null;

		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			TypedQuery<Cart> query = em.createQuery("From Cart where customerId= :cId and active=true", Cart.class);
			query.setParameter("cId", cart.getCustomerId());
			cartList = query.getResultList();
			if (cartList.size() > 0) {
				tempcart = cartList.get(0);
				if (tempcart.getProduct1Id() == cart.getProduct1Id()) {
					tempcart.setProduct1Count(cart.getProduct1Count());
				}
				if (tempcart.getProduct2Id() == cart.getProduct2Id()) {
					tempcart.setProduct2Count(cart.getProduct2Count());
				}
				if (tempcart.getProduct3Id() == cart.getProduct3Id()) {
					tempcart.setProduct3Count(cart.getProduct2Count());
				}
			}
			em.getTransaction().commit();

		} catch (Exception e) {

		}
		return tempcart;

	}

	@Override
	public List<OrderInfo> displayOrder(int customerId) {

		EntityManager em = emf.createEntityManager();
		TypedQuery<Order> query = em.createQuery("From Order where customerId= :cId", Order.class);
		query.setParameter("cId", customerId);

		List<OrderInfo> orderList = new ArrayList<OrderInfo>();
		List<Order> orders = query.getResultList();
		System.out.println(orders);
		for (Order order : orders) {
			OrderInfo orderInfo = new OrderInfo();
			Cart cart = searchCart(order.getCartId());
			System.out.println(cart + "-----------------------------");

			orderInfo.setOrderId(order.getOrderId());
			orderInfo.setTotalPrice(cart.getTotal_price());
			Product product = null;
			if (cart.getProduct1Id() != 0) {
				product = searchProduct(cart.getProduct1Id());
				orderInfo.setProduct1Name(product.getProductName());
				orderInfo.setProduct1Id(product.getProductId());
				orderInfo.setProduct1Count(cart.getProduct1Count());
			}
			if (cart.getProduct2Id() != 0) {
				product = searchProduct(cart.getProduct2Id());
				orderInfo.setProduct2Name(product.getProductName());
				orderInfo.setProduct2Id(product.getProductId());
				orderInfo.setProduct2Count(cart.getProduct2Count());
			}

			if (cart.getProduct3Id() != 0) {
				product = searchProduct(cart.getProduct3Id());
				orderInfo.setProduct3Name(product.getProductName());
				orderInfo.setProduct3Id(product.getProductId());
				orderInfo.setProduct3Count(cart.getProduct3Count());
			}
			if (customerId != 0) {
				TypedQuery<CustomerAddress> query1 = em.createQuery("From CustomerAddress a where a.customerId=:cId",
						CustomerAddress.class);
				query1.setParameter("cId", cart.getCustomerId());
				CustomerAddress address = query1.getSingleResult();
				System.out.println(address);
				orderInfo.setAddress(address);
			}
			orderList.add(orderInfo);
		}
		return orderList;
	}

	@Override
	public Cart searchCart(int cartId) {
		EntityManager em = emf.createEntityManager();
		Cart cart = em.find(Cart.class, cartId);
		em.close();
		System.out.println(cart);
		return cart;
	}

	@Override
	public OrderInfo searchOrder(int cartId) {
		EntityManager em = emf.createEntityManager();
		Cart cart = em.find(Cart.class, cartId);
		Product product = null;
		OrderInfo orderInfo = new OrderInfo();
		orderInfo.setCartId(cartId);
		orderInfo.setTotalPrice(cart.getTotal_price());
		if (cart.getProduct1Id() != 0) {
			product = searchProduct(cart.getProduct1Id());
			orderInfo.setProduct1Name(product.getProductName());
			orderInfo.setProduct1Id(product.getProductId());
			orderInfo.setProduct1Count(cart.getProduct1Count());
		}
		if (cart.getProduct2Id() != 0) {
			product = searchProduct(cart.getProduct2Id());
			orderInfo.setProduct2Name(product.getProductName());
			orderInfo.setProduct2Id(product.getProductId());
			orderInfo.setProduct2Count(cart.getProduct2Count());
		}

		if (cart.getProduct3Id() != 0) {
			product = searchProduct(cart.getProduct3Id());
			orderInfo.setProduct3Name(product.getProductName());
			orderInfo.setProduct3Id(product.getProductId());
			orderInfo.setProduct3Count(cart.getProduct3Count());
		}
		if (cart.getCustomerId() != 0) {
			TypedQuery<CustomerAddress> query1 = em.createQuery("From CustomerAddress a where a.customerId=:cId",
					CustomerAddress.class);
			query1.setParameter("cId", cart.getCustomerId());
			CustomerAddress address = query1.getSingleResult();
			Customer customer = searchCustomer(cart.getCustomerId());
			orderInfo.setEmail(customer.getEmail());
			System.out.println(address);
			orderInfo.setAddress(address);
		}
		return orderInfo;
	}

}
