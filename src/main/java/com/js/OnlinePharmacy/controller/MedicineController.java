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

import com.js.OnlinePharmacy.dao.MedicalStoreDao;
import com.js.OnlinePharmacy.entity.MedicalStore;
import com.js.OnlinePharmacy.entity.Medicine;
import com.js.OnlinePharmacy.exception.MedicalStoreIdNotFoundException;
import com.js.OnlinePharmacy.service.MedicineService;
import com.js.OnlinePharmacy.util.ResponseStructure;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/medicine")
public class MedicineController {

	@Autowired
	private MedicineService service;
	
	@Operation(summary =  "Save Medicine",  description ="This API is used to save the Medicine")
	@ApiResponses(value = {@ApiResponse(responseCode = "201",description = "Successfully Saved")})
	@PostMapping
	private ResponseEntity<ResponseStructure<Medicine>> saveMedicine(@RequestBody Medicine medicine,@RequestParam int storeId){
		return service.saveMedicine(medicine,storeId);
	}
	
	
	
	@Operation(summary = "Update Medicine", description ="This API is used to update the existing Medicine")
	@ApiResponses(value = {@ApiResponse(responseCode = "200",description = "Successfully updated"),
			@ApiResponse(responseCode = "404",description = "In not Found for Medicine")})	
	@PutMapping
	private ResponseEntity<ResponseStructure<Medicine>> updateMedicine(@RequestBody Medicine medicine,@RequestParam int medicineId){
		return service.updateMedicine(medicine,medicineId);
	}
	


	@Operation(summary = "Find Medicine", description ="This API is used to Find the existing Medicine")
	@ApiResponses(value = {@ApiResponse(responseCode = "302",description = "Successfully Found"),
			@ApiResponse(responseCode = "404",description = "In not Found for Medicine")})		
	@GetMapping
	private ResponseEntity<ResponseStructure<Medicine>> findMedicine(@RequestParam int medicineId){
		return service.findMedicine(medicineId);
	}
	
	
	
	@Operation(summary = "Delete Medicine", description ="This API is used to delete the existing Medicine")
	@ApiResponses(value = {@ApiResponse(responseCode = "200",description = "Successfully deleted"),
			@ApiResponse(responseCode = "404",description = "In not Found for Medicine")})	
	@DeleteMapping
	private ResponseEntity<ResponseStructure<Medicine>> updateMedicine(@RequestParam int medicineId){
		return service.deleteMedicine(medicineId);
	}
}
