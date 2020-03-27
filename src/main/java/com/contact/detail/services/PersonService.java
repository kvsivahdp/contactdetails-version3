package com.contact.detail.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.contact.detail.entity.Person;
import com.contact.detail.repository.PersonRepository;

@Service
public class PersonService {
	@Autowired
	private PersonRepository personRepository;

	public List<Person> fetchAllPersons() {
		try {
			return personRepository.findAll();
		} catch (Exception e) {
			throw e;
		}
	}
	
	public Person savePerson(Person newPerson) {
		try {
			return personRepository.save(newPerson);
		} catch (Exception e) {
			throw e;
		}
	}

	public Person updatePerson(Person person, Long personid) {
		try {
			Optional<Person> personFound = personRepository.findById(personid);
			if (personFound.isPresent()) {
				Person prsn = personFound.get();
				prsn.setFirstName(person.getFirstName());
				prsn.setLastName(person.getLastName());
				//prsn.setPhoneNumber(person.getPhoneNumber());
				if (person.getCustomer() != null) {
					prsn.getCustomer().setCustNum(person.getCustomer().getCustNum());
					prsn.getCustomer().setLastOrderDate(person.getCustomer().getLastOrderDate());
				}
				if (person.getSupplier() != null) {
					prsn.getSupplier().setTaxNum(person.getSupplier().getTaxNum());
					prsn.getSupplier().setOrderLeadTime(person.getSupplier().getOrderLeadTime());
				}
				return personRepository.save(prsn);
			}
			return null;
		} catch (Exception e) {
			throw e;
		}
	}
	
	public Person findByCustomerId(Long customerId) {
		try {
			return this.personRepository.findByCustomerId(customerId);
		}catch(Exception e) {
			throw e;
		}
	}
	
	public Person findBySupplierId(Long supplierId) {
		try {
			return this.personRepository.findBySupplierId(supplierId);
		}catch(Exception e) {
			throw e;
		}
	}
	
	public void deletePerson(Long personid) {
		try {
			Optional<Person> person = personRepository.findById(personid);
			if (person.isPresent()) {
				personRepository.deleteById(personid);
			}
		} catch (Exception e) {
			throw e;
		}
	}

}
