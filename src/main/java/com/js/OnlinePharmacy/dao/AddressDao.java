package com.js.OnlinePharmacy.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.js.OnlinePharmacy.entity.Address;
import com.js.OnlinePharmacy.repository.AddressRepo;

@Repository
public class AddressDao {

	@Autowired
	private AddressRepo repo;
	
	public Address saveAddress(Address address) {
		return repo.save(address);
	}

	public Address findAddress(int addressId) {
		Optional<Address>optional=repo.findById(addressId);
		if(optional.isPresent()) {
			return optional.get();
		}return null;
	}
	
	public Address deleteAddress(int addressId) {
		Optional<Address>optional=repo.findById(addressId);
		if(optional.isPresent()) {
			repo.delete(optional.get());
			return optional.get();
		}return null;
	}
	
	public Address updateAddress(int addressId, Address address) {
		Optional<Address>optional=repo.findById(addressId);
		if(optional.isPresent()) {
			address.setAddressId(addressId);
			return repo.save(address);
		}return null;
	}
	
}
