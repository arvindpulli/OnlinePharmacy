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

import com.js.OnlinePharmacy.dto.AdminDto;
import com.js.OnlinePharmacy.entity.Admin;
import com.js.OnlinePharmacy.service.AdminService;
import com.js.OnlinePharmacy.util.ResponseStructure;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService service;
	
	@Operation(summary =  "Save Admin",  description ="This API is used to save the Admin")
	@ApiResponses(value = {@ApiResponse(responseCode = "201",description = "Successfully created")})
	@PostMapping
	public ResponseEntity<ResponseStructure<AdminDto>>saveAdmin(@RequestBody Admin admin){
		return service.saveAdmin(admin);
	}
	
	
	@Operation(summary = "Find Admin", description ="This API is used to Find the existing Admin")
	@ApiResponses(value = {@ApiResponse(responseCode = "302",description = "Successfully Found"),
			@ApiResponse(responseCode = "404",description = "In not Found for Admin")})		
	@GetMapping
	public ResponseEntity<ResponseStructure<AdminDto>>findAdmin(@RequestParam int adminId){
		return service.findAdmin(adminId);
	}
	
	
	
	@Operation(summary = "Delete Admin", description ="This API is used to delete the existing Admin")
	@ApiResponses(value = {@ApiResponse(responseCode = "200",description = "Successfully deleted"),
			@ApiResponse(responseCode = "404",description = "In not Found for Admin")})	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<AdminDto>>deleteAdmin(@RequestParam int adminId){
		return service.deleteAdmin(adminId);
	}
	
	@Operation(summary = "Update Admin", description ="This API is used to update the existing Admin")
	@ApiResponses(value = {@ApiResponse(responseCode = "200",description = "Successfully updated"),
			@ApiResponse(responseCode = "404",description = "In not Found for Admin")})	
	@PutMapping
	public ResponseEntity<ResponseStructure<AdminDto>>updateAdmin(@RequestBody Admin admin,@RequestParam int adminId ){
		return service.updateAdmin(adminId,admin);
	}
	
	
	
	@Operation(summary = "Admin Login", description ="This API is used to Admin Login")
	@ApiResponses(value = {@ApiResponse(responseCode = "200",description = "Successfully Logged In"),
			@ApiResponse(responseCode = "404",description = "Invalid Credentials")})	
	@GetMapping("/login")
	public ResponseEntity<ResponseStructure<AdminDto>>loginAdmin(@RequestParam String email,@RequestParam String password ){
		return service.loginAdmin(email,password);
	}
	
	@Operation(summary = "Forgot Admin Password", description ="This API is used to forgot the Admin password")
	@ApiResponses(value = {@ApiResponse(responseCode = "200",description = "password resettes Successfully"),
			@ApiResponse(responseCode = "404",description = "Invalid Credentials")})	
	@PutMapping("/forgotpassword")
	public ResponseEntity<ResponseStructure<AdminDto>>fotgotAdminPassword(@RequestParam String email,@RequestParam long phone,@RequestParam String updatedPassword ){
		return service.forgotAdminPassword(email,phone,updatedPassword);
	}
}
