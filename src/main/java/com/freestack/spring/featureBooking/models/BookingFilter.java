package com.freestack.spring.featureBooking.models;

import com.freestack.spring.featureDoctor.models.Doctor;

import java.time.LocalDate;

public class BookingFilter {
	private LocalDate day;
	private Doctor doctor;

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public LocalDate getDay() {
		return day;
	}

	public void setDay(LocalDate day) {
		this.day = day;
	}
}
