package com.freestack.spring.featureDoctor.models;

import javax.persistence.*;

@Entity
public class OpenCloseTime {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	private Doctor doctor;

	private int mondayStartHour;
	private int mondayEndHour;

	private int tuesdayStartHour;
	private int tuesdayEndHour;

	private int wednesdayStartHour;
	private int wednesdayEndHour;

	private int thursdayStartHour;
	private int thursdayEndHour;

	private int fridayStartHour;
	private int fridayEndHour;

	private int saturdayStartHour;
	private int saturdayEndHour;

	private int sundayStartHour;
	private int sundayEndHour;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public int getMondayStartHour() {
		return mondayStartHour;
	}

	public void setMondayStartHour(int mondayStartHour) {
		this.mondayStartHour = mondayStartHour;
	}

	public int getMondayEndHour() {
		return mondayEndHour;
	}

	public void setMondayEndHour(int mondayEndHour) {
		this.mondayEndHour = mondayEndHour;
	}

	public int getTuesdayStartHour() {
		return tuesdayStartHour;
	}

	public void setTuesdayStartHour(int tuesdayStartHour) {
		this.tuesdayStartHour = tuesdayStartHour;
	}

	public int getTuesdayEndHour() {
		return tuesdayEndHour;
	}

	public void setTuesdayEndHour(int tuesdayEndHour) {
		this.tuesdayEndHour = tuesdayEndHour;
	}

	public int getWednesdayStartHour() {
		return wednesdayStartHour;
	}

	public void setWednesdayStartHour(int wednesdayStartHour) {
		this.wednesdayStartHour = wednesdayStartHour;
	}

	public int getWednesdayEndHour() {
		return wednesdayEndHour;
	}

	public void setWednesdayEndHour(int wednesdayEndHour) {
		this.wednesdayEndHour = wednesdayEndHour;
	}

	public int getThursdayStartHour() {
		return thursdayStartHour;
	}

	public void setThursdayStartHour(int thursdayStartHour) {
		this.thursdayStartHour = thursdayStartHour;
	}

	public int getThursdayEndHour() {
		return thursdayEndHour;
	}

	public void setThursdayEndHour(int thursdayEndHour) {
		this.thursdayEndHour = thursdayEndHour;
	}

	public int getFridayStartHour() {
		return fridayStartHour;
	}

	public void setFridayStartHour(int fridayStartHour) {
		this.fridayStartHour = fridayStartHour;
	}

	public int getFridayEndHour() {
		return fridayEndHour;
	}

	public void setFridayEndHour(int fridayEndHour) {
		this.fridayEndHour = fridayEndHour;
	}

	public int getSaturdayStartHour() {
		return saturdayStartHour;
	}

	public void setSaturdayStartHour(int saturdayStartHour) {
		this.saturdayStartHour = saturdayStartHour;
	}

	public int getSaturdayEndHour() {
		return saturdayEndHour;
	}

	public void setSaturdayEndHour(int saturdayEndHour) {
		this.saturdayEndHour = saturdayEndHour;
	}

	public int getSundayStartHour() {
		return sundayStartHour;
	}

	public void setSundayStartHour(int sundayStartHour) {
		this.sundayStartHour = sundayStartHour;
	}

	public int getSundayEndHour() {
		return sundayEndHour;
	}

	public void setSundayEndHour(int sundayEndHour) {
		this.sundayEndHour = sundayEndHour;
	}
}
