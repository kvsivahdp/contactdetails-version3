package com.contact.detail.dto;

import java.sql.Date;

import com.contact.detail.entity.PhoneNumber;

public class CustomerDTO {
	private long id;
	private String custNum;
	private Date lastOrderDate;
	private PersonDTO person;
	private CompanyDTO company;
	private PhoneNumber phoneNumber;
	/**
	 * 
	 */
	public CustomerDTO() {
	}
	/**
	 * @param id
	 * @param custNum
	 * @param lastOrderDate
	 * @param person
	 * @param company
	 * @param phoneNumber
	 */
	public CustomerDTO(long id, String custNum, Date lastOrderDate, PersonDTO person, CompanyDTO company,
			PhoneNumber phoneNumber) {
		this.id = id;
		this.custNum = custNum;
		this.lastOrderDate = lastOrderDate;
		this.person = person;
		this.company = company;
		this.phoneNumber = phoneNumber;
	}
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
	 * @return the custNum
	 */
	public String getCustNum() {
		return custNum;
	}
	/**
	 * @param custNum the custNum to set
	 */
	public void setCustNum(String custNum) {
		this.custNum = custNum;
	}
	/**
	 * @return the lastOrderDate
	 */
	public Date getLastOrderDate() {
		return lastOrderDate;
	}
	/**
	 * @param lastOrderDate the lastOrderDate to set
	 */
	public void setLastOrderDate(Date lastOrderDate) {
		this.lastOrderDate = lastOrderDate;
	}
	/**
	 * @return the person
	 */
	public PersonDTO getPerson() {
		return person;
	}
	/**
	 * @param person the person to set
	 */
	public void setPerson(PersonDTO person) {
		this.person = person;
	}
	/**
	 * @return the company
	 */
	public CompanyDTO getCompany() {
		return company;
	}
	/**
	 * @param company the company to set
	 */
	public void setCompany(CompanyDTO company) {
		this.company = company;
	}
	/**
	 * @return the phoneNumber
	 */
	public PhoneNumber getPhoneNumber() {
		return phoneNumber;
	}
	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(PhoneNumber phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	
		
}
