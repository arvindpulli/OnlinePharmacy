package com.js.OnlinePharmacy.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.js.OnlinePharmacy.entity.Medicine;
import com.js.OnlinePharmacy.repository.MedicineRepo;

@Repository
public class MedicineDao {

	@Autowired
	private MedicineRepo repo;
	
	public Medicine saveMedicine(Medicine medicine) {
		return repo.save(medicine);
	}
	
	public Medicine findMedicine(int medicineId) {
	Optional<Medicine> optional=repo.findById(medicineId);
	if(optional.isPresent()) {
		return optional.get();
	}return null;
	}
	
	public Medicine deleteMedicine(int medicineId) {
		Optional<Medicine> optional=repo.findById(medicineId);
		if(optional.isPresent()) {
			Medicine medicine=optional.get();
			repo.delete(medicine);
			return medicine;
		}return null;}
	
	public Medicine updateMedicine(Medicine medicine, int medicineId) {
		Optional<Medicine> optional=repo.findById(medicineId);
		if(optional.isPresent()) {
			medicine.setMedicineId(medicineId);
			medicine.setMedicalStore(optional.get().getMedicalStore());
			repo.save(medicine);
			return medicine;
		}return null;
	}
}
