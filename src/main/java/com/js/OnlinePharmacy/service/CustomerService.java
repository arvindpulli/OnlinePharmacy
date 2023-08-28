package com.js.OnlinePharmacy.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.js.OnlinePharmacy.dao.AddressDao;
import com.js.OnlinePharmacy.dao.CustomerDao;
import com.js.OnlinePharmacy.dto.AddressDto;
import com.js.OnlinePharmacy.dto.BookingDto;
import com.js.OnlinePharmacy.dto.CustomerDto;
import com.js.OnlinePharmacy.entity.Address;
import com.js.OnlinePharmacy.entity.Booking;
import com.js.OnlinePharmacy.entity.Customer;
import com.js.OnlinePharmacy.exception.AddressAlreadyMappedToMedicalStoreException;
import com.js.OnlinePharmacy.exception.AddressAlreayMappedToOtherCustomerException;
import com.js.OnlinePharmacy.exception.AddressIdNotFoundException;
import com.js.OnlinePharmacy.exception.AddressMappedToMedicalStore;
import com.js.OnlinePharmacy.exception.AddressMappedToMedicalStoreException;
import com.js.OnlinePharmacy.exception.AddressMappedToStoreException;
import com.js.OnlinePharmacy.exception.CustomerIdNotFoundException;
import com.js.OnlinePharmacy.exception.EmailNotFoundException;
import com.js.OnlinePharmacy.exception.InvalidPasswordException;
import com.js.OnlinePharmacy.exception.PhoneNumberIsNotValidException;
import com.js.OnlinePharmacy.util.ResponseStructure;

@Service
public class CustomerService {

	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private AddressDao addressDao;

	@Autowired
	private ModelMapper modelMapper;
	
	
	public ResponseEntity<ResponseStructure<CustomerDto>> saveCustomer(int addressId, Customer customer) {
		Address dbAddress=addressDao.findAddress(addressId);
	
		if(dbAddress!=null) {
			if(dbAddress.getMedicalStore()!=null) {
				throw new AddressAlreadyMappedToMedicalStoreException("Address is mapped to medical store");
			}
			if(dbAddress.getCustomer()!=null) {
				throw new AddressAlreayMappedToOtherCustomerException("Address id mapped to another customer, try with another address");
			}
			
			dbAddress.setCustomer(customer);
			
			
			List<Address>addresses=new ArrayList<Address>();
			addresses.add(dbAddress);
			customer.setAddresses(addresses);
			
			Customer dbCustomer=customerDao.saveCustomer(customer);
			CustomerDto customerDto=this.modelMapper.map(dbCustomer, CustomerDto.class);
			
			List<AddressDto>addressDtos;
			for(Address add:dbCustomer.getAddresses()) {
				AddressDto addressDto=this.modelMapper.map(add, AddressDto.class);
				addressDtos=new ArrayList<AddressDto>();
				addressDtos.add(addressDto);
				customerDto.setAddresses(addressDtos);
				
				customerDto.setBookingsDtos(null);
				
			}
			ResponseStructure<CustomerDto>structure=new ResponseStructure<CustomerDto>();
			structure.setMessage("Customer added successfully !");
			structure.setStatus(HttpStatus.CREATED.value());
			structure.setData(customerDto);
			
			return new ResponseEntity<ResponseStructure<CustomerDto>>(structure,HttpStatus.CREATED);
			}
			else {
				throw new AddressIdNotFoundException("Sorry ! failed to add customer");
			}
		}
	
	
	

	public ResponseEntity<ResponseStructure<CustomerDto>> updateCustomer(int customerId, Customer customer) {
		Customer dbCustomer=customerDao.updateCustomer(customerId, customer);
		if(dbCustomer!=null) {
			CustomerDto customerDto=this.modelMapper.map(dbCustomer, CustomerDto.class);
			List<AddressDto>addressDtos;
			for(Address add:dbCustomer.getAddresses()) {
				AddressDto addressDto=this.modelMapper.map(add, AddressDto.class);
				addressDtos=new ArrayList<AddressDto>();
				addressDtos.add(addressDto);
				customerDto.setAddresses(addressDtos);
			}
			customerDto.setBookingsDtos(null);
			ResponseStructure<CustomerDto>structure=new ResponseStructure<CustomerDto>();
			structure.setMessage("Customer updated successfully !");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(customerDto);
			
			return new ResponseEntity<ResponseStructure<CustomerDto>>(structure,HttpStatus.OK);
			}
			else {
				throw new CustomerIdNotFoundException("Sorry ! failed to update customer");
			}
			
		}
	
	
	public ResponseEntity<ResponseStructure<CustomerDto>> findCustomer(int customerId) {
		Customer dbCustomer=customerDao.findCustomer(customerId);
		if(dbCustomer!=null) {
			CustomerDto customerDto=this.modelMapper.map(dbCustomer, CustomerDto.class);
			List<AddressDto>addressDtos;
			for(Address add:dbCustomer.getAddresses()) {
				AddressDto addressDto=this.modelMapper.map(add, AddressDto.class);
				addressDtos=new ArrayList<AddressDto>();
				addressDtos.add(addressDto);
				customerDto.setAddresses(addressDtos);
			}
			customerDto.setBookingsDtos(null);
			ResponseStructure<CustomerDto>structure=new ResponseStructure<CustomerDto>();
			structure.setMessage("Customer Fetched successfully !");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(customerDto);
			
			return new ResponseEntity<ResponseStructure<CustomerDto>>(structure,HttpStatus.FOUND);
			}
			else {
				throw new CustomerIdNotFoundException("Sorry ! failed to Fetch customer");
			}
			
		}
	
