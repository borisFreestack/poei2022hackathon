package com.freestack.spring.featureBooking.controllers;

import com.freestack.spring.featureDoctor.models.Doctor;
import com.freestack.spring.featurePatient.models.featureDoctor.models.Patient;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Availability {

	private Doctor doctor;
	private LocalDateTime time;
	private LocalDate day;

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	public LocalDate getDay() {
		return day;
	}

	public void setDay(LocalDate day) {
		this.day = day;
	}
}
