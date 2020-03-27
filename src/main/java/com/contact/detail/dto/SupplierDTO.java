package com.contact.detail.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.contact.detail.entity.PhoneNumber;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;

public class SupplierDTO {
	
	private long id;
	private String taxNum;
	private Integer orderLeadTime;
	private PersonDTO person;
	private CompanyDTO company;
	private PhoneNumber phoneNumber;
	/**
	 * 
	 */
	public SupplierDTO() {
	}
	/**
	 * @param id
	 * @param taxNum
	 * @param orderLeadTime
	 * @param person
	 * @param company
	 * @param phoneNumber
	 */
	public SupplierDTO(long id, String taxNum, Integer orderLeadTime, PersonDTO person, CompanyDTO company,
			PhoneNumber phoneNumber) {
		this.id = id;
		this.taxNum = taxNum;
		this.orderLeadTime = orderLeadTime;
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
	 * @return the taxNum
	 */
	public String getTaxNum() {
		return taxNum;
	}
	/**
	 * @param taxNum the taxNum to set
	 */
	public void setTaxNum(String taxNum) {
		this.taxNum = taxNum;
	}
	/**
	 * @return the orderLeadTime
	 */
	public Integer getOrderLeadTime() {
		return orderLeadTime;
	}
	/**
	 * @param orderLeadTime the orderLeadTime to set
	 */
	public void setOrderLeadTime(Integer orderLeadTime) {
		this.orderLeadTime = orderLeadTime;
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((orderLeadTime == null) ? 0 : orderLeadTime.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof SupplierDTO))
			return false;
		SupplierDTO other = (SupplierDTO) obj;
		if (id != other.id)
			return false;
		if (orderLeadTime == null) {
			if (other.orderLeadTime != null)
				return false;
		} else if (!orderLeadTime.equals(other.orderLeadTime))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "SupplierDTO [id=" + id + ", taxNum=" + taxNum + ", orderLeadTime=" + orderLeadTime + "]";
	}
}
