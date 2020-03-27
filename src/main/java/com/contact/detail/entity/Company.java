package com.contact.detail.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "company")

public class Company {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonIgnore
	private long id;

	@ApiModelProperty(required = true, notes = "Company Name")
	@NotNull
	@Column(nullable = false)
	private String companyName;

	@ApiModelProperty(required = true, notes = "Registration Number")
	@NotNull
	@Column(nullable = false)
	private String regNum;

	@OneToOne(cascade = CascadeType.ALL)
	private CustomerCompany customer;

	@OneToOne(cascade = CascadeType.ALL)
	private SupplierCompany supplier;

	/*@Embedded
	private PhoneNumber phoneNumber;*/

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * @param companyName the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * @return the regNum
	 */
	public String getRegNum() {
		return regNum;
	}

	/**
	 * @param regNum the regNum to set
	 */
	public void setRegNum(String regNum) {
		this.regNum = regNum;
	}

	/**
	 * @return the customer
	 */
	public CustomerCompany getCustomer() {
		return customer;
	}

	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(CustomerCompany customer) {
		this.customer = customer;
	}

	/**
	 * @return the supplier
	 */
	public SupplierCompany getSupplier() {
		return supplier;
	}

	/**
	 * @param supplier the supplier to set
	 */
	public void setSupplier(SupplierCompany supplier) {
		this.supplier = supplier;
	}


	/*
	public PhoneNumber getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(PhoneNumber phoneNumber) {
		this.phoneNumber = phoneNumber;
	}*/

	@Override
	public String toString() {
		return "Company [id=" + id + ", companyName=" + companyName + ", regNum=" + regNum + "]";
	}
}
