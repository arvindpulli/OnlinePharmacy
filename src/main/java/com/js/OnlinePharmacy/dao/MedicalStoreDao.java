package com.js.OnlinePharmacy.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.js.OnlinePharmacy.entity.Address;
import com.js.OnlinePharmacy.entity.Admin;
import com.js.OnlinePharmacy.entity.MedicalStore;
import com.js.OnlinePharmacy.entity.Staff;
import com.js.OnlinePharmacy.repository.MedicalStoreRepo;

@Repository
public class MedicalStoreDao {

	@Autowired
	private MedicalStoreRepo repo;
	
	public MedicalStore saveMedical(MedicalStore store) {
		return repo.save(store);
	}

	public MedicalStore findMedicalStore(int storeId) {
		Optional<MedicalStore> optional=repo.findById(storeId);
		if(optional.isPresent()) {
			return optional.get();
		}return null;
	}
	
	public MedicalStore deleteMedicalStore(int storeId) {
		Optional<MedicalStore> optional=repo.findById(storeId);
		if(optional.isPresent()) {
			MedicalStore store=optional.get();
			store.setAddress(null);
			store.setAdmin(null);
			store.setStaff(null);
			repo.delete(optional.get());
			return optional.get();
		}return null;
	}
	
	
	public MedicalStore updateMedicalStore(int storeId,MedicalStore store) {
		Optional<MedicalStore> optional=repo.findById(storeId);
		if(optional.isPresent()) {
			store.setStoreId(storeId);
			store.setAdmin(optional.get().getAdmin());
			store.setAddress(optional.get().getAddress());
		return repo.save(store);
		}return null;}
}
