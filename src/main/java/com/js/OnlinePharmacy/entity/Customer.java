package com.js.OnlinePharmacy.entity;

import java.util.List;

import org.hibernate.validator.constraints.UniqueElements;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Getter
@Setter
public class Customer {
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int customerId;
	@NotBlank(message="customerName can't be blank")
	@NotNull(message="customerName can't be null")
	private String customerName;
	
	@NotBlank(message="customerEmail can't be blank")
	@NotNull(message="customerEmail can't be null")
	@Column(unique = true )
	@Email
	@Pattern(regexp=".+@.+\\.[a-z]+", message="Invalid email address!")
	private String customerEmail;
	
	
	@Positive(message="phone number can't be zero")
	@Column(unique = true)
	@Max(9999999999L)@Min(6666666666L)
	private long customerPhone;
	
	
	@NotBlank(message="password can't be blank")
	@NotNull(message="password can't be null")
	@Size(min=5, max=10, message="password should be in 5-10 character long")
	private String password;

	@OneToMany(mappedBy = "customer",cascade = CascadeType.REMOVE)
	private List<Address> addresses;
	
	@OneToMany(mappedBy="customer")
	@JsonIgnore
	private List<Booking> bookings;
	
}
