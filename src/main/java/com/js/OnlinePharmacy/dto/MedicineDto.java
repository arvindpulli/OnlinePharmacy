package com.js.OnlinePharmacy.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class MedicineDto {
	private int medicineId;
	private String medicineName;
	private double medicinePrice;
	private LocalDate expiryDate;
	private int stockQuantity;
	private String manufracturer;
	private String description;
}
