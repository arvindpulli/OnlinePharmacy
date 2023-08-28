package com.js.OnlinePharmacy.entity;

import java.util.List;

import org.hibernate.validator.constraints.UniqueElements;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Admin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int adminId;
	@NotBlank(message="adminName can't be blank")
	@NotNull(message="adminName can't be null")
	private String adminName;
	
	@NotBlank(message="adminEmail can't be blank")
	@NotNull(message="adminEmail can't be null")
	@Column(unique=true)
	@Email
	@Pattern(regexp=".+@.+\\.[a-z]+", message="Invalid email address!")
	private String adminEmail;
	
	@NotBlank(message="password can't be blank")
	@NotNull(message="password can't be null")
	@Size(min=5, max=10, message="password should be in 5-10 character long")
	private String password;
	
	@Positive(message="phone number can't be zero")
	@Column(unique = true )
	@Max(9999999999L)@Min(6666666666L)
	private long adminPhone;
	
	@OneToMany(mappedBy = "admin",fetch = FetchType.EAGER)
	
	private List<Staff> staffs;
	
	@OneToMany(mappedBy = "admin",fetch = FetchType.EAGER)
	private List<MedicalStore> medicalStores;
	
	
}
