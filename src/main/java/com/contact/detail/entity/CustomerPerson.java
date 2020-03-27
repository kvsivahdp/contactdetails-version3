package com.contact.detail.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("P")
//@Table(name = "customercompany")
public class CustomerPerson extends Customer {
	@OneToOne(mappedBy = "customer")
	Person person;

}
