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

import com.js.OnlinePharmacy.dto.StaffDto;
import com.js.OnlinePharmacy.entity.Staff;
import com.js.OnlinePharmacy.service.StaffService;
import com.js.OnlinePharmacy.util.ResponseStructure;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.Getter;

@RestController
@RequestMapping("staff")
public class StaffController {

	@Autowired
	private StaffService service;
	
	
	
	@Operation(summary =  "Save Staff",  description ="This API is used to save the staff")
	@ApiResponses(value = {@ApiResponse(responseCode = "201",description = "Successfully created")})
	@PostMapping
	public ResponseEntity<ResponseStructure<StaffDto>>saveStaff(@RequestBody Staff staff,@RequestParam int adminId,@RequestParam int storeId){
		return service.saveStaff(staff,storeId, adminId);
	}
	
	
	
	@Operation(summary = "Update Staff", description ="This API is used to update the existing staff")
	@ApiResponses(value = {@ApiResponse(responseCode = "200",description = "Successfully updated"),
			@ApiResponse(responseCode = "404",description = "In not Found for staff")})	
	@PutMapping
	public ResponseEntity<ResponseStructure<StaffDto>>updateStaff(@RequestBody Staff staff, @RequestParam int staffId){
		return service.updateStaff(staff, staffId);
	}
	
	
	
	@Operation(summary = "Find Staff", description ="This API is used to Find the existing staff")
	@ApiResponses(value = {@ApiResponse(responseCode = "302",description = "Successfully Found"),
			@ApiResponse(responseCode = "404",description = "In not Found for staff")})		
	@GetMapping
	public ResponseEntity<ResponseStructure<StaffDto>>findStaff(@RequestParam int staffId){
		return service.findStaff(staffId);
	}
	
	
	@Operation(summary = "Delete Staff", description ="This API is used to delete the existing staff")
	@ApiResponses(value = {@ApiResponse(responseCode = "200",description = "Successfully deleted"),
			@ApiResponse(responseCode = "404",description = "In not Found for staff")})	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<StaffDto>>deleteStaff(@RequestParam int staffId){
		return service.deleteStaff(staffId);
	}
	
	
	
	@Operation(summary = "Staff Login", description ="This API is used to Staff Login")
	@ApiResponses(value = {@ApiResponse(responseCode = "200",description = "Successfully Logged In"),
			@ApiResponse(responseCode = "404",description = "Invalid Credentials")})	
	@GetMapping("/login")
	public ResponseEntity<ResponseStructure<StaffDto>>loginStaff(@RequestParam String email,@RequestParam String password){
		return service.loginStaff(email,password);
	}
	
	
	
	@Operation(summary = "Forgot Staff Password", description ="This API is used to forgot the staff password")
	@ApiResponses(value = {@ApiResponse(responseCode = "200",description = "password resettes Successfully"),
			@ApiResponse(responseCode = "404",description = "Invalid Credentials")})	
	@PutMapping("/forgotpassword")
	public ResponseEntity<ResponseStructure<StaffDto>>forgotStaffPassword(@RequestParam String email,@RequestParam long phone,@RequestParam String updatedPassword){
		return service.forgotStaffPassword(email, phone, updatedPassword);
	}
}
