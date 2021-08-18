/*	AppointmentServiceTest.java:  This file contains the test class specification for the appointment service class
 * 
 * 	Author: 	David Smith
 * 	Course: 	CS-320
 * 	Date:		August 8, 2021 	
 * 	Instructor: Professor A. Luo
 * 	Version:	2.0
 */

package test;

import java.time.Duration;
import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import appointmentservice.Appointment;
import appointmentservice.AppointmentService;

import org.junit.jupiter.api.BeforeAll;

class AppointmentServiceTest {
	// Variable used to store data for each test 
	AppointmentService service = new AppointmentService();
	private String tenChar1 = "12A34B56C7";
	private String tenChar2 = "12A34B56C8";
	private String tenChar3 = "12A34B56C9";
	private String fiftyChar = tenChar1 + tenChar2 + tenChar3 + tenChar1 + tenChar2;
	private static Date tomorrow;
	private static Date nextDay;

	// Set appointment times
	@BeforeAll
	static void setupAppointmentTests() {
		tomorrow = new Date();
		tomorrow = Date.from(tomorrow.toInstant().plus(Duration.ofDays(1)));
		nextDay = Date.from(tomorrow.toInstant().plus(Duration.ofDays(1)));
	}

	// Perform this before each test. Empties previous values from service and sets it up for next test.
	@BeforeEach
	void setupAppointmentServiceTests() {
		// Remove all data from service
		service.empty();
		// Create a appointment
		Appointment myAppointment = new Appointment(tenChar1, tomorrow, fiftyChar);
		// Add appointment to service
		service.add(myAppointment);
		// Create another appointment
		myAppointment = new Appointment(tenChar2, tomorrow, tenChar2);
		// Add appointment to service
		service.add(myAppointment);		
	}
	
	// Check that added appointment is correct
	@Test
	void testAppointmentServiceAddAppointment() {
		// Create a appointment
		Appointment myAppointment = new Appointment(tenChar3, tomorrow, tenChar2);
		// Add appointment to service
		service.add(myAppointment);
		// Retrieve appointment from service
		Appointment TestAppointment = service.getAppointment(tenChar3);
		// Check that all values match original appointment
		Assertions.assertTrue(myAppointment.getAppointmentId().equals(TestAppointment.getAppointmentId()));
		Assertions.assertTrue(myAppointment.getDate().equals(TestAppointment.getDate()));
		Assertions.assertTrue(myAppointment.getDescription().equals(TestAppointment.getDescription()));
	}
	
	// Check process when adding a appointment that already exists. This should throw error since already existing appointmentIds should be updated instead of added
	@Test
	void testAppointmentServiceAppointmentAddAlreadyExists() {
		// Create appointment
		Appointment myAppointment = new Appointment(tenChar1, tomorrow, tenChar2);
		// Check for exception when adding to service
		Assertions.assertThrows(IllegalStateException.class, () -> {
			service.add(myAppointment);
		});
	}
 
	// Check for empty service after calling empty()
	@Test
	void testAppointmentServiceEmpty() {
		// Data is currently in service from @BeforeEach
		Assertions.assertFalse(service.checkEmpty());
		// Empty service
		service.empty();
		// Make sure that service is empty
		Assertions.assertTrue(service.checkEmpty());
	}
	
	// Check that an appointment has actually been deleted
	@Test
	void testAppointmentServiceAppointmentDeleted() {
		// Remove an existing appointment
		service.delete(tenChar1);
		// Try to retrieve the same appointment from service
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			service.getAppointment(tenChar1);
		});
	}
	
	// Check that service can handle attempting to delete an item in an empty list
	@Test
	void testAppointmentServiceDeleteWhenEmpty() {
		// Remove all appointments from list
		service.empty();
		// Attempt to delete appointment that does not exist in empty list
		Assertions.assertTrue(service.delete(tenChar1));
	}

	// Check that service notifies that it did not delete an item it couldn't find
	@Test
	void testAppointmentServiceDeleteNotFound() {
		// Attempt to delete an appointment that doesn't exist in populated list
		Assertions.assertFalse(service.delete(tenChar3));
	}
	
	// Check that an appointment that exists in service is updated
	@Test
	void testAppointmentServiceAppointmentUpdated() {
		// Retrieve appointment from service
		Appointment TestAppointment = service.getAppointment(tenChar1);
		// Create appointment with existing appointmentId
		Appointment myAppointment = new Appointment(tenChar1, nextDay, tenChar3);
		// Update service list with appointment
		service.update(myAppointment);
		
		// Ensure that data is the same as data updated
		Assertions.assertTrue(TestAppointment.getAppointmentId().equals(myAppointment.getAppointmentId()));
		Assertions.assertFalse(TestAppointment.getDate().equals(myAppointment.getDate()));
		Assertions.assertFalse(TestAppointment.getDescription().equals(myAppointment.getDescription()));
	}
	
	// Check that appointment not updated if appointmentId does not exist in service list
	@Test
	void testAppointmentServiceAppointmentNotUpdated() {
		// Attempt to use Update for a appointmentId that does not exist in the list
		Appointment myAppointment = new Appointment(tenChar3, tomorrow, tenChar3);
		// Ensure using Add is enforced
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			service.update(myAppointment);
		});
	}

}
