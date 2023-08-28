package com.js.OnlinePharmacy.entity;

import org.hibernate.validator.constraints.UniqueElements;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Staff {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int staffId;
	@NotBlank(message="staffName can't be blank")
	@NotNull(message="staffName can't be null")
	private String staffName;
	
	@NotBlank(message="staffEmail can't be blank")
	@NotNull(message="staffEmail can't be null")	
	@Column(unique = true )
	@Email
	@Pattern(regexp=".+@.+\\.[a-z]+", message="Invalid email address!")
	private String staffEmail;
	
	@Positive(message="phone number can't be zero")
	@Column(unique = true )
	@Max(9999999999L)@Min(6666666666L)
	private long staffPhone;
	
	
	@NotBlank(message="staffPassword can't be blank")
	@NotNull(message="staffPassword can't be null")
	private String staffPassword;
	
	@ManyToOne
	@JoinColumn
	private Admin admin;
	@OneToOne
	private MedicalStore medicalStore;
}
