package com.contact.detail.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.contact.detail.entity.Company;
import com.contact.detail.services.CompanyService;

/**
 * A RESTFul controller for accessing Customer information via
 * {@link CompanyService}.
 *
 */
/*
 * @CrossOrigin(origins = "*", allowedHeaders = "*")
 * 
 * @RestController
 * 
 * @RequestMapping("/api/v1")
 */
public class CompanyController {

	@Autowired
	private CompanyService companyService;

	/**
	 * 
	 * @param newCompany
	 * @return newly saved company
	 */
	@PostMapping("/companies")
	public ResponseEntity<ResponseData<Company>> save(@RequestBody Company newCompany) {
		ResponseData<Company> appResponse = new ResponseData<>();
		try {
			if (newCompany.getCustomer() == null && newCompany.getSupplier() == null) {
				appResponse.setResponseMessage("A company should atleast be a customer or supplier or both");
				return ResponseEntity.ok().body(appResponse);
			}
			appResponse.setPayload(companyService.saveCompany(newCompany));
			return ResponseEntity.ok().body(appResponse);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 
	 * @return list of companies
	 */
	@GetMapping("/companies")
	public ResponseEntity<ResponseData<List<Company>>> companies() {
		ResponseData<List<Company>> appResponse = new ResponseData<>();
		try {
			List<Company> companyList = companyService.fetchAllCompanies();
			appResponse.setPayload(companyList);
			if (companyList != null && !companyList.isEmpty()) {
				appResponse.setPayload(companyList);
			} else {
				appResponse.setResponseMessage("No company(s) found");
				appResponse.setPayload(null);
			}
			return ResponseEntity.ok().body(appResponse);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 
	 * @param company
	 * @param cmpid
	 * @return updated company
	 */
	@PatchMapping("/companies/{cmpid}")
	public ResponseEntity<ResponseData<Company>> companies(@RequestBody Company company, @PathVariable Long cmpid) {
		ResponseData<Company> appResponse = new ResponseData<>();
		try {
			Company cmp = companyService.updateCompany(company, cmpid);
			if (cmp == null) {
				appResponse.setResponseMessage("Company not found");
			} else {
				appResponse.setPayload(cmp);
			}
			return ResponseEntity.ok().body(appResponse);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 
	 * @param cmpid
	 */
	@DeleteMapping("/companies/{cmpid}")
	public void delete(@PathVariable Long cmpid) {
		try {
			companyService.deleteCompany(cmpid);
		} catch (Exception e) {
			throw e;
		}
	}
}