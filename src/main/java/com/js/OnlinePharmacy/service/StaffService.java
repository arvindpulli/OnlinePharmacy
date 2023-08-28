package com.js.OnlinePharmacy.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.js.OnlinePharmacy.dao.AdminDao;
import com.js.OnlinePharmacy.dao.MedicalStoreDao;
import com.js.OnlinePharmacy.dao.StaffDao;
import com.js.OnlinePharmacy.dto.AdminDto;
import com.js.OnlinePharmacy.dto.MedicalStoreDto;
import com.js.OnlinePharmacy.dto.StaffDto;
import com.js.OnlinePharmacy.entity.Admin;
import com.js.OnlinePharmacy.entity.MedicalStore;
import com.js.OnlinePharmacy.entity.Staff;
import com.js.OnlinePharmacy.exception.AdminIdNotFoundException;
import com.js.OnlinePharmacy.exception.EmailNotFoundException;
import com.js.OnlinePharmacy.exception.InvalidPasswordException;
import com.js.OnlinePharmacy.exception.MedicalStoreIdNotFoundException;
import com.js.OnlinePharmacy.exception.PhoneNumberIsNotValidException;
import com.js.OnlinePharmacy.exception.StaffIdNotFoundException;
import com.js.OnlinePharmacy.exception.StoreIsAlreadyMappedToStaff;
import com.js.OnlinePharmacy.util.ResponseStructure;

@Service
public class StaffService {

	@Autowired
	private StaffDao staffDao;
	
	@Autowired
	private AdminDao adminDao;
	
	@Autowired
	private MedicalStoreDao storeDao;
	
	@Autowired
	private ModelMapper modelMapper;

	public ResponseEntity<ResponseStructure<StaffDto>> saveStaff(Staff staff, int storeId, int adminId) {
		Admin admin=adminDao.findAdmin(adminId);
		if(admin!=null) {
			staff.setAdmin(admin);
			MedicalStore store=storeDao.findMedicalStore(storeId);
			if(store!=null) {
				if(store.getStaff()!=null) {
					throw new StoreIsAlreadyMappedToStaff("Store is alreay mapped to staff, try with different store");
				}
				else {
				staff.setMedicalStore(store);
				Staff dbStaff=staffDao.saveStaff(staff);
				
				AdminDto adminDto=this.modelMapper.map(admin, AdminDto.class);
				MedicalStoreDto storeDto=this.modelMapper.map(store, MedicalStoreDto.class);
				
				StaffDto staffDto=this.modelMapper.map(dbStaff, StaffDto.class);
				staffDto.setAdminDto(adminDto);
				staffDto.setMedicalStoreDto(storeDto);
				
				
				ResponseStructure<StaffDto>structure=new ResponseStructure<StaffDto>();
				structure.setMessage("Staff added successfully !");
				structure.setStatus(HttpStatus.CREATED.value());
				structure.setData(staffDto);
				
				return new ResponseEntity<ResponseStructure<StaffDto>>(structure,HttpStatus.CREATED);
			}
			}
			else {
				throw new MedicalStoreIdNotFoundException("Sorry ! failed to add satff");
			}
		}else {
			throw new AdminIdNotFoundException("Sorry ! failed to add satff");
		}
	}

	public ResponseEntity<ResponseStructure<StaffDto>> updateStaff(Staff staff, int staffId) {
		Staff dbStaff=staffDao.updateStaff(staffId, staff);
		if(dbStaff!=null) {
			
			StaffDto staffDto=this.modelMapper.map(dbStaff, StaffDto.class);
			staffDto.setAdminDto(this.modelMapper.map(dbStaff.getAdmin(), AdminDto.class));
			staffDto.setMedicalStoreDto(this.modelMapper.map(dbStaff.getMedicalStore(), MedicalStoreDto.class));
			
			ResponseStructure<StaffDto>structure=new ResponseStructure<StaffDto>();
			structure.setMessage("Staff updated successfully !");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(staffDto);
			return new ResponseEntity<ResponseStructure<StaffDto>>(structure,HttpStatus.OK);

		}else {
			throw new StaffIdNotFoundException("Sorry ! failed to update staff");
		}
	}

	
	
