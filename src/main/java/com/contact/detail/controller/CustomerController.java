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
import com.contact.detail.entity.Customer;
import com.contact.detail.repository.CustomerRepository;
import com.contact.detail.services.CustomerService;

/**
 * A RESTFul controller for accessing Customer information via
 * {@link CustomerService}.
 *
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1")
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/customers")
	public ResponseEntity<ResponseData<CustomerDTO>> save(@RequestBody CustomerDTO customerDTO) throws Exception {
		ResponseData<CustomerDTO> appResponse = new ResponseData<>();
		try {
			if(customerDTO.getCompany()!=null && customerDTO.getPerson()!=null) {
				appResponse.setResponseMessage("A customer can be a person or a company, but not both.");
				return ResponseEntity.ok().body(appResponse);
			}
			appResponse.setPayload( customerService.saveCustomer(customerDTO));
			return ResponseEntity.ok().body(appResponse);
		} catch (Exception e) {
			throw e;
		}
	}
	
	@PatchMapping("/customers/{customerId}")
	public ResponseEntity<ResponseData<CustomerDTO>> update(@RequestBody CustomerDTO customerDTO, @PathVariable Long customerId) throws Exception {
		ResponseData<CustomerDTO> appResponse = new ResponseData<>();
		try {
			if(customerDTO.getCompany()!=null && customerDTO.getPerson()!=null) {
				appResponse.setResponseMessage("A customer can be a person or a company, but not both.");
				return ResponseEntity.ok().body(appResponse);
			}
			appResponse.setPayload( customerService.updateCustomer(customerDTO, customerId));
			return ResponseEntity.ok().body(appResponse);
		} catch (Exception e) {
			throw e;
		}
	}

	@GetMapping("/customers/")
	public ResponseEntity<ResponseData<List<CustomerDTO>>> customers() throws Exception {
		ResponseData<List<CustomerDTO>> appResponse = new ResponseData<>();
		try {
			appResponse.setPayload(customerService.findAllCustomers());
			return ResponseEntity.ok().body(appResponse);
		} catch (Exception e) {
			throw e;
		}
	}
	
}
