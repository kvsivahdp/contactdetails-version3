package com.contact.detail.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
//@Table(name="supplierperson")
@DiscriminatorValue("P")
public class SupplierPerson extends Supplier{
	@OneToOne(mappedBy = "supplier" )
	Person person;

}
