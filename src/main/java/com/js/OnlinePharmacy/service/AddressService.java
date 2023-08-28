package com.js.OnlinePharmacy.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.js.OnlinePharmacy.dao.AddressDao;
import com.js.OnlinePharmacy.dto.AddressDto;
import com.js.OnlinePharmacy.entity.Address;
import com.js.OnlinePharmacy.exception.AddressIdNotFoundException;
import com.js.OnlinePharmacy.util.ResponseStructure;

@Service
public class AddressService {

	@Autowired
	private AddressDao addressDao;
	
	
	@Autowired
	private ModelMapper modelmapper;
	
	public ResponseEntity<ResponseStructure<AddressDto>>saveAddress(Address address){
		Address dbAddress=addressDao.saveAddress(address);
		AddressDto addDto=new AddressDto();
		addDto.setAddressId(dbAddress.getAddressId());
		addDto.setCity(dbAddress.getCity());
		addDto.setPincode(dbAddress.getPincode());
		addDto.setState(dbAddress.getState());
		addDto.setPlotNumber(dbAddress.getPlotNumber());
		addDto.setStreetName(dbAddress.getStreetName());
		
		ResponseStructure<AddressDto> structure=new ResponseStructure<AddressDto>();
		structure.setMessage("Address Saved Successfully !");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(addDto);
		
		return new ResponseEntity<ResponseStructure<AddressDto>>(structure,HttpStatus.CREATED);
	}


	public ResponseEntity<ResponseStructure<AddressDto>> findAddress(int addressId) {
		Address address=addressDao.findAddress(addressId);
		if(address!=null) {
			ResponseStructure<AddressDto>structure=new ResponseStructure<AddressDto>();
			structure.setMessage("Address fetched successfully !");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(this.modelmapper.map(address, AddressDto.class));
			
			return new ResponseEntity<ResponseStructure<AddressDto>>(structure,HttpStatus.FOUND);
		}else {
			throw new AddressIdNotFoundException("Sorry ! failed to add address");
		}
	}


	public ResponseEntity<ResponseStructure<AddressDto>> deleteAddress(int addressId) {
		Address address=addressDao.deleteAddress(addressId);
		if(address!=null) {
			ResponseStructure<AddressDto>structure=new ResponseStructure<AddressDto>();
			structure.setMessage("Address deleted successfully !");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(this.modelmapper.map(address, AddressDto.class));
			return new ResponseEntity<ResponseStructure<AddressDto>>(structure,HttpStatus.OK);

		}else {
			throw new AddressIdNotFoundException("Sorry ! failed to add address");
		}
	}
	
	public ResponseEntity<ResponseStructure<AddressDto>> updateAddress(int addressId,Address address) {
		Address dbAddress=addressDao.updateAddress(addressId,address);
		if(address!=null) {
			ResponseStructure<AddressDto>structure=new ResponseStructure<AddressDto>();
			structure.setMessage("Address updated successfully !");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(this.modelmapper.map(address, AddressDto.class));
			return new ResponseEntity<ResponseStructure<AddressDto>>(structure,HttpStatus.OK);

		}else {
			throw new AddressIdNotFoundException("Sorry ! failed to add address");
		}
	}
}
