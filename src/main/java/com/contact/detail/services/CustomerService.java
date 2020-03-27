package com.contact.detail.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.contact.detail.dto.CompanyDTO;
import com.contact.detail.dto.CustomerDTO;
import com.contact.detail.dto.CustomerDTOList;
import com.contact.detail.dto.PersonDTO;
import com.contact.detail.dto.SupplierDTO;
import com.contact.detail.dto.SupplierDTOList;
import com.contact.detail.entity.Company;
import com.contact.detail.entity.Customer;
import com.contact.detail.entity.CustomerCompany;
import com.contact.detail.entity.CustomerPerson;
import com.contact.detail.entity.Person;
import com.contact.detail.entity.Supplier;
import com.contact.detail.repository.CustomerRepository;

@Service
public class CustomerService {
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private PersonService personService;
	
	public CustomerDTO saveCustomer(CustomerDTO newCustomer) throws Exception {
		CustomerDTO customerDTO =null;
		try {
			if(newCustomer.getCompany()!=null) {
				customerDTO = saveCustomerCompany(newCustomer);
			} else if(newCustomer.getPerson()!=null) {
				customerDTO = savePersonCustomer(newCustomer);
			}
			return customerDTO;
		} catch (Exception e) {
			throw e;
		}
	}
	
	private CustomerDTO saveCustomerCompany(CustomerDTO newCustomer) {
		CustomerDTO customerDTO= new CustomerDTO();
		try {
			//setup data to save
			CustomerCompany customer = new CustomerCompany();
			customer.setCustNum(newCustomer.getCustNum());
			customer.setLastOrderDate(newCustomer.getLastOrderDate());
			customer.setPhoneNumber(newCustomer.getPhoneNumber());
			Company company = new Company();
			company.setCompanyName(newCustomer.getCompany().getCompanyName());
			company.setRegNum(newCustomer.getCompany().getRegNum());
			company.setCustomer(customer);
			
			//save company along with customer
			Company savedCompany = companyService.saveCompany(company);
			
			// create value object to return
			CompanyDTO companyDTO = new CompanyDTO();
			companyDTO.setCompanyName(savedCompany.getCompanyName());
			companyDTO.setRegNum(savedCompany.getRegNum());
			customerDTO.setCompany(companyDTO);
			customerDTO.setCustNum(savedCompany.getCustomer().getCustNum());
			customerDTO.setLastOrderDate(savedCompany.getCustomer().getLastOrderDate());
			customerDTO.setPhoneNumber(savedCompany.getCustomer().getPhoneNumber());
			return customerDTO;  
		}catch(Exception e) {
			throw e;
		}
	}
	private CustomerDTO savePersonCustomer(CustomerDTO newCustomer) {
		CustomerDTO customerDTO= new CustomerDTO();
		try {
			
			//prepare data to save
			CustomerPerson customerPerson = new CustomerPerson();
			customerPerson.setCustNum(newCustomer.getCustNum());
			customerPerson.setLastOrderDate(newCustomer.getLastOrderDate());
			customerPerson.setPhoneNumber(newCustomer.getPhoneNumber());
			Person newPerson = new Person();
			newPerson.setFirstName(newCustomer.getPerson().getFirstName());
			newPerson.setLastName(newCustomer.getPerson().getLastName());
			newPerson.setCustomer(customerPerson);
			
			//save person along with customer
			Person savedPerson = personService.savePerson(newPerson);
			
			// create value object to return
			PersonDTO personDTO = new PersonDTO();
			personDTO.setFirstName(savedPerson.getFirstName());
			personDTO.setLastName(savedPerson.getLastName()); 
			customerDTO.setPerson(personDTO);
			customerDTO.setCustNum(savedPerson.getCustomer().getCustNum());
			customerDTO.setLastOrderDate(savedPerson.getCustomer().getLastOrderDate());
			customerDTO.setPhoneNumber(savedPerson.getCustomer().getPhoneNumber());
			return customerDTO;  
		}catch(Exception e) {
			throw e;
		}
	}
	
	public CustomerDTO updateCustomer(CustomerDTO updateCustomer, Long customerId) throws Exception {
		try {
			if(updateCustomer.getCompany()!=null) {
				return updateCustomerCompany(updateCustomer, customerId);
			}else if(updateCustomer.getPerson()!=null) {
				return updateCustomerPerson(updateCustomer, customerId);
			}
			return null;
		} catch (Exception e) {
			throw e;
		}
	}
	
