package com.js.OnlinePharmacy.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.js.OnlinePharmacy.dao.MedicalStoreDao;
import com.js.OnlinePharmacy.dao.MedicineDao;
import com.js.OnlinePharmacy.entity.MedicalStore;
import com.js.OnlinePharmacy.entity.Medicine;
import com.js.OnlinePharmacy.exception.MedicalStoreIdNotFoundException;
import com.js.OnlinePharmacy.exception.MedicineIdNotFoundException;
import com.js.OnlinePharmacy.util.ResponseStructure;

@Service
public class MedicineService {

	@Autowired
	private MedicineDao medicineDao;
	
	@Autowired
	private MedicalStoreDao storeDao;
	
	@Autowired
	private ModelMapper modelMapper;

	public ResponseEntity<ResponseStructure<Medicine>> saveMedicine(Medicine medicine, int storeId) {
		MedicalStore store=storeDao.findMedicalStore(storeId);
		if(store!=null) {
			medicine.setMedicalStore(store);
			Medicine dbMedicine=medicineDao.saveMedicine(medicine);
			
			ResponseStructure<Medicine>structure=new ResponseStructure<Medicine>();
			structure.setMessage("Medicine added successfully !");
			structure.setStatus(HttpStatus.CREATED.value());
			structure.setData(dbMedicine);
			
			return new ResponseEntity<ResponseStructure<Medicine>>(structure,HttpStatus.CREATED);
		}else {
			throw new MedicalStoreIdNotFoundException("Sorry ! failed to add medicine");
		}
	}

	public ResponseEntity<ResponseStructure<Medicine>> updateMedicine(Medicine medicine, int medicineId) {
		Medicine dbMedicine=medicineDao.updateMedicine(medicine, medicineId);
		if(dbMedicine!=null) {
			ResponseStructure<Medicine>structure=new ResponseStructure<Medicine>();
			structure.setMessage("Medicine updatedd successfully !");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(dbMedicine);
			
			return new ResponseEntity<ResponseStructure<Medicine>>(structure,HttpStatus.OK);
			
		}else {
			throw new MedicineIdNotFoundException("Sorry ! failed to update medicine");
		}	
	}

	public ResponseEntity<ResponseStructure<Medicine>> findMedicine(int medicineId) {
		Medicine dbMedicine=medicineDao.findMedicine(medicineId);
		if(dbMedicine!=null) {
			ResponseStructure<Medicine>structure=new ResponseStructure<Medicine>();
			structure.setMessage("Medicine Fetched successfully !");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(dbMedicine);
			
			return new ResponseEntity<ResponseStructure<Medicine>>(structure,HttpStatus.FOUND);
			
		}else {
			throw new MedicineIdNotFoundException("Sorry ! failed to Fetch medicine");
		}	
	}
	
	public ResponseEntity<ResponseStructure<Medicine>> deleteMedicine(int medicineId) {
		Medicine dbMedicine=medicineDao.deleteMedicine(medicineId);
		if(dbMedicine!=null) {
			ResponseStructure<Medicine>structure=new ResponseStructure<Medicine>();
			structure.setMessage("Medicine deleted successfully !");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(dbMedicine);
			
			return new ResponseEntity<ResponseStructure<Medicine>>(structure,HttpStatus.OK);
			
		}else {
			throw new MedicineIdNotFoundException("Sorry ! failed to delete medicine");
		}	
	}
	
	
}
