package com.contact.detail.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.contact.detail.entity.Company;

public interface CompanyRepository extends JpaRepository<Company,Long>{
	
	public Company findByCustomerId(Long customerId);
	
	public Company findBySupplierId(Long supplierId);

}
