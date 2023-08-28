package com.js.OnlinePharmacy.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Medicine {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int medicineId;
	@NotBlank(message="medicineName can't be blank")
	@NotNull(message="medicineName can't be null")
	private String medicineName;
	@Positive(message="price can't be zero")
	private double medicinePrice;
	
	private LocalDate expiryDate;
	
	private int stockQuantity;
	@NotBlank(message="manufracturer can't be blank")
	@NotNull(message="manufracturer can't be null")
	private String manufracturer;
	@NotBlank(message="description can't be blank")
	@NotNull(message="description can't be null")
	private String description;
	
	@ManyToOne
	@JsonIgnore
	private MedicalStore medicalStore;
	@ManyToOne
	@JoinColumn
	private Booking booking;
	
}
