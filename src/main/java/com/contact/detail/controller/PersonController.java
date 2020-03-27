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

import com.contact.detail.entity.Person;
import com.contact.detail.services.PersonService;

/**
 * A RESTFul controller for accessing Person information via
 * {@link PersonService}.
 */

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1")
public class PersonController {

	@Autowired
	private PersonService personService;

	/**
	 * 
	 * @param newPerson
	 * @return newly saved person
	 */
	@PostMapping("/persons")
	public ResponseEntity<ResponseData<Person>> save(@RequestBody Person newPerson) {
		ResponseData<Person> appResponse = new ResponseData<>();
		try {
			if (newPerson.getCustomer() == null && newPerson.getSupplier() == null) {
				appResponse.setResponseMessage("A person should atleast be a customer or supplier or both");
				return ResponseEntity.ok().body(appResponse);
			}
			appResponse.setPayload(personService.savePerson(newPerson));
			return ResponseEntity.ok().body(appResponse);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 
	 * @return list of persons
	 */
	@GetMapping("/persons")
	public ResponseEntity<ResponseData<List<Person>>> persons() {
		ResponseData<List<Person>> appResponse = new ResponseData<>();
		try {
			List<Person> personList = personService.fetchAllPersons();
			appResponse.setPayload(personList);
			if (personList != null && !personList.isEmpty()) {
				appResponse.setPayload(personList);
			} else {
				appResponse.setResponseMessage("No person(s) found");
				appResponse.setPayload(null);
			}
			return ResponseEntity.ok().body(appResponse);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 
	 * @param person
	 * @param personid
	 * @return updated person
	 */
	@PatchMapping("/persons/{personid}")
	public ResponseEntity<ResponseData<Person>> persons(@RequestBody Person person, @PathVariable Long personid) {
		ResponseData<Person> appResponse = new ResponseData<>();
		try {
			Person prsn = personService.updatePerson(person, personid);
			if (prsn == null) {
				appResponse.setResponseMessage("Person not found");
			} else {
				appResponse.setPayload(prsn);
			}
			return ResponseEntity.ok().body(appResponse);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 
	 * @param personid
	 */
	@DeleteMapping("/persons/{personid}")
	public void delete(@PathVariable Long personid) {
		try {
			personService.deletePerson(personid);
		} catch (Exception e) {
			throw e;
		}
	}

}
