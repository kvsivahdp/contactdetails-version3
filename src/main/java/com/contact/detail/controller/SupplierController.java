package com.contact.detail.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.contact.detail.dto.CustomerDTO;
import com.contact.detail.dto.SupplierDTO;
import com.contact.detail.services.SupplierService;

/**
 * A RESTFul controller for accessing Supplier information via
 * {@link SuppierService}.
 *
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1")
public class SupplierController {
	@Autowired
	private SupplierService supplierService;
	
	@PostMapping("/suppliers")
	public ResponseEntity<ResponseData<SupplierDTO>> save(@RequestBody SupplierDTO supplierDTO) throws Exception{
		ResponseData<SupplierDTO> appResponse = new ResponseData<>();
		try {
			if(supplierDTO.getCompany()!=null && supplierDTO.getPerson()!=null) {
				appResponse.setResponseMessage("A supplier can be a person or a company, but not both.");
				return ResponseEntity.ok().body(appResponse);
			}
			appResponse.setPayload( supplierService.saveSupplier(supplierDTO));
			return ResponseEntity.ok().body(appResponse);
		} catch (Exception e) {
			throw e;
		}
	}
	
	@PatchMapping("/suppliers/{supplierId}")
	public ResponseEntity<ResponseData<SupplierDTO>> update(@RequestBody SupplierDTO supplierDTO, @PathVariable Long supplierId) throws Exception {
		ResponseData<SupplierDTO> appResponse = new ResponseData<>();
		try {
			if(supplierDTO.getCompany()!=null && supplierDTO.getPerson()!=null) {
				appResponse.setResponseMessage("A supplier can be a person or a company, but not both.");
				return ResponseEntity.ok().body(appResponse);
			}
			appResponse.setPayload( supplierService.updateSupplier(supplierDTO, supplierId));
			return ResponseEntity.ok().body(appResponse);
		} catch (Exception e) {
			throw e;
		}
	}

	@GetMapping("/suppliers/")
	public ResponseEntity<ResponseData<List<SupplierDTO>>> suppliers() throws Exception {
		ResponseData<List<SupplierDTO>> appResponse = new ResponseData<>();
		try {
			appResponse.setPayload(supplierService.findAllSuppliers());
			return ResponseEntity.ok().body(appResponse);
		} catch (Exception e) {
			throw e;
		}
	}
	
}
