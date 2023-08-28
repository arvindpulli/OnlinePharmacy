package com.js.OnlinePharmacy.entity;

import org.hibernate.validator.constraints.UniqueElements;

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
@Setter
@Getter
public class MedicalStore {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int storeId;
	
	private String storeName;
	
	private String storeManagerName;
	
	@Positive(message="phone number can't be zero")
	@Column(unique = true)
	@Max(9999999999L)@Min(6666666666L)
	private long storePhone;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn
	private Admin admin;
	
	
	@OneToOne(mappedBy = "medicalStore")
	private Address address;
	
	@OneToOne
	private Staff staff;

	  
}
