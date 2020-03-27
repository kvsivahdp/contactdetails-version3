package com.contact.detail.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.contact.detail.dto.CompanyDTO;
import com.contact.detail.dto.PersonDTO;
import com.contact.detail.dto.SupplierDTO;
import com.contact.detail.entity.Company;
import com.contact.detail.entity.Person;
import com.contact.detail.entity.Supplier;
import com.contact.detail.entity.SupplierCompany;
import com.contact.detail.entity.SupplierPerson;
import com.contact.detail.repository.SupplierRepository;

@Service
public class SupplierService {
	@Autowired
	private SupplierRepository supplierRepository;
	
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private PersonService personService;
	
	public SupplierDTO saveSupplier(SupplierDTO newSupplier) throws Exception {
		SupplierDTO supplierDTO =null;
		System.out.println("newSupplier.getPerson()"+newSupplier.getPerson());
		try {
			if(newSupplier.getCompany()!=null) {
				supplierDTO = saveSupplierCompany(newSupplier);
			} else if(newSupplier.getPerson()!=null) {
				supplierDTO = savePersonSupplier(newSupplier);
			}
			return supplierDTO;
		} catch (Exception e) {
			throw e;
		}
	}
	
	private SupplierDTO saveSupplierCompany(SupplierDTO newSupplier) {
		SupplierDTO supplierDTO= new SupplierDTO();
		try {
			//setup data to save
			SupplierCompany supplier = new SupplierCompany();
			supplier.setTaxNum(newSupplier.getTaxNum());
			supplier.setOrderLeadTime(newSupplier.getOrderLeadTime());
			supplier.setPhoneNumber(newSupplier.getPhoneNumber());
			Company company = new Company();
			company.setCompanyName(newSupplier.getCompany().getCompanyName());
			company.setRegNum(newSupplier.getCompany().getRegNum());
			company.setSupplier(supplier);
			
			//save company along with supplier
			Company savedCompany = companyService.saveCompany(company);
			
			// create value object to return
			CompanyDTO companyDTO = new CompanyDTO();
			companyDTO.setCompanyName(savedCompany.getCompanyName());
			companyDTO.setRegNum(savedCompany.getRegNum());
			supplierDTO.setCompany(companyDTO);
			supplierDTO.setTaxNum(savedCompany.getSupplier().getTaxNum());
			supplierDTO.setOrderLeadTime(savedCompany.getSupplier().getOrderLeadTime());
			supplierDTO.setPhoneNumber(savedCompany.getSupplier().getPhoneNumber());
			return supplierDTO;  
		}catch(Exception e) {
			throw e;
		}
	}
	private SupplierDTO savePersonSupplier(SupplierDTO newSupplier) {
		SupplierDTO supplierDTO= new SupplierDTO();
		try {
			
			//prepare data to save
			SupplierPerson supplierPerson = new SupplierPerson();
			supplierPerson.setOrderLeadTime(newSupplier.getOrderLeadTime());
			supplierPerson.setTaxNum(newSupplier.getTaxNum());
			supplierPerson.setPhoneNumber(newSupplier.getPhoneNumber());
			Person newPerson = new Person();
			newPerson.setFirstName(newSupplier.getPerson().getFirstName());
			newPerson.setLastName(newSupplier.getPerson().getLastName());
			newPerson.setSupplier(supplierPerson);
			
			//save person along with customer
			Person savedPerson = personService.savePerson(newPerson);
			
			// create value object to return
			PersonDTO personDTO = new PersonDTO();
			personDTO.setFirstName(savedPerson.getFirstName());
			personDTO.setLastName(savedPerson.getLastName()); 
			supplierDTO.setPerson(personDTO);
			supplierDTO.setTaxNum(savedPerson.getSupplier().getTaxNum());
			supplierDTO.setOrderLeadTime(savedPerson.getSupplier().getOrderLeadTime());
			supplierDTO.setPhoneNumber(savedPerson.getSupplier().getPhoneNumber());
			return supplierDTO;  
		}catch(Exception e) {
			throw e;
		}
	}
	
	public SupplierDTO updateSupplier(SupplierDTO updateSupplier, Long supplierId) throws Exception {
		try {
			if(updateSupplier.getCompany()!=null) {
				return updateSupplierCompany(updateSupplier, supplierId);
			}else if(updateSupplier.getPerson()!=null) {
				return updateSupplierPerson(updateSupplier, supplierId);
			}
			return null;
		} catch (Exception e) {
			throw e;
		}
	}
	