	private CustomerDTO updateCustomerCompany(CustomerDTO updateCustomer, Long customerId) throws Exception{
		try {
			CustomerDTO customerDTO= new CustomerDTO();
			Company company = this.companyService.findByCustomerId(customerId);
			if(company==null) {
				return saveCustomer(updateCustomer);
			}
			
			//prepare data to update
			Customer customer = company.getCustomer();
			customer.setCustNum(updateCustomer.getCustNum());
			customer.setLastOrderDate(updateCustomer.getLastOrderDate());
			customer.setPhoneNumber(updateCustomer.getPhoneNumber());
			company.setCompanyName(updateCustomer.getCompany().getCompanyName());
			company.setRegNum(updateCustomer.getCompany().getRegNum());
			
			//update company along with customer
			Company updatedCustomerCompany = companyService.updateCompany(company, company.getId());
			
			// create value object to return
			CompanyDTO companyDTO = new CompanyDTO();
			companyDTO.setCompanyName(updatedCustomerCompany.getCompanyName());
			companyDTO.setRegNum(updatedCustomerCompany.getRegNum());
			customerDTO.setCompany(companyDTO);
			customerDTO.setCustNum(updatedCustomerCompany.getCustomer().getCustNum());
			customerDTO.setLastOrderDate(updatedCustomerCompany.getCustomer().getLastOrderDate());
			customerDTO.setPhoneNumber(updatedCustomerCompany.getCustomer().getPhoneNumber());
			return customerDTO;
		}catch(Exception e) {
			throw e;
		}
	}
	
	private CustomerDTO updateCustomerPerson(CustomerDTO updateCustomer, Long customerId) throws Exception{
		try {
			CustomerDTO customerDTO= new CustomerDTO();
			Person person = this.personService.findByCustomerId(customerId);
			if(person==null) {
				saveCustomer(updateCustomer);
			}
			
			//prepare data to update
			Customer customer = person.getCustomer();
			customer.setCustNum(updateCustomer.getCustNum());
			customer.setLastOrderDate(updateCustomer.getLastOrderDate());
			customer.setPhoneNumber(updateCustomer.getPhoneNumber());
			
			person.setFirstName(updateCustomer.getPerson().getFirstName());
			person.setLastName(updateCustomer.getPerson().getLastName());
			
			//update person along with customer
			Person updatedCustomerPerson = personService.updatePerson(person, person.getId());
			
			// create value object to return
			PersonDTO personDTO = new PersonDTO();
			personDTO.setFirstName(updatedCustomerPerson.getFirstName());
			personDTO.setLastName(updatedCustomerPerson.getLastName());
			customerDTO.setPerson(personDTO);
			customerDTO.setCustNum(updatedCustomerPerson.getCustomer().getCustNum());
			customerDTO.setLastOrderDate(updatedCustomerPerson.getCustomer().getLastOrderDate());
			customerDTO.setPhoneNumber(updatedCustomerPerson.getCustomer().getPhoneNumber());
			return customerDTO;
		}catch(Exception e) {
			throw e;
		}
	}
	
	
	public List<CustomerDTOList> findAllCustomers(){
		List<CustomerDTOList> customers = new ArrayList<>();
		try {
			List<Customer> customerList = customerRepository.findAll();
			customerList.stream().forEach(x->{
				CustomerDTOList customerDTO = new CustomerDTOList();
				PersonDTO personDTO = new PersonDTO();
				CompanyDTO companyDTO = new CompanyDTO();
				customerDTO.setCustNum(x.getCustNum());
				customerDTO.setLastOrderDate(x.getLastOrderDate());
				customerDTO.setPhoneNumber(x.getPhoneNumber());
				Company company = this.companyService.findByCustomerId(x.getId());
				Person person = this.personService.findByCustomerId(x.getId());
				if(company!=null) {
					companyDTO.setCompanyName(company.getCompanyName());
					companyDTO.setRegNum(company.getRegNum());
					companyDTO.setId(company.getId());
					//customerDTO.setCompany(companyDTO);
					customerDTO.getCompanyList().add(companyDTO);
				}
				if(person!=null) {
					personDTO.setFirstName(person.getFirstName());
					personDTO.setLastName(person.getLastName());
					personDTO.setId(person.getId());
					customerDTO.getPersonList().add(personDTO);
					
				}
				customers.add(customerDTO);
			});
			return customers;
		}catch(Exception e) {
			throw e;
		}
	}
	
	public CustomerDTO findCustomerByCustomerId(Long customerId){
		CustomerDTO customerDTO = new CustomerDTO();
		try {
			Optional<Customer> isCustomerFound = customerRepository.findById(customerId);
			if(isCustomerFound.isPresent()) {
				Customer customer = isCustomerFound.get();
				
				PersonDTO personDTO = new PersonDTO();
				CompanyDTO companyDTO = new CompanyDTO();
				customerDTO.setCustNum(customer.getCustNum());
				customerDTO.setLastOrderDate(customer.getLastOrderDate());
				customerDTO.setPhoneNumber(customer.getPhoneNumber());
				Company company = this.companyService.findByCustomerId(customer.getId());
				Person person = this.personService.findByCustomerId(customer.getId());
				if(company!=null) {
					companyDTO.setCompanyName(company.getCompanyName());
					companyDTO.setRegNum(company.getRegNum());
					companyDTO.setId(company.getId());
				}
				if(person!=null) {
					personDTO.setFirstName(person.getFirstName());
					personDTO.setLastName(person.getLastName());
					personDTO.setId(person.getId());
				}
			
			}
			return customerDTO;
		}catch(Exception e) {
			throw e;
		}
	}
}
