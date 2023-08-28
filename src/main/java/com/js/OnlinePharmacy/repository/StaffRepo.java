package com.js.OnlinePharmacy.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.js.OnlinePharmacy.entity.Staff;

public interface StaffRepo extends JpaRepository<Staff, Integer> {

	@Query("select s from Staff s where s.staffEmail=?1")
	Optional<Staff> findByEmail(String email);

}
