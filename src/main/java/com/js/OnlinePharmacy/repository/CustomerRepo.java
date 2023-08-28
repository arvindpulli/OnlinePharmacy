package com.js.OnlinePharmacy.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.js.OnlinePharmacy.entity.Address;
import com.js.OnlinePharmacy.entity.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Integer>{

	@Query("Select c from Customer c where c.customerEmail=?1")
	public Optional<Customer> findByEmail(String email);

	//Optional class is used to avoid null pointer exception

	
	
}
