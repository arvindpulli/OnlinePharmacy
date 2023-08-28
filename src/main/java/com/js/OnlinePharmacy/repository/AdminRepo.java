package com.js.OnlinePharmacy.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.js.OnlinePharmacy.entity.Admin;

public interface AdminRepo extends JpaRepository<Admin, Integer> {

	@Query("select a from Admin a where a.adminEmail=?1")
	public Optional<Admin> findByEmail(String email);

}
