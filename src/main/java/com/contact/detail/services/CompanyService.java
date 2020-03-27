package com.contact.detail.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.contact.detail.entity.Company;
import com.contact.detail.repository.CompanyRepository;

@Service
public class CompanyService {

	@Autowired
	private CompanyRepository companyRepository;

	public List<Company> fetchAllCompanies() {
		try {
			return companyRepository.findAll();
		} catch (Exception e) {
			throw e;
		}

	}

	public Company saveCompany(Company newCompany) {
		try {
			return companyRepository.save(newCompany);
		} catch (Exception e) {
			throw e;
		}
	}

	public Company updateCompany(Company company, Long cmpid) {
		try {
			Optional<Company> companyFound = companyRepository.findById(cmpid);
			if (companyFound.isPresent()) {
				Company cmp = companyFound.get();
				cmp.setCompanyName(company.getCompanyName());
				cmp.setRegNum(company.getRegNum());
				//cmp.setPhoneNumber(company.getPhoneNumber());
				if (company.getCustomer() != null) {
					cmp.getCustomer().setCustNum(company.getCustomer().getCustNum());
					cmp.getCustomer().setLastOrderDate(company.getCustomer().getLastOrderDate());
				}
				if (company.getSupplier() != null) {
					cmp.getSupplier().setTaxNum(company.getSupplier().getTaxNum());
					cmp.getSupplier().setOrderLeadTime(company.getSupplier().getOrderLeadTime());
				}
				return companyRepository.save(cmp);
			}
			return null;
		} catch (Exception e) {
			throw e;
		}
	}
	
	public Company findByCustomerId(Long customerId) {
		try {
			return this.companyRepository.findByCustomerId(customerId);
		}catch(Exception e) {
			throw e;
		}
	}

	public Company findBySupplierId(Long supplierId) {
		try {
			return this.companyRepository.findBySupplierId(supplierId);
		}catch(Exception e) {
			throw e;
		}
	}
	
	public void deleteCompany(Long cmpid) {
		try {
			Optional<Company> company = companyRepository.findById(cmpid);
			if (company.isPresent()) {
				companyRepository.deleteById(cmpid);
			}
		} catch (Exception e) {
			throw e;
		}
	}
}
