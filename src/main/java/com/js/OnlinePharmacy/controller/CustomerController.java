package com.js.OnlinePharmacy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.js.OnlinePharmacy.dto.CustomerDto;
import com.js.OnlinePharmacy.entity.Customer;
import com.js.OnlinePharmacy.service.CustomerService;
import com.js.OnlinePharmacy.util.ResponseStructure;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	
	@Autowired
	private CustomerService service;
	
	@Operation(summary =  "Save Customer",  description ="This API is used to save the Customer")
	@ApiResponses(value = {@ApiResponse(responseCode = "201",description = "Successfully created")})
	@PostMapping
	public ResponseEntity<ResponseStructure<CustomerDto>>saveCustomer(@RequestParam int addressId,@RequestBody Customer customer){
    	return service.saveCustomer(addressId, customer);
    }
	
    
    @Operation(summary = "Update Customer", description ="This API is used to update the existing Customer")
	@ApiResponses(value = {@ApiResponse(responseCode = "200",description = "Successfully updated"),
			@ApiResponse(responseCode = "404",description = "In not Found for Customer")})	
	@PutMapping
    public ResponseEntity<ResponseStructure<CustomerDto>>updateCustomer(@RequestParam int customerId, @RequestBody Customer customer){
    	return service.updateCustomer(customerId, customer);
    }
    
    
    @Operation(summary = "Delete Customer", description ="This API is used to delete the existing Customer")
	@ApiResponses(value = {@ApiResponse(responseCode = "200",description = "Successfully deleted"),
			@ApiResponse(responseCode = "404",description = "In not Found for Customer")})	
	@DeleteMapping
    public ResponseEntity<ResponseStructure<CustomerDto>>deleteCustomer(@RequestParam int customerId){
    	return service.deleteCustomer(customerId);
    }
    
    
    @Operation(summary = "Find Customer", description ="This API is used to Find the existing Customer")
	@ApiResponses(value = {@ApiResponse(responseCode = "302",description = "Successfully Found"),
			@ApiResponse(responseCode = "404",description = "In not Found for Customer")})		
	@GetMapping
	public ResponseEntity<ResponseStructure<CustomerDto>>findCustomer(@RequestParam int customerId){
    	return service.findCustomer(customerId);
    }
	
    
    @Operation(summary = "Customer Login", description ="This API is used to Customer Login")
	@ApiResponses(value = {@ApiResponse(responseCode = "200",description = "Successfully Logged In"),
			@ApiResponse(responseCode = "404",description = "Invalid Credentials")})	
	@GetMapping("/login")
    public ResponseEntity<ResponseStructure<CustomerDto>>loginCustomer(@RequestParam String email,@RequestParam String password){
    	return service.loginCustomer(email,password);
    }
    
    
    @Operation(summary = "Forgot Customer Password", description ="This API is used to forgot the Customer password")
	@ApiResponses(value = {@ApiResponse(responseCode = "200",description = "password resettes Successfully"),
			@ApiResponse(responseCode = "404",description = "Invalid Credentials")})	
	@PutMapping("/forgotpassword")
    public ResponseEntity<ResponseStructure<CustomerDto>>forgotCustomerPassword(@RequestParam String email, @RequestParam long phone,@RequestParam String updatedPassword){
    	return service.forgotCustomerPassword(email, phone, updatedPassword);
    }
    
}
