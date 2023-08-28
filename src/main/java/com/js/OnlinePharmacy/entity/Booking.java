package com.js.OnlinePharmacy.entity;

import java.time.LocalDate;
import java.util.List;

import com.js.OnlinePharmacy.Enum.BookingStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bookingId;
	private LocalDate orderDate;
	@Min(1)
	
	private int quantity;
	@NotBlank@NotNull
	private String paymentMethod;
	private LocalDate expectedDate;
	private BookingStatus bookingStatus;
	@OneToMany(mappedBy = "booking",fetch = FetchType.EAGER)
	private List<Medicine> medicines;
	@ManyToOne
	@JoinColumn
	private Customer customer;
	
}
