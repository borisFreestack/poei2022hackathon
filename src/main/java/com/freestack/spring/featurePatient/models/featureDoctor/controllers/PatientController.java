package com.freestack.spring.featurePatient.models.featureDoctor.controllers;

import com.freestack.spring.EntityManagerFactorySingleton;
import com.freestack.spring.featurePatient.models.featureDoctor.models.Patient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;

@RestController
@RequestMapping("/patients")
public class PatientController {

	@PostMapping
	public Patient register(@RequestBody Patient newPatient) {
		EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
		em.getTransaction().begin();
		em.persist(newPatient);
		em.getTransaction().commit();
		return newPatient;
	}
}
