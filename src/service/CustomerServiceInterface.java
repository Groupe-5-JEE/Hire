package service;
import model.Customer;
import java.util.ArrayList;

import java.util.List;

public interface CustomerServiceInterface {

	public int createNew(Customer newClient);
	
	public List<Customer>  getAll();
	
	public Customer get(int id);
	
	public ArrayList<Customer> getAllCustomers();
}