	public ResponseEntity<ResponseStructure<StaffDto>> findStaff(int staffId) {
		Staff dbStaff=staffDao.findStaff(staffId);
		if(dbStaff!=null) {
			StaffDto staffDto=this.modelMapper.map(dbStaff, StaffDto.class);
			staffDto.setAdminDto(this.modelMapper.map(dbStaff.getAdmin(), AdminDto.class));
			staffDto.setMedicalStoreDto(this.modelMapper.map(dbStaff.getMedicalStore(), MedicalStoreDto.class));
			
			ResponseStructure<StaffDto>structure=new ResponseStructure<StaffDto>();
			structure.setMessage("Staff Fetched successfully !");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(staffDto);
			return new ResponseEntity<ResponseStructure<StaffDto>>(structure,HttpStatus.FOUND);

		}else {
			throw new StaffIdNotFoundException("Sorry ! failed to fetch staff");
		}
		}
	
	
	public ResponseEntity<ResponseStructure<StaffDto>> deleteStaff(int staffId) {
		Staff dbStaff=staffDao.deleteStaff(staffId);
		if(dbStaff!=null) {
			StaffDto staffDto=this.modelMapper.map(dbStaff, StaffDto.class);
			staffDto.setAdminDto(this.modelMapper.map(dbStaff.getAdmin(), AdminDto.class));
			staffDto.setMedicalStoreDto(this.modelMapper.map(dbStaff.getMedicalStore(), MedicalStoreDto.class));
			
			ResponseStructure<StaffDto>structure=new ResponseStructure<StaffDto>();
			structure.setMessage("Staff deleted successfully !");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(staffDto);
			return new ResponseEntity<ResponseStructure<StaffDto>>(structure,HttpStatus.OK);

		}else {
			throw new StaffIdNotFoundException("Sorry ! failed to delete staff");
		}
		}

	
	public ResponseEntity<ResponseStructure<StaffDto>> loginStaff(String email, String password) {
		Staff staff=staffDao.findByEmail(email);
		if(staff!=null) {
			if(staff.getStaffPassword().equals(password)) {
				
			
			StaffDto staffDto=this.modelMapper.map(staff, StaffDto.class);
			staffDto.setAdminDto(this.modelMapper.map(staff.getAdmin(), AdminDto.class));
			staffDto.setMedicalStoreDto(this.modelMapper.map(staff.getMedicalStore(), MedicalStoreDto.class));
			
			ResponseStructure<StaffDto>structure=new ResponseStructure<StaffDto>();
			structure.setStatus(HttpStatus.OK.value());
			structure.setMessage("Login successful");
			structure.setData(staffDto);
			
			return new ResponseEntity<ResponseStructure<StaffDto>>(structure,HttpStatus.OK);
		}else {
			throw new InvalidPasswordException("Sorry failed to login, enter corrent password");
		}
		}
		else {
			throw new EmailNotFoundException(" sorry ! failed to login, enter valid email");
		}
		
		
	}

	public ResponseEntity<ResponseStructure<StaffDto>> forgotStaffPassword(String email, long phone,
			String updatedPassword) {
		
		Staff staff=staffDao.findByEmail(email);
		if(staff!=null) {
			if(staff.getStaffPhone()==phone) {
				staff.setStaffPassword(updatedPassword);
				Staff dbStaff=staffDao.updateStaff(staff.getStaffId(), staff);
				
				StaffDto staffDto=this.modelMapper.map(dbStaff, StaffDto.class);
				staffDto.setAdminDto(this.modelMapper.map(dbStaff.getAdmin(), AdminDto.class));
				staffDto.setMedicalStoreDto(this.modelMapper.map(dbStaff.getMedicalStore(), MedicalStoreDto.class));
				
				ResponseStructure<StaffDto>structure=new ResponseStructure<StaffDto>();
				structure.setMessage("password reseted successfully !");
				structure.setStatus(HttpStatus.OK.value());
				structure.setData(staffDto);
				return new ResponseEntity<ResponseStructure<StaffDto>>(structure,HttpStatus.OK);
		
			}else {
				throw new  PhoneNumberIsNotValidException("Sorry failed to reset the password ! try with registerd phone number");
			}
			
		}else {
			throw new EmailNotFoundException("sorry failed to reset password ! try with different email");
		}
		
		
	}
	
	
		
	
	
}
