package com.js.OnlinePharmacy.dto;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StaffDto {
	private int staffId;
	private String staffName;
	private String staffEmail;
	private long staffPhone;
	
	@ManyToOne
	private AdminDto adminDto;
	@OneToOne
	private MedicalStoreDto medicalStoreDto;
}
