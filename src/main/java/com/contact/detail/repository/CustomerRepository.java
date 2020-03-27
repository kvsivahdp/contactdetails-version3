package com.contact.detail.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.contact.detail.dto.CustomerDTO;
import com.contact.detail.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	public Customer save(CustomerDTO newCustomer);

}
