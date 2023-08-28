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

import com.js.OnlinePharmacy.dto.MedicalStoreDto;
import com.js.OnlinePharmacy.entity.MedicalStore;
import com.js.OnlinePharmacy.service.MedicalStoreService;
import com.js.OnlinePharmacy.util.ResponseStructure;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/medicalstore")
public class MedicalStoreController {

	@Autowired
	private MedicalStoreService service;
	
	@Operation(summary =  "Save MedicalStore",  description ="This API is used to save the MedicalStore")
	@ApiResponses(value = {@ApiResponse(responseCode = "201",description = "Successfully created")})
	@PostMapping
	public ResponseEntity<ResponseStructure<MedicalStoreDto>>saveMedicalStore(@RequestBody MedicalStoreDto storeDto,@RequestParam int adminId,@RequestParam int addressId){
		return service.saveMedicalStore(adminId,addressId,storeDto);
		
	}
	
	
	
	@Operation(summary = "Find MedicalStore", description ="This API is used to Find the existing MedicalStore")
	@ApiResponses(value = {@ApiResponse(responseCode = "302",description = "Successfully Found"),
			@ApiResponse(responseCode = "404",description = "In not Found for MedicalStore")})		
	@GetMapping
	public ResponseEntity<ResponseStructure<MedicalStoreDto>>findMedicalStore(@RequestParam int storeId ){
		return service.findMedicalStore(storeId);
		
	}
	
	
	
	@Operation(summary = "Delete MedicalStore", description ="This API is used to delete the existing MedicalStore")
	@ApiResponses(value = {@ApiResponse(responseCode = "200",description = "Successfully deleted"),
			@ApiResponse(responseCode = "404",description = "In not Found for MedicalStore")})	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<MedicalStore>>deleteMedicalStore(@RequestParam int storeId ){
		return service.deleteMedicalStore(storeId);
		
	}
	
	@Operation(summary = "Update MedicalStore", description ="This API is used to update the existing MedicalStore")
	@ApiResponses(value = {@ApiResponse(responseCode = "200",description = "Successfully updated"),
			@ApiResponse(responseCode = "404",description = "In not Found for MedicalStore")})	
	@PutMapping
	public ResponseEntity<ResponseStructure<MedicalStoreDto>>updateMedicalStore(@RequestParam int storeId,@RequestBody MedicalStore store ){
		return service.updateMedicalStore(storeId,store);
		
	}
		
	
}
