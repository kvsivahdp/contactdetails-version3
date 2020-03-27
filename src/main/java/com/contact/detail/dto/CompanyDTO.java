package com.contact.detail.dto;

public class CompanyDTO {
	private long id;
	private String companyName;
	private String regNum;
	/**
	 * 
	 */
	public CompanyDTO() {
	}
	/**
	 * @param id
	 * @param companyName
	 * @param regNum
	 */
	public CompanyDTO(long id, String companyName, String regNum) {
		this.id = id;
		this.companyName = companyName;
		this.regNum = regNum;
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((companyName == null) ? 0 : companyName.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((regNum == null) ? 0 : regNum.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof CompanyDTO))
			return false;
		CompanyDTO other = (CompanyDTO) obj;
		if (companyName == null) {
			if (other.companyName != null)
				return false;
		} else if (!companyName.equals(other.companyName))
			return false;
		if (id != other.id)
			return false;
		if (regNum == null) {
			if (other.regNum != null)
				return false;
		} else if (!regNum.equals(other.regNum))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "CompanyDTO [id=" + id + ", companyName=" + companyName + ", regNum=" + regNum + "]";
	}

}
