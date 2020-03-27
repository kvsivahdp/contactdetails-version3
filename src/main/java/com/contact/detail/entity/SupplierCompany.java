package com.contact.detail.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
//@Table(name="suppliercompany")
@DiscriminatorValue("C")
public class SupplierCompany extends Supplier{
	@OneToOne(mappedBy = "supplier" )
	Company company;
	
}
