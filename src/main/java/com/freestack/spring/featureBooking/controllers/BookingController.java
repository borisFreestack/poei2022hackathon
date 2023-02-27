package com.freestack.spring.featureBooking.controllers;

import com.freestack.spring.EntityManagerFactorySingleton;
import com.freestack.spring.featureBooking.models.Booking;
import com.freestack.spring.featureBooking.models.BookingFilter;
import com.freestack.spring.featureDoctor.models.Doctor;
import com.freestack.spring.featureDoctor.models.OpenCloseTime;
import com.freestack.spring.featurePatient.models.featureDoctor.models.Patient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@RestController
@RequestMapping("/bookings")
public class BookingController {

	@PostMapping("/search")
	public ResponseEntity<List<Booking>> bookingListByDoctorByDay(@RequestBody BookingFilter bookingFilter) {
		EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
		List<Booking> bookings = em.createQuery(
		"select b from Booking b where doctor.id = :doctorId and day = :day",
		Booking.class
		)
		.setParameter("doctorId", bookingFilter.getDoctor().getId())
		.setParameter("day", bookingFilter.getDay()).getResultList();
		return ResponseEntity.ok(bookings);
	}

	@PostMapping("/availabilities")
	public List<Availability> availabilitiesListByDoctorByDay(@RequestBody BookingFilter bookingFilter) {
		EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
		List<Booking> bookings = em.createQuery(
		"select b from Booking b where doctor.id = :doctorId and day = :day",
		Booking.class
		)
		.setParameter("doctorId", bookingFilter.getDoctor().getId())
		.setParameter("day", bookingFilter.getDay()).getResultList();

		// on recherche les horaires d'ouverture/fermeture correspond au jour si le docteur les a définis
		// sinon on considère des journées de 7h à 22h, comme ça normalement il devrait rapidement
		// s'en occuper ^^
		Doctor doctor = em.find(Doctor.class, bookingFilter.getDoctor().getId());
		OpenCloseTime openCloseTime = doctor.getOpenCloseTime();
		int startHour = 7;
		int endHour = 22;
		if (Objects.nonNull(openCloseTime)) {
			switch (bookingFilter.getDay().getDayOfWeek().name().toLowerCase()) {
				case "monday":
					startHour = openCloseTime.getMondayStartHour();
					endHour = openCloseTime.getMondayEndHour();
					break;
				case "tuesday":
					startHour = openCloseTime.getTuesdayStartHour();
					endHour = openCloseTime.getTuesdayEndHour();
					break;
				case "wednesday":
					startHour = openCloseTime.getWednesdayStartHour();
					endHour = openCloseTime.getWednesdayEndHour();
					break;
				case "thursday":
					startHour = openCloseTime.getThursdayStartHour();
					endHour = openCloseTime.getThursdayStartHour();
					break;
				case "friday":
					startHour = openCloseTime.getFridayStartHour();
					endHour = openCloseTime.getFridayEndHour();
					break;
				case "saturday":
					startHour = openCloseTime.getSaturdayStartHour();
					endHour = openCloseTime.getSaturdayEndHour();
					break;
				case "sunday":
					startHour = openCloseTime.getSundayStartHour();
					endHour = openCloseTime.getSundayEndHour();
					break;
				default:
					startHour = 7;
					endHour = 22;
			}
		}
		List<Availability> availabilities = new ArrayList();
		for (int currentHour = startHour; currentHour < endHour; currentHour++) {
			LocalDateTime currentLocalDateTime = LocalDateTime.of(
			bookingFilter.getDay(),
			LocalTime.of(currentHour, 0)
			);

			if (!bookings.stream().anyMatch(booking -> booking.getTime().equals(currentLocalDateTime))) {
				Availability availability = new Availability();
				availability.setTime(currentLocalDateTime);
				availability.setDoctor(bookingFilter.getDoctor());
				availability.setDay(bookingFilter.getDay());
				availabilities.add(availability);
			}

		}
		return availabilities;
	}

	@PostMapping
	public ResponseEntity<Booking> book(@RequestBody Booking newBooking) {

		EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
		em.getTransaction().begin();

		Doctor doctor = em.find(Doctor.class, newBooking.getDoctor().getId());

		if (isAvailable(doctor, newBooking.getTime())) {
			Patient patient = em.find(Patient.class, newBooking.getPatient().getId());
			newBooking.setDay(newBooking.getTime().toLocalDate());
			newBooking.setDoctor(doctor);
			newBooking.setPatient(patient);
			em.persist(newBooking);
			em.getTransaction().commit();
			return ResponseEntity.status(HttpStatus.CREATED).body(newBooking);
		}
		return ResponseEntity.status(HttpStatus.CONFLICT).build();
	}

	private boolean isAvailable(Doctor doctor, LocalDateTime time) {
		if (time.getMinute() != 0) {
			return false;
		}
		EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
		try {
			em.createQuery("select b from Booking b where doctor = :doctor and time = :time")
			.setParameter("doctor", doctor)
			.setParameter("time", time).getSingleResult();
		} catch (NoResultException e) {
			return true;
		}
		return false;
	}
}
