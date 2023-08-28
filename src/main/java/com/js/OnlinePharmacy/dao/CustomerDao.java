package com.js.OnlinePharmacy.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.js.OnlinePharmacy.entity.Address;
import com.js.OnlinePharmacy.entity.Customer;
import com.js.OnlinePharmacy.repository.CustomerRepo;

@Repository
public class CustomerDao {

	@Autowired
	private CustomerRepo repo;
	
	public Customer saveCustomer(Customer customer) {
		return repo.save(customer);
	}

	public Customer updateCustomer(int customerId, Customer customer) {
		Optional<Customer>optional=repo.findById(customerId);
		if(optional.isPresent()) {
			customer.setCustomerId(customerId);
			customer.setAddresses(optional.get().getAddresses());
			customer.setBookings(optional.get().getBookings());
			repo.save(customer);
			return customer;
		}return null;
	}
	
	public Customer findCustomer(int customerId) {
		Optional<Customer>optional=repo.findById(customerId);
		if(optional.isPresent()) {
			return optional.get();
		}return null;
	}
	
	public Customer deleteCustomer(int customerId) {
		Optional<Customer>optional=repo.findById(customerId);
		if(optional.isPresent()) {
			List<Address>addresses=optional.get().getAddresses();
			for(Address add:addresses) {
				add.setCustomer(null);
			}
			repo.delete(optional.get());
			return optional.get();
		}return null;
	}

	public Customer findByEmail(String email) {
		Optional<Customer>optional=repo.findByEmail(email);
		if(optional.isPresent()) {
			return optional.get();
		}return null;
	}
	
}
