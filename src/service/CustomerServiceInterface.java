package service;

import model.Customer;

import java.util.List;

public interface CustomerServiceInterface {

	/**
	 * Create a new Customer sheet and store it in database
	 * @param newClient Customer we need to store in database
	 * @return Customer id if Customer was well store, else -1
	 */
	public int createNewCustomer(Customer newClient);
	
	
	/**
	 * Return a Customer stored in database based on id
	 * @param id The id of the customer we look for
	 * @return Customer if we find it, else null
	 */
	public Customer getById(int id);
	
	/**
	 * get all customers from the database
	 * @return List of customers
	 */
	public List<Customer> getAllCustomers();
}