	public ResponseEntity<ResponseStructure<CustomerDto>> deleteCustomer(int customerId) {
		Customer dbCustomer=customerDao.deleteCustomer(customerId);
		if(dbCustomer!=null) {
			CustomerDto customerDto=this.modelMapper.map(dbCustomer, CustomerDto.class);
			List<AddressDto>addressDtos;
			for(Address add:dbCustomer.getAddresses()) {
				AddressDto addressDto=this.modelMapper.map(add, AddressDto.class);
				addressDtos=new ArrayList<AddressDto>();
				addressDtos.add(addressDto);
				customerDto.setAddresses(addressDtos);
			}
			customerDto.setBookingsDtos(null);
			ResponseStructure<CustomerDto>structure=new ResponseStructure<CustomerDto>();
			structure.setMessage("Customer deleted successfully !");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(customerDto);
			
			return new ResponseEntity<ResponseStructure<CustomerDto>>(structure,HttpStatus.OK);
			}
			else {
				throw new CustomerIdNotFoundException("Sorry ! failed to delete customer");
			}
			
		}




	public ResponseEntity<ResponseStructure<CustomerDto>> loginCustomer(String email, String password) {
		Customer dbCustomer=customerDao.findByEmail(email);
		if(dbCustomer!=null) {
			if(dbCustomer.getPassword().equals(password)) {
				CustomerDto customerDto=this.modelMapper.map(dbCustomer, CustomerDto.class);
				List<AddressDto> addressDtos;
				for(Address add:dbCustomer.getAddresses()) {
					AddressDto addressDto=this.modelMapper.map(add, AddressDto.class);
					addressDtos=new ArrayList<AddressDto>();
					addressDtos.add(addressDto);
					customerDto.setAddresses(addressDtos);
				}
				List<BookingDto> bookingDtos;
				for(Booking book: dbCustomer.getBookings()) {
					BookingDto bookingDto=this.modelMapper.map(book, BookingDto.class);
					bookingDtos=new ArrayList<BookingDto>();
					bookingDtos.add(bookingDto);
					customerDto.setBookingsDtos(bookingDtos);
					
				}
				ResponseStructure<CustomerDto>structure=new ResponseStructure<CustomerDto>();
				structure.setMessage("Login Successful! Welcome to Online Pharmacy");
				structure.setStatus(HttpStatus.ACCEPTED.value());
				structure.setData(customerDto);
				return new ResponseEntity<ResponseStructure<CustomerDto>>(structure,HttpStatus.ACCEPTED);
			
				
			}else {
				throw new InvalidPasswordException("Sorry failed to login");
			}
		}else {
			throw new EmailNotFoundException("Customer is not found for given email");
		}
	}




	public ResponseEntity<ResponseStructure<CustomerDto>> forgotCustomerPassword(String email, long phone,
			String updatedPassword) {
		Customer customer=customerDao.findByEmail(email);
		if(customer!=null) {
			if(customer.getCustomerPhone()==phone) {
				customer.setPassword(updatedPassword);
				Customer dbCustomer=customerDao.updateCustomer(customer.getCustomerId(), customer);
				
				CustomerDto customerDto=this.modelMapper.map(dbCustomer, CustomerDto.class);
				List<AddressDto> addressDtos;
				for(Address add:dbCustomer.getAddresses()) {
					AddressDto addressDto=this.modelMapper.map(add, AddressDto.class);
					addressDtos=new ArrayList<AddressDto>();
					addressDtos.add(addressDto);
					customerDto.setAddresses(addressDtos);
				}
				List<BookingDto> bookingDtos;
				for(Booking book: dbCustomer.getBookings()) {
					BookingDto bookingDto=this.modelMapper.map(book, BookingDto.class);
					bookingDtos=new ArrayList<BookingDto>();
					bookingDtos.add(bookingDto);
					customerDto.setBookingsDtos(bookingDtos);
					
				}
				ResponseStructure<CustomerDto>structure=new ResponseStructure<CustomerDto>();
				structure.setMessage("Login Successful! Welcome to Online Pharmacy");
				structure.setStatus(HttpStatus.ACCEPTED.value());
				structure.setData(customerDto);
				return new ResponseEntity<ResponseStructure<CustomerDto>>(structure,HttpStatus.ACCEPTED);
			
				
			}else {
				throw new PhoneNumberIsNotValidException("Sorry ! failed to reset the password, please enter Registerd PhoneNumber");
			}
		}else {
			throw new EmailNotFoundException("Sorry ! failed to reset the password, please enter valid email");
		}
	}


}
