package com.contact.detail.dto;

import java.util.ArrayList;
import java.util.List;

import com.contact.detail.entity.PhoneNumber;

public class SupplierDTOList {
	private long id;
	private String taxNum;
	private Integer orderLeadTime;;
	private PhoneNumber phoneNumber;
	private List<CompanyDTO> companyList = new ArrayList<>();
	private List<PersonDTO> personList = new ArrayList<>();
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTaxNum() {
		return taxNum;
	}
	public void setTaxNum(String taxNum) {
		this.taxNum = taxNum;
	}
	public Integer getOrderLeadTime() {
		return orderLeadTime;
	}
	public void setOrderLeadTime(Integer orderLeadTime) {
		this.orderLeadTime = orderLeadTime;
	}
	public PhoneNumber getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(PhoneNumber phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public List<CompanyDTO> getCompanyList() {
		return companyList;
	}
	public void setCompanyList(List<CompanyDTO> companyList) {
		this.companyList = companyList;
	}
	public List<PersonDTO> getPersonList() {
		return personList;
	}
	public void setPersonList(List<PersonDTO> personList) {
		this.personList = personList;
	}

}
