package com.contact.detail.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.contact.detail.entity.Company;
import com.contact.detail.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{
	
	public Person findByCustomerId(Long customerId);
	
	public Person findBySupplierId(Long supplierId);

}
