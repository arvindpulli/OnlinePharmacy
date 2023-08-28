package com.js.OnlinePharmacy.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int addressId;
	@Positive(message="plot number can't be zero")
	@Column(unique=true)
	private int plotNumber;
	
	
	@NotBlank(message="streetName can't be blank")
	@NotNull(message="streetName can't be null")
	private String streetName;
	@NotBlank(message="city can't be blank")
	@NotNull(message="city can't be null")
	private String city;
	@NotBlank(message="state can't be blank")
	@NotNull(message="state can't be null")
	private String state;
	@Positive(message="pincode can't be zero")
	@Min(100001)@Max(999999)
	private long pincode;
	
	@ManyToOne
	@JoinColumn
	@JsonIgnore
	private Customer customer;
	
	@OneToOne
	@JoinColumn
	private MedicalStore medicalStore;
		
}
