/*	Appointment.java:  This file contains the class specification for the appointment class
 * 
 * 	Author: 	David Smith
 * 	Course: 	CS-320
 * 	Date:		August 8, 2021 	
 * 	Instructor: Professor A. Luo
 * 	Version:	2.0
 */

package appointmentservice;

import java.util.Date;

public class Appointment {
	// Fields to store data for a Appointment
		private String appointmentId;		// Unique ID
		private Date appointmentDate;		// Date of appointment
		private String description;			// Description of appointment
		
		// Constructor
		public Appointment(String inputId, Date inputDate, String inputDescription) throws IllegalArgumentException {
			// Check for thrown exceptions
			try {
				setAppointmentId(inputId);
				setDescription(inputDescription);
				setDate(inputDate);
			} catch(IllegalArgumentException e) {
				System.out.println(e + "\n Error creating appointment.");
				throw e;
			} finally {}
		}
		
		// Mutator for appointmentId
		private void setAppointmentId(String inputId) throws IllegalArgumentException{
			// AppointmentId cannot be null
			if(inputId == null) {
				throw new IllegalArgumentException("Appointment Id cannot be null.");
			}
			// AppointmentId cannot be more than 10 characters
			if(inputId.length() > 10) {
				throw new IllegalArgumentException("Appointment ID cannot be more than 10 characters long.");
			}
			// If no exceptions are thrown, assign appointmentId to field
			appointmentId = inputId;
		}

		// Mutator for description
		public void setDescription(String inputDescription) throws IllegalArgumentException {
			// Description cannot be null
			if(inputDescription == null) {
				throw new IllegalArgumentException("Description cannot be null.");
			}
			// Description cannot be more than 50 characters
			if(inputDescription.length() > 50) {
				throw new IllegalArgumentException("Description cannot be more than 50 characters long.");
			}
			// If no exceptions are thrown, assign description to field
			description = inputDescription;
		}
		
		// Mutator for appointmentDate
		public void setDate(Date inputDate) throws IllegalArgumentException{
			// Date cannot be null
			if(inputDate == null) {
				throw new IllegalArgumentException("Date cannot be null.");
			}
			// Date cannot be more than 10 characters
			if(inputDate.before(new Date())) {
				throw new IllegalArgumentException("Date cannot be in the past.");
			}
			// If no exceptions are thrown, assign Date to field
			appointmentDate = inputDate;
		}
		// Accessor for appointmentId
		public String getAppointmentId() {
			return appointmentId;
		}
						
		// Accessor for description
		public String getDescription() {
			return description;
		}
		
		// Accessor for appointmentDate
		public Date getDate() {
			return appointmentDate;
		}
}
