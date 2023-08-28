package com.js.OnlinePharmacy.exception;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.js.OnlinePharmacy.util.ResponseStructure;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class OnlinePharmacyExceptionalHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> AddressIdNotFound(AddressIdNotFoundException ex){
		ResponseStructure<String>structure=new ResponseStructure<String>();
		structure.setMessage("Id not found for address");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMsg());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> AdminIdNotFound(AdminIdNotFoundException ex){
		ResponseStructure<String>structure=new ResponseStructure<String>();
		structure.setMessage("Id not found for admin");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMsg());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	

	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> MedicalStoreIdNotFound(MedicalStoreIdNotFoundException ex){
		ResponseStructure<String>structure=new ResponseStructure<String>();
		structure.setMessage("Id not found for Medical store");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMsg());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> StaffIdNotFound(StaffIdNotFoundException ex){
		ResponseStructure<String>structure=new ResponseStructure<String>();
		structure.setMessage("Id not found for Staff");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMsg());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> MedicineIdNotFound(MedicineIdNotFoundException ex){
		ResponseStructure<String>structure=new ResponseStructure<String>();
		structure.setMessage("Id not found for Medicine");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMsg());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	

	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> AddressAlreayMappedToOtherCustomer(AddressAlreayMappedToOtherCustomerException ex){
		ResponseStructure<String>structure=new ResponseStructure<String>();
		structure.setMessage("Address is Mapped to another customer");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMsg());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> CustomerIdNotFound(CustomerIdNotFoundException ex){
		ResponseStructure<String>structure=new ResponseStructure<String>();
		structure.setMessage("Id not found for customer");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMsg());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> BookingIdNotFound(BookingIdNotFoundException ex){
		ResponseStructure<String>structure=new ResponseStructure<String>();
		structure.setMessage("Id not found for Booking");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMsg());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> AddressMappedToMedicalStore(AddressMappedToMedicalStoreException ex){
		ResponseStructure<String>structure=new ResponseStructure<String>();
		structure.setMessage("Address is Mapped to medical store");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMsg());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> AddressAlreayMappedToCustomer(AddressAlreayMappedToCustomerException ex){
		ResponseStructure<String>structure=new ResponseStructure<String>();
		structure.setMessage("Address is Mapped to a customer");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMsg());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> AddressAlreayMappedMedicalStore(AddressAlreadyMappedToMedicalStoreException ex){
		ResponseStructure<String>structure=new ResponseStructure<String>();
		structure.setMessage("Address is adready Mapped to a medical Store");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMsg());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> AddressMappedToMedicalStore(AddressMappedToMedicalStore ex){
		ResponseStructure<String>structure=new ResponseStructure<String>();
		structure.setMessage("Address is  Mapped to a medical Store");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMsg());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> AddressMappedToStore(AddressMappedToStoreException ex){
		ResponseStructure<String>structure=new ResponseStructure<String>();
		structure.setMessage("Address is  Mapped to a medical Store");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMsg());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> BookingCantCancelledNow(BookingCantCancelledNow ex){
		ResponseStructure<String>structure=new ResponseStructure<String>();
		structure.setMessage("Booking cant't cancel now");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMsg());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> BookingAlreadyCancelled(BookingAlreadyCancelledException ex){
		ResponseStructure<String>structure=new ResponseStructure<String>();
		structure.setMessage("Booking is already cancelled");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMsg());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> BookingDelivered(BookingDeliveredException ex){
		ResponseStructure<String>structure=new ResponseStructure<String>();
		structure.setMessage("Booking is already Delivered");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMsg());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> EmailNotFound(EmailNotFoundException ex){
		ResponseStructure<String>structure=new ResponseStructure<String>();
		structure.setMessage("Invalid Email ! try with different email");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMsg());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> InvalidPassword(InvalidPasswordException ex){
		ResponseStructure<String>structure=new ResponseStructure<String>();
		structure.setMessage("Invalid password ! try again");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMsg());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> StoreIsAlreadyMappedToStaff(StoreIsAlreadyMappedToStaff ex){
		ResponseStructure<String>structure=new ResponseStructure<String>();
		structure.setMessage("Store is already mapped to staff");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMsg());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> PhoneNumberIsNotValid(PhoneNumberIsNotValidException ex){
		ResponseStructure<String>structure=new ResponseStructure<String>();
		structure.setMessage("Incorrect PhoneNumber ! Enter valid PhoneNumber");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMsg());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<Object>> HandleConstraintViolationException(ConstraintViolationException ex){
		ResponseStructure<Object>structure=new ResponseStructure<Object>();
		HashMap<String, String>hashmap=new HashMap<String, String>();
		
		for(ConstraintViolation<?>violation:ex.getConstraintViolations()) {
			String field=violation.getPropertyPath().toString();
			String msg=violation.getMessage();
			hashmap.put(field, msg);
		}
		structure.setMessage("Add Proper details");
		structure.setStatus(HttpStatus.FORBIDDEN.value());
		structure.setData(hashmap);
		return new ResponseEntity<ResponseStructure<Object>>(structure,HttpStatus.BAD_REQUEST);
		}
}
