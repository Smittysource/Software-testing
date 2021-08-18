/*	Appointment.java:  This file contains the test class specification for the appointment class
 * 
 * 	Author: 	David Smith
 * 	Course: 	CS-320
 * 	Date:		August 8, 2021 	
 * 	Instructor: Professor A. Luo
 * 	Version:	2.0
 */

package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import appointmentservice.Appointment;

import org.junit.jupiter.api.BeforeAll;

import java.time.Duration;
import java.util.Date;

class AppointmentTest {
	private String tenChar = "12A34B56C7";
	private String fiftyChar = tenChar + tenChar + tenChar + tenChar + tenChar;
	private static Date tomorrow;
	private static Date yesterday;
	
	
	// Set appointment times
	@BeforeAll
	static void setupAppointmentTests() {
		tomorrow = new Date();
		tomorrow = Date.from(tomorrow.toInstant().plus(Duration.ofDays(1)));
		yesterday = Date.from(tomorrow.toInstant().minus(Duration.ofDays(2)));
	}

	// Test constructor
	@Test
	void testAppointmentClass() {
		// Construct with passed in values
		Appointment appointment = new Appointment(tenChar, tomorrow, fiftyChar);
		// Check that each value contains the correct string
		assertTrue(appointment.getDescription().equals(fiftyChar));
		assertTrue(appointment.getDate().equals(tomorrow));
		assertTrue(appointment.getAppointmentId().equals(tenChar));
	}

	// Check if description is too long
	@Test
	void testAppointmentSetDescriptionTooLong() {
		// Description supplied is too long
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Appointment(tenChar, tomorrow, fiftyChar + "A");});
	}
	
	// Check if description is null
	@Test
	void testAppointmentSetDescriptionNull() {
		// Description supplied is null
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Appointment(tenChar, tomorrow, null);});
	}

	// Check if name is too long
	@Test
	void testAppointmentSetDateInPast() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Appointment(tenChar, yesterday, fiftyChar);});
	}

	// Test if name is null
	@Test
	void testAppointmentSetDateNULL() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Appointment(tenChar, null, fiftyChar);});
	}
	
	// Test if appointmentId is too long
	@Test
	void testAppointmentSetAppointmentIdTooLong() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Appointment(tenChar + "A", tomorrow, fiftyChar);});
	}

	// Test if appointmentId is null
	@Test
	void testAppointmentSetAppointmentIdNull() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Appointment(null, tomorrow, fiftyChar);});
	}
}
