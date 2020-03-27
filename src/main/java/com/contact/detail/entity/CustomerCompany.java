package com.contact.detail.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("C")
//@Table(name = "customercompany")
public class CustomerCompany extends Customer {
	@OneToOne(mappedBy = "customer")
	Company company;

}
