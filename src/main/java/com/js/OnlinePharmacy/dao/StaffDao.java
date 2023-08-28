package com.js.OnlinePharmacy.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.js.OnlinePharmacy.entity.Staff;
import com.js.OnlinePharmacy.repository.StaffRepo;

@Repository
public class StaffDao {

	@Autowired
	private StaffRepo repo;
	
	public Staff saveStaff(Staff staff) {
		return repo.save(staff);
		}

	
	
	public Staff updateStaff(int staffId, Staff staff) {
		Optional<Staff>optional=repo.findById(staffId);
		if(optional.isPresent()) {
			Staff existingStaff=optional.get();
			staff.setStaffId(staffId);
			staff.setAdmin(existingStaff.getAdmin());
			staff.setMedicalStore(existingStaff.getMedicalStore());
			
			repo.save(staff);
			return staff;
			
		}return null;
	}



	public Staff findStaff(int staffId) {
		Optional<Staff>optional=repo.findById(staffId);
		if(optional.isPresent()) {
			return optional.get();
		}else {
			return null;
		}
	}
	
	public Staff deleteStaff(int staffId) {
		Optional<Staff>optional=repo.findById(staffId);
		if(optional.isPresent()) {
			repo.delete(optional.get());
			return optional.get();
		}else {
			return null;
		}
	}



	public Staff findByEmail(String email) {
		Optional<Staff>optional=repo.findByEmail(email);
		if(optional.isPresent()) {
			return optional.get();
		}return null;
	}
	
	
	
	
	
}
