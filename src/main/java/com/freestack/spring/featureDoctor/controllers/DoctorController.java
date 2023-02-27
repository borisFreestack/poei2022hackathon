package com.freestack.spring.featureDoctor.controllers;

import com.freestack.spring.EntityManagerFactorySingleton;
import com.freestack.spring.featureDoctor.models.Doctor;
import com.freestack.spring.featureDoctor.models.OpenCloseTime;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

	@PostMapping
	public Doctor register(@RequestBody Doctor newDoctor) {
		EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
		em.getTransaction().begin();
		em.persist(newDoctor);
		em.getTransaction().commit();
		return newDoctor;
	}

	@PostMapping("/openCloseTime")
	public OpenCloseTime storeOpenCloseTime(@RequestBody OpenCloseTime openCloseTime) {
		EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
		em.getTransaction().begin();
		em.persist(openCloseTime);
		em.getTransaction().commit();
		return openCloseTime;
	}
}
