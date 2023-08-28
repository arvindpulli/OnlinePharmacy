package com.js.OnlinePharmacy.dto;

import com.js.OnlinePharmacy.entity.Address;
import com.js.OnlinePharmacy.entity.Admin;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MedicalStoreDto {
	private int storeId;
	private String storeName;
	private String storeManagerName;
	private long storePhone;
	
	@ManyToOne
	private AdminDto adminDto;
	
	@OneToOne
	private AddressDto addressDto;
	
	@OneToOne
	private StaffDto staffDto;
}
