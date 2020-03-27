package com.contact.detail.dto;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.contact.detail.entity.PhoneNumber;

public class CustomerDTOList {
	private long id;
	private String custNum;
	private Date lastOrderDate;
	
	private PhoneNumber phoneNumber;
	private List<CompanyDTO> companyList = new ArrayList<>();
	private List<PersonDTO> personList = new ArrayList<>();
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCustNum() {
		return custNum;
	}
	public void setCustNum(String custNum) {
		this.custNum = custNum;
	}
	public Date getLastOrderDate() {
		return lastOrderDate;
	}
	public void setLastOrderDate(Date lastOrderDate) {
		this.lastOrderDate = lastOrderDate;
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