	private SupplierDTO updateSupplierCompany(SupplierDTO updateSupplier, Long supplierId) throws Exception{
		try {
			SupplierDTO supplierDTO= new SupplierDTO();
			Company company = this.companyService.findBySupplierId(supplierId);
			if(company==null) {
				return saveSupplier(updateSupplier);
			}
			
			//prepare data to update
			SupplierCompany supplier = company.getSupplier();
			supplier.setTaxNum(updateSupplier.getTaxNum());
			supplier.setOrderLeadTime(updateSupplier.getOrderLeadTime());
			supplier.setPhoneNumber(updateSupplier.getPhoneNumber());
			company.setCompanyName(updateSupplier.getCompany().getCompanyName());
			company.setRegNum(updateSupplier.getCompany().getRegNum());
			company.setSupplier(supplier);
			//update company along with supplier
			Company updatedSupplierCompany = companyService.updateCompany(company, company.getId());
			
			// create value object to return
			CompanyDTO companyDTO = new CompanyDTO();
			companyDTO.setCompanyName(updatedSupplierCompany.getCompanyName());
			companyDTO.setRegNum(updatedSupplierCompany.getRegNum());
			supplierDTO.setCompany(companyDTO);
			supplierDTO.setTaxNum(updatedSupplierCompany.getSupplier().getTaxNum());
			supplierDTO.setOrderLeadTime(updatedSupplierCompany.getSupplier().getOrderLeadTime());
			supplierDTO.setPhoneNumber(updatedSupplierCompany.getSupplier().getPhoneNumber());
			return supplierDTO;
		}catch(Exception e) {
			throw e;
		}
	}
	
	private SupplierDTO updateSupplierPerson(SupplierDTO updateSupplier, Long supplierId) throws Exception {
		try {
			SupplierDTO supplierDTO= new SupplierDTO();
			Person person = this.personService.findBySupplierId(supplierId);
			if(person==null) {
				return saveSupplier(updateSupplier);
			}
			
			//prepare data to update
			SupplierPerson supplier = person.getSupplier();
			supplier.setOrderLeadTime(updateSupplier.getOrderLeadTime());
			supplier.setTaxNum(updateSupplier.getTaxNum());
			supplier.setPhoneNumber(updateSupplier.getPhoneNumber());
			person.setFirstName(updateSupplier.getPerson().getFirstName());
			person.setLastName(updateSupplier.getPerson().getLastName());
			person.setSupplier(supplier);
			
			//update person along with supplier
			Person updatedSupplierPerson = personService.updatePerson(person, person.getId());
			
			// create value object to return
			PersonDTO personDTO = new PersonDTO();
			personDTO.setFirstName(updatedSupplierPerson.getFirstName());
			personDTO.setLastName(updatedSupplierPerson.getLastName());
			supplierDTO.setPerson(personDTO);
			supplierDTO.setOrderLeadTime(updatedSupplierPerson.getSupplier().getOrderLeadTime());
			supplierDTO.setTaxNum(updatedSupplierPerson.getSupplier().getTaxNum());
			supplierDTO.setPhoneNumber(updatedSupplierPerson.getCustomer().getPhoneNumber());
			return supplierDTO;
		}catch(Exception e) {
			throw e;
		}
	}
	
	
	public List<SupplierDTO> findAllSuppliers(){
		List<SupplierDTO> supplierDTOList = new ArrayList<SupplierDTO>();
		try {
			List<Supplier> supplierList = supplierRepository.findAll();
			supplierList.stream().forEach(x->{
				SupplierDTO supplierDTO = new SupplierDTO();
				PersonDTO personDTO = new PersonDTO();
				CompanyDTO companyDTO = new CompanyDTO();
				supplierDTO.setOrderLeadTime(x.getOrderLeadTime());
				supplierDTO.setTaxNum(x.getTaxNum());
				supplierDTO.setPhoneNumber(x.getPhoneNumber());
				Company company = this.companyService.findBySupplierId(x.getId());
				Person person = this.personService.findBySupplierId(x.getId());
				if(company!=null) {
					companyDTO.setCompanyName(company.getCompanyName());
					companyDTO.setRegNum(company.getRegNum());
					companyDTO.setId(company.getId());
					supplierDTO.setCompany(companyDTO);
				}
				if(person!=null) {
					personDTO.setFirstName(person.getFirstName());
					personDTO.setLastName(person.getLastName());
					personDTO.setId(person.getId());
					supplierDTO.setPerson(personDTO);
				}
				supplierDTOList.add(supplierDTO);
			});
			return supplierDTOList;
		}catch(Exception e) {
			throw e;
		}
	}
}
