package com.js.OnlinePharmacy.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.js.OnlinePharmacy.dao.AdminDao;
import com.js.OnlinePharmacy.dto.AdminDto;
import com.js.OnlinePharmacy.entity.Admin;
import com.js.OnlinePharmacy.entity.MedicalStore;
import com.js.OnlinePharmacy.entity.Staff;
import com.js.OnlinePharmacy.exception.AdminIdNotFoundException;
import com.js.OnlinePharmacy.exception.EmailNotFoundException;
import com.js.OnlinePharmacy.exception.InvalidPasswordException;
import com.js.OnlinePharmacy.exception.PhoneNumberIsNotValidException;
import com.js.OnlinePharmacy.util.ResponseStructure;

@Service
public class AdminService {
	
	@Autowired
	private AdminDao adminDao;
	
	@Autowired
	private ModelMapper modelMapper;

	public ResponseEntity<ResponseStructure<AdminDto>> saveAdmin(Admin admin) {
		Admin dbAdmin=adminDao.saveAdmin(admin);
		ResponseStructure<AdminDto>structure=new ResponseStructure<AdminDto>();
		structure.setMessage("Admin saved successFully !");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(this.modelMapper.map(dbAdmin, AdminDto.class));
		return new ResponseEntity<ResponseStructure<AdminDto>>(structure,HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<AdminDto>>findAdmin(int adminId){
		Admin dbAdmin=adminDao.findAdmin(adminId);
		if(dbAdmin!=null) {
			ResponseStructure<AdminDto>structure=new ResponseStructure<AdminDto>();
			structure.setMessage("Admin fetched successfully !");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(this.modelMapper.map(dbAdmin, AdminDto.class));
			return new ResponseEntity<ResponseStructure<AdminDto>>(structure,HttpStatus.FOUND);
		}else {
			throw new AdminIdNotFoundException("Sorry ! failed to fetch admin");
				}
	}
	
	
	public ResponseEntity<ResponseStructure<AdminDto>>deleteAdmin(int adminId){
		Admin dbAdmin=adminDao.deleteAdmin(adminId);
		if(dbAdmin!=null) {
			for(MedicalStore store :dbAdmin.getMedicalStores()) {
				store.setAdmin(null);
			}
			for(Staff staff:dbAdmin.getStaffs()) {
				staff.setAdmin(null);
			}
			ResponseStructure<AdminDto>structure=new ResponseStructure<AdminDto>();
			structure.setMessage("Admin deleted successfully !");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(this.modelMapper.map(dbAdmin, AdminDto.class));
			return new ResponseEntity<ResponseStructure<AdminDto>>(structure,HttpStatus.OK);
		}else {
			throw new AdminIdNotFoundException("Sorry ! failed to delete admin");
				}
	}
	
	public ResponseEntity<ResponseStructure<AdminDto>>updateAdmin(int adminId,Admin admin){
		Admin dbAdmin=adminDao.updateAdmin(adminId,admin);
		if(dbAdmin!=null) {
			ResponseStructure<AdminDto>structure=new ResponseStructure<AdminDto>();
			structure.setMessage("updated admin successfully !");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(this.modelMapper.map(dbAdmin, AdminDto.class));
			return new ResponseEntity<ResponseStructure<AdminDto>>(structure,HttpStatus.OK);
		}else {
			throw new AdminIdNotFoundException("Sorry ! failed to update admin");

		}
	}

	
	public ResponseEntity<ResponseStructure<AdminDto>> loginAdmin(String email, String password) {
		Admin admin=adminDao.findByEmail(email);
		if(admin!=null) {
			if(admin.getPassword().equals(password)) {
			ResponseStructure<AdminDto>structure=new ResponseStructure<AdminDto>();
			structure.setMessage("Login successful !");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(this.modelMapper.map(admin, AdminDto.class));
			return new ResponseEntity<ResponseStructure<AdminDto>>(structure,HttpStatus.OK);
		}else {
			throw new InvalidPasswordException("Sorry failed to login , enter correct password");
		}
		}
		else {
			throw new EmailNotFoundException("Sorry ! failed to login, try with valid Email");
		}
	}

	public ResponseEntity<ResponseStructure<AdminDto>> forgotAdminPassword(String email, long phone,
			String updatedPassword) {
		Admin admin=adminDao.findByEmail(email);
		if(admin!=null) {
			if(admin.getAdminPhone()==phone) {
				admin.setPassword(updatedPassword);
				Admin dbAdmin=adminDao.updateAdmin(admin.getAdminId(), admin);
				ResponseStructure<AdminDto>structure=new ResponseStructure<AdminDto>();
				structure.setMessage(" Reseted Password successfully !");
				structure.setStatus(HttpStatus.OK.value());
				structure.setData(this.modelMapper.map(dbAdmin, AdminDto.class));
				return new ResponseEntity<ResponseStructure<AdminDto>>(structure,HttpStatus.OK);
				
			}else {
				throw new PhoneNumberIsNotValidException("sorry failed to resset passsword, try with registerd phoneNumber");
			}
		}else {
			throw new EmailNotFoundException("sorry failed to resset passsword, try with correct email");
		}
	}
	
	

	
	
	
}
