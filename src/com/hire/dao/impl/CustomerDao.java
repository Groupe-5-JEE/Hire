package com.hire.dao.impl;

import com.hire.dao.CustomerDaoInterface;
import com.hire.model.Customer;
import com.hire.service.DBManager;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CustomerDao implements CustomerDaoInterface
{
	private EntityManager em = DBManager.getEntityManager();

	@Override
	public int createNewCustomer(Customer newClient) {
		try {
			em.getTransaction().begin();
			em.persist(newClient);
			em.flush();
			em.getTransaction().commit();
		} catch (Exception e) {
			return -1;
		}
		return newClient.getId();
	}

	@Override
	public Customer getById(int id) {
		return em.find(Customer.class, id);
	}

	@Override
	public List<Customer> getAllCustomers() {
		if (em != null) {
			try {
				Query query = em.createQuery("select p from Customer p");
				List<Customer> customers = (List<Customer>) query.getResultList();
				return customers;
			} catch (Exception exception) {
				System.out.println("Exception occured while reading user data: " + exception.getMessage());
				return null;
			}

		} else {
			System.out.println("DB server down.....");
		}
		return null;
	}

	@Override
	public void update(Customer customer) {
		if(em != null) {
			try {
				Customer entityCustomer = em.find(Customer.class, customer.getId());
				em.getTransaction().begin();
				entityCustomer.setName(customer.getName());
				entityCustomer.setFirstName(customer.getFirstName());
				entityCustomer.setEmail(customer.getEmail());
				entityCustomer.setPhone(customer.getPhone());
				entityCustomer.setAddress(customer.getAddress());
				em.getTransaction().commit();
			}catch(Exception exception) {
				System.out.println("Exception occured while reading user data: " + exception.getMessage());
			}
		} else {
			System.out.println("DB server down.....");
		}
	}

	@Override
	public void remove(int customerId) {
		if(em != null) {
			try {
				Customer customer = em.find(Customer.class, customerId);
				em.getTransaction().begin();
				em.remove(customer);
				em.getTransaction().commit();
			}catch(Exception exception) {
				System.out.println("Exception occured while reading user dara: " + exception.getMessage());
			}
		}else {
			System.out.println("DB server down.....");
		}
	}

	@Override
	public List<Customer> getAllCustomersByCriteria(String reqSearch) {
		if (em != null) {
			CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
			CriteriaQuery<Customer> criteriaQuery = criteriaBuilder.createQuery(Customer.class);
			Root<Customer> customerRoot = criteriaQuery.from(Customer.class);
			Predicate predicateForName = criteriaBuilder.like(customerRoot.get("name"),"%"+reqSearch+"%");
			Predicate predicateForMail = criteriaBuilder.like(customerRoot.get("email"),"%"+ reqSearch+"%");

			Predicate predicate = criteriaBuilder.or(predicateForName, predicateForMail);

			try {
				criteriaQuery.where(predicate);
				Query query =  em.createQuery(criteriaQuery);
				List<Customer> customers = (List<Customer>) query.getResultList();
				return customers;
			} catch (Exception exception) {
				System.out.println("Exception occured while reading user data: " + exception.getMessage());
				return null;
			}

		} else {
			System.out.println("DB server down.....");
		}
		return null;
	}

	@Override
	public List<Map.Entry<Customer, Long>> getBestHiringCustomer()
	{
		List<Map.Entry<Customer, Long>> list = new ArrayList<>();

		if(em != null)
		{
			Query query = em.createQuery("SELECT h.client, COUNT(h) AS total FROM Hire h GROUP BY h.client");

			for(Object[] entry : (List<Object[]>)query.getResultList())
				list.add(Map.entry((Customer)entry[0], (Long)entry[1]));

			list.sort((Map.Entry<Customer, Long> set1, Map.Entry<Customer, Long> set2) -> set2.getValue().compareTo(set1.getValue()));

			if(list.size() > 10)
				list = list.subList(0, 10);
		}

		return list;
	}

	@Override
	public List<Map.Entry<Customer, Double>> getBestBuyerCustomer()
	{
		List<Map.Entry<Customer, Double>> list = new ArrayList<>();

		if(em != null)
		{
			Query query = em.createQuery("SELECT h.client, SUM(h.priceExpected) AS total FROM Hire h GROUP BY h.client");

			for(Object[] entry : (List<Object[]>)query.getResultList())
				list.add(Map.entry((Customer)entry[0], (Double)entry[1]));

			list.sort((Map.Entry<Customer, Double> set1, Map.Entry<Customer, Double> set2) -> set2.getValue().compareTo(set1.getValue()));

			if(list.size() > 10)
				list = list.subList(0, 10);
		}

		return list;
	}
}
