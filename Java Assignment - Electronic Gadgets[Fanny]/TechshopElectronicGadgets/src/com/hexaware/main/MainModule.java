package com.hexaware.main;

import java.util.Scanner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

import com.hexaware.util.DBConnector;

import com.hexaware.entity.Customer;
import com.hexaware.entity.Product;

import com.hexaware.entity.Inventory;
import com.hexaware.entity.Order;
import com.hexaware.entity.OrderDetail;
import com.hexaware.entity.Payment;

import com.hexaware.dao.impl.CustomerDAOImpl;
import com.hexaware.dao.impl.ProductDAOImpl;
import com.hexaware.dao.impl.InventoryDAOImpl;
import com.hexaware.dao.impl.OrderDAOImpl;
import com.hexaware.dao.impl.OrderDetailDAOImpl;
import com.hexaware.dao.impl.PaymentDAOImpl;

import com.hexaware.exception.InvalidDataException;
import com.hexaware.exception.InsufficientStockException;
import com.hexaware.exception.IncompleteOrderException;
import com.hexaware.exception.PaymentFailedException;

public class MainModule {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int choice;

		do {
			System.out.println("\n====== TECHSHOP MAIN MENU ======");
			System.out.println("1. Add New Customer");
			System.out.println("2. View All Customers");
			System.out.println("3. Product Management");
			System.out.println("4. View All Products");
			System.out.println("5. Add Inventory");
			System.out.println("6. View Inventory");
			System.out.println("7. Place Order");
			System.out.println("8. View All Orders");
			System.out.println("9. Sales Reporting");
			System.out.println("10. Payment");
			System.out.println("11. Search a Product");
			System.out.println("12. Exit");
			System.out.print("Enter your choice: ");
			choice = sc.nextInt();
			sc.nextLine(); // consume newline

			switch (choice) {
			case 1:
				// TODO: Add New Customer
				try {
					
					List<Customer> existingCustomers = new CustomerDAOImpl().getAllCustomers();
			        if (!existingCustomers.isEmpty()) {
			            System.out.println("\n--- Existing Customers ---");
			            for (Customer c : existingCustomers) {
			                c.getCustomerDetails();
			                System.out.println();
			            }
			        }
					
					System.out.print("Enter Customer ID: ");
					int customerId = sc.nextInt();
					sc.nextLine();

					System.out.print("Enter First Name: ");
					String firstName = sc.nextLine();

					System.out.print("Enter Last Name: ");
					String lastName = sc.nextLine();

					System.out.print("Enter Email: ");
					String email = sc.nextLine();
					if (!email.contains("@")) {
						throw new InvalidDataException("Invalid email format! Please include '@'.");
					}

					System.out.print("Enter Phone (10 digits): ");
					String phone = sc.nextLine();

					System.out.print("Enter Address: ");
					String address = sc.nextLine();

					Customer customer = new Customer(customerId, firstName, lastName, email, phone, address);
					new com.hexaware.dao.impl.CustomerDAOImpl().insertCustomer(customer);

					System.out.println("Customer added successfully!");

				} catch (InvalidDataException e) {
					System.out.println("❌ " + e.getMessage());
				} catch (Exception e) {
					System.out.println("❌ Error adding customer: " + e.getMessage());
				}
				break;

			case 2:
				// TODO: View All Customers
				try {
					
					System.out.println("\n--- List of All Customers ---");
					List<Customer> customers = new CustomerDAOImpl().getAllCustomers();

					for (Customer c : customers) {
						c.getCustomerDetails();
						System.out.println();
					}
				} catch (Exception e) {
					System.out.println("Error fetching customers: " + e.getMessage());
				}
				break;
			case 3:
			    try {
			    	
			    
			        System.out.println("------ Product Management ------");
			        System.out.println("1. Add Product");
			        System.out.println("2. Update Product");
			        System.out.println("3. Delete Product");
			        System.out.print("Enter your choice: ");
			        int choice1 = sc.nextInt();
			        sc.nextLine();
			        
			        // Show all existing products
			        List<Product> existingProducts = new ProductDAOImpl().getAllProducts();
			        if (!existingProducts.isEmpty()) {
			            System.out.println("\n--- Existing Products ---");
			            for (Product p : existingProducts) {
			                p.GetProductDetails();
			                System.out.println();
			            }
			        }

			        switch (choice1) {
			            case 1:
			                System.out.print("Enter Product ID: ");
			                int productId = sc.nextInt();
			                sc.nextLine();

			                System.out.print("Enter Product Name: ");
			                String name = sc.nextLine();

			                System.out.print("Enter Description: ");
			                String description = sc.nextLine();

			                System.out.print("Enter Price: ");
			                double price = sc.nextDouble();
			                sc.nextLine();

			                Product product = new Product(productId, name, description, price);
			                new ProductDAOImpl().insertProduct(product);
			                System.out.println("✅ Product added successfully!");
			                break;

			            case 2:
			                System.out.print("Enter Product ID to update: ");
			                int updateId = sc.nextInt();
			                sc.nextLine();

			                System.out.print("Enter new Name: ");
			                String newName = sc.nextLine();

			                System.out.print("Enter new Description: ");
			                String newDesc = sc.nextLine();

			                System.out.print("Enter new Price: ");
			                double newPrice = sc.nextDouble();
			                sc.nextLine();

			                Product updatedProduct = new Product(updateId, newName, newDesc, newPrice);
			                new ProductDAOImpl().updateProduct(updatedProduct);
			                System.out.println("✅ Product updated successfully!");
			                break;

			            case 3:
			                System.out.print("Enter Product ID to delete: ");
			                int deleteId = sc.nextInt();
			                sc.nextLine();
			                new ProductDAOImpl().deleteProduct(deleteId);
			                System.out.println("🗑️ Product deleted successfully!");
			                break;

			            default:
			                System.out.println("❌ Invalid product menu choice.");
			        }

			    } catch (Exception e) {
			        System.out.println("❌ Error in Product Management: " + e.getMessage());
			    }
			    break;
			    
			case 4:
			    try {
			        List<Product> products = new ProductDAOImpl().getAllProducts();

			        if (products.isEmpty()) {
			            System.out.println("❌ No products found.");
			            break;
			        }

			        System.out.println("📦 Sort Products By:");
			        System.out.println("1. Name (A-Z)");
			        System.out.println("2. Price (Low to High)");
			        System.out.print("Enter your choice: ");
			        int sortChoice = sc.nextInt();
			        sc.nextLine();

			        if (sortChoice == 1) {
			            products.sort(Comparator.comparing(Product::getProductName));
			        } else if (sortChoice == 2) {
			            products.sort(Comparator.comparing(Product::getPrice));
			        }

			        for (Product p : products) {
			            p.GetProductDetails();
			            System.out.println();
			        }

			    } catch (Exception e) {
			        System.out.println("❌ Error displaying products: " + e.getMessage());
			    }
			    break;


			case 5:
				// TODO: Add Inventory
				try {
					// Show all available products
					System.out.println("\n--- Available Products ---");
					List<Product> allProducts = new ProductDAOImpl().getAllProducts();
					for (Product p : allProducts) {
					    p.GetProductDetails();
					    System.out.println();
					}
				
					System.out.print("Enter Inventory ID: ");
					int invId = sc.nextInt();

					System.out.print("Enter Product ID: ");
					int prodId = sc.nextInt();

					System.out.print("Enter Quantity in Stock: ");
					int qty = sc.nextInt();
					sc.nextLine();

					LocalDate today = LocalDate.now();
					Product prod = new ProductDAOImpl().getProductById(prodId);
					Inventory inv = new Inventory(invId, prod, qty, today);
					new InventoryDAOImpl().insertInventory(inv);
				} catch (Exception e) {
					System.out.println("Error: " + e.getMessage());
				}
				break;
			case 6:
				// TODO: View Inventory
				try {
					List<Inventory> invList = new InventoryDAOImpl().getAllInventory();
					for (Inventory i : invList) {
						i.ListAllProducts();
						System.out.println();
					}
				} catch (Exception e) {
					System.out.println("Error: " + e.getMessage());
				}
				break;
			case 7:
				// TODO: Place Order
				try {
					
					 // Show all customers
			        System.out.println("\n--- Available Customers ---");
			        List<Customer> customers = new CustomerDAOImpl().getAllCustomers();
			        for (Customer c : customers) {
			            c.getCustomerDetails();
			            System.out.println();
			        }

			        // Show all products
			        System.out.println("\n--- Available Products ---");
			        List<Product> products = new ProductDAOImpl().getAllProducts();
			        for (Product p : products) {
			            p.GetProductDetails();
			            System.out.println();
			        }

			        // Show inventory (optional)
			        System.out.println("\n--- Inventory Info ---");
			        List<Inventory> invList = new InventoryDAOImpl().getAllInventory();
			        for (Inventory inv : invList) {
			            inv.ListAllProducts();
			            System.out.println();
			        }

					
					System.out.print("Enter Order ID: ");
					int orderId = sc.nextInt();

					System.out.print("Enter Customer ID: ");
					int custId = sc.nextInt();

					System.out.print("Enter Product ID: ");
					int productId = sc.nextInt();

					System.out.print("Enter Quantity: ");
					int quantity = sc.nextInt();

					System.out.print("Enter Discount: ");
					double discount = sc.nextDouble();
					sc.nextLine();

					Customer cust = new CustomerDAOImpl().getCustomerById(custId);
					if (cust == null) {
						throw new IncompleteOrderException("Customer not found for ID: " + custId);
					}

					Product prod = new ProductDAOImpl().getProductById(productId);
					if (prod == null) {
						throw new IncompleteOrderException("Product not found for ID: " + productId);
					}

					Inventory inventory = new InventoryDAOImpl().getInventoryByProductId(productId);
					if (inventory == null) {
						throw new IncompleteOrderException("Inventory record not found for Product ID: " + productId);
					}
					if (quantity > inventory.getQuantityInStock()) {
						throw new InsufficientStockException(
								"Ordered quantity exceeds available stock (" + inventory.getQuantityInStock() + ").");
					}

					// Calculate Date
					LocalDate orderDate = LocalDate.now();
					double total = (prod.getPrice() * quantity) - discount;

					// Create Order and OrderDetail
					Order order = new Order(orderId, cust, orderDate, total, "Placed");
					new OrderDAOImpl().insertOrder(order);

					OrderDetail detail = new OrderDetail(orderId + 1000, order, prod, quantity, discount);
					new OrderDetailDAOImpl().insertOrderDetail(detail);
					System.out.println("Order placed successfully!");

				} catch (InsufficientStockException e) {
					System.out.println("Stock Error: " + e.getMessage());
				} catch (IncompleteOrderException e) {
					System.out.println("Order Incomplete: " + e.getMessage());
				} catch (Exception e) {
					System.out.println("General Error: " + e.getMessage());
				}
				break;
			case 8:
				// TODO: View All Orders
				try {
					List<Order> orders = new OrderDAOImpl().getAllOrders();
					for (Order o : orders) {
						o.GetOrderDetails();
						System.out.println();
					}
				} catch (Exception e) {
					System.out.println("Error: " + e.getMessage());
				}
				break;

			case 9: {
				System.out.println("Sales Reporting");
				System.out.println("-------------------");

				try (Connection conn = DBConnector.getConnection()) {
					String query = "SELECT SUM(totalamount) AS total_sales FROM orders WHERE status = 'Shipped'";
					try (PreparedStatement stmt = conn.prepareStatement(query)) {
						ResultSet rs = stmt.executeQuery();

						if (rs.next()) {
							double totalSales = rs.getDouble("total_sales");
							System.out.println("Total Sales (Shipped Orders): " + totalSales);
						} else {
							System.out.println("No shipped orders found.");
						}
					}
				} catch (SQLException e) {
					System.out.println("Error fetching sales report: " + e.getMessage());
				}
			}
				break;

			case 10:
				try {
					// show all existing orders before asking for order ID
					System.out.println("\n--- Available Orders ---");
			        List<Order> orders = new OrderDAOImpl().getAllOrders();
			        for (Order o : orders) {
			            o.GetOrderDetails();
			            System.out.println();
			        }
					
					System.out.print("Enter Payment ID: ");
					int pid = sc.nextInt();

					System.out.print("Enter Order ID: ");
					int oid = sc.nextInt();

					System.out.print("Enter Amount: ");
					double amount = sc.nextDouble();
					sc.nextLine();

					System.out.print("Enter Payment Method (UPI/Card/Cash): ");
					String method = sc.nextLine();

					System.out.print("Enter Payment Status (Success/Failed): ");
					String status = sc.nextLine();

					if (status.equalsIgnoreCase("Failed")) {
						throw new PaymentFailedException("Payment was declined. Please try again.");
					}

					Payment payment = new Payment(pid, oid, amount, method, status);
					new PaymentDAOImpl().recordPayment(payment);

					System.out.println("Payment recorded successfully.");

				} catch (PaymentFailedException e) {
					System.out.println("Payment Error: " + e.getMessage());
				} catch (Exception e) {
					System.out.println("General Error: " + e.getMessage());
				}
				break;
			case 11:
				System.out.print("Enter keyword to search product: ");
				String keyword = sc.nextLine();

				try {
					List<Product> results = new ProductDAOImpl().searchProductsByName(keyword);
					if (results.isEmpty()) {
						System.out.println("No products found.");
					} else {
						System.out.println("Products found:");
						for (Product p : results) {
							p.GetProductDetails();
							System.out.println();
						}
					}
				} catch (Exception e) {
					System.out.println("Error: " + e.getMessage());
				}
				break;

			case 12:
				System.out.println("Exiting... Thank you!");
				break;
			default:
				System.out.println("Invalid choice. Please try again.");
			}

		} while (choice != 12);

		sc.close();
	}
}
