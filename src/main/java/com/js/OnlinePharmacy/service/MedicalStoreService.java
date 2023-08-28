package com.js.OnlinePharmacy.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.js.OnlinePharmacy.dao.AddressDao;
import com.js.OnlinePharmacy.dao.AdminDao;
import com.js.OnlinePharmacy.dao.MedicalStoreDao;
import com.js.OnlinePharmacy.dto.AddressDto;
import com.js.OnlinePharmacy.dto.AdminDto;
import com.js.OnlinePharmacy.dto.MedicalStoreDto;
import com.js.OnlinePharmacy.dto.StaffDto;
import com.js.OnlinePharmacy.entity.Address;
import com.js.OnlinePharmacy.entity.Admin;
import com.js.OnlinePharmacy.entity.MedicalStore;
import com.js.OnlinePharmacy.entity.Staff;
import com.js.OnlinePharmacy.exception.AddressAlreadyMappedToMedicalStoreException;
import com.js.OnlinePharmacy.exception.AddressAlreayMappedToCustomerException;
import com.js.OnlinePharmacy.exception.AddressAlreayMappedToOtherCustomerException;
import com.js.OnlinePharmacy.exception.AddressIdNotFoundException;
import com.js.OnlinePharmacy.exception.AdminIdNotFoundException;
import com.js.OnlinePharmacy.exception.MedicalStoreIdNotFoundException;
import com.js.OnlinePharmacy.util.ResponseStructure;

@Service
public class MedicalStoreService {

	@Autowired
	private MedicalStoreDao storeDao;
	
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private AdminDao adminDao;
	
	@Autowired
	private AddressDao addressDao;
	
	public ResponseEntity<ResponseStructure<MedicalStoreDto>> saveMedicalStore(int adminId, int addressId,
			MedicalStoreDto storeDto) {
		MedicalStore store=this.modelMapper.map(storeDto, MedicalStore.class);
		Admin admin=adminDao.findAdmin(adminId);
		if(admin!=null) {
			store.setAdmin(admin);
			Address address=addressDao.findAddress(addressId);
			if(address!=null) {
				if(address.getCustomer()!=null) {
					throw new AddressAlreayMappedToCustomerException("Given address is mapped to a customer ! try with different address");
				}
				if(address.getMedicalStore()!=null) {
					throw new AddressAlreadyMappedToMedicalStoreException("Given address is already mapped to a medical Store ! try with different address");
				}
				
				
				store.setAddress(address);
				address.setMedicalStore(store);
				
				
				MedicalStore dbStore=storeDao.saveMedical(store);
				
				Address medicalStoreAddress=dbStore.getAddress();
				AddressDto addressDto=this.modelMapper.map(medicalStoreAddress, AddressDto.class);
				
				Admin medicalStoreAdmin=dbStore.getAdmin();
				AdminDto adminDto=this.modelMapper.map(medicalStoreAdmin, AdminDto.class);
						
				MedicalStoreDto dbStoreDto=this.modelMapper.map(dbStore, MedicalStoreDto.class);
				dbStoreDto.setAddressDto(addressDto);
				dbStoreDto.setAdminDto(adminDto);
				
				ResponseStructure<MedicalStoreDto>structure=new ResponseStructure<MedicalStoreDto>();
				structure.setMessage("Medical store added successfully !");
				structure.setStatus(HttpStatus.CREATED.value());
				structure.setData(dbStoreDto);
				return new ResponseEntity<ResponseStructure<MedicalStoreDto>>(structure,HttpStatus.CREATED);
				
				
			}else {
				throw new AddressIdNotFoundException("Sorry ! failed to add medical store");
			}
			
		}else {
			throw new AdminIdNotFoundException("Sorry ! failed to add medical store");
		}
	}

	public ResponseEntity<ResponseStructure<MedicalStoreDto>> findMedicalStore(int storeId) {
		MedicalStore store=storeDao.findMedicalStore(storeId);
		if(store!=null) {
			MedicalStoreDto storeDto=this.modelMapper.map(store, MedicalStoreDto.class);
			storeDto.setAddressDto(this.modelMapper.map(store.getAddress(), AddressDto.class));
			storeDto.setAdminDto(this.modelMapper.map(store.getAdmin(), AdminDto.class));
			
			ResponseStructure<MedicalStoreDto>structure=new ResponseStructure<MedicalStoreDto>();
			structure.setMessage("Medical store fetched successfully !");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(storeDto);
			
			return new ResponseEntity<ResponseStructure<MedicalStoreDto>>(structure,HttpStatus.FOUND);
		}else {
			throw new MedicalStoreIdNotFoundException("Sorry ! failed to fetch the Medical Store");
		}
	}
	
	
	
	
	public ResponseEntity<ResponseStructure<MedicalStoreDto>> updateMedicalStore(int storeId,MedicalStore store) {
		MedicalStore dbStore=storeDao.updateMedicalStore(storeId,store);
		if(dbStore!=null) {
			MedicalStoreDto storeDto=this.modelMapper.map(dbStore, MedicalStoreDto.class);
			storeDto.setAddressDto(this.modelMapper.map(dbStore.getAddress(), AddressDto.class));
			storeDto.setAdminDto(this.modelMapper.map(dbStore.getAdmin(),AdminDto.class));
			ResponseStructure<MedicalStoreDto>structure=new ResponseStructure<MedicalStoreDto>();
			structure.setMessage("Medical store updated successfully !");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(storeDto);
			return new ResponseEntity<ResponseStructure<MedicalStoreDto>>(structure,HttpStatus.OK);
		}else {
			throw new MedicalStoreIdNotFoundException("Sorry ! failed to update the Medical Store");
		}
	}

	public ResponseEntity<ResponseStructure<MedicalStore>> deleteMedicalStore(int storeId) {
		MedicalStore dbStore=storeDao.findMedicalStore(storeId);
		dbStore.getAddress().setMedicalStore(null);
		if(dbStore!=null) {
			MedicalStore store=storeDao.deleteMedicalStore(storeId);

			ResponseStructure<MedicalStore>structure=new ResponseStructure<MedicalStore>();
			structure.setMessage("Deleted successfully");
			structure.setData(dbStore);
			structure.setStatus(HttpStatus.GONE.value());
			
			return new ResponseEntity<ResponseStructure<MedicalStore>>(structure,HttpStatus.GONE);
		}else {
			throw new MedicalStoreIdNotFoundException("Sorry ! failed to delete the medical store");
		}

	}
	
}
