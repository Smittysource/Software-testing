/*	Appointment Service.java:  This file contains the class specification for the appointment service class
 * 
 * 	Author: 	David Smith
 * 	Course: 	CS-320
 * 	Date:		August 8, 2021 	
 * 	Instructor: Professor A. Luo
 * 	Version:	2.0
 */

package appointmentservice;

import java.util.HashMap;
import java.util.Map;

public class AppointmentService {
	// Declare storage for appointments
	private Map<String, Appointment> appointments = new HashMap<String, Appointment>();
	
	// Add an appointment. Check if appointmentId already exists. If it does, enforce need to use Update method
	public void add(Appointment inputAppointment) throws IllegalStateException{
		// If the list is empty, there are no conflicts. Add the appointment
		if(appointments.isEmpty()) {
			appointments.put(inputAppointment.getAppointmentId(), inputAppointment);
			return;
		}
		// If the list is not empty, check for conflicts
		if(appointments.containsKey(inputAppointment.getAppointmentId())) {
			throw new IllegalStateException("This appointment id already exists: " + inputAppointment.getAppointmentId() + ".");
		}
		// If no conflict found, add the appointment
		appointments.put(inputAppointment.getAppointmentId(), inputAppointment);
	}
	
	// Update appointment with same appointmentId as the input appointment. Enforce adding a new appointment using Add method.
	public void update(Appointment inputAppointment) throws IllegalArgumentException{
		// See if id already exists
		if(appointments.containsKey(inputAppointment.getAppointmentId())) {
			appointments.remove(inputAppointment.getAppointmentId());
			appointments.put(inputAppointment.getAppointmentId(), inputAppointment);
			return;
		}
		// If input appointment's appointmentId is not in the list, enforce adding new appointment through the Add method
		throw new IllegalArgumentException("The appointment id " + inputAppointment.getAppointmentId() + " was not found.");
	}
	
	// Delete a appointment from the list based on appointmentId
	public boolean delete(String inputAppointmentId) {
		// If list is empty, there is nothing to do
		if(appointments.isEmpty()) {
			return true;
		}
		// Check for matching appointmentId
		if(appointments.containsKey(inputAppointmentId)) {
			// Remove the matching item
			appointments.remove(inputAppointmentId);
		}
		return false;
	}
	
	// Empty the list of all appointments
	public void empty() {
		// If the list is empty, there is nothing to do
		appointments.clear();
	}
	
	// Get an appointment based on appointmentId
	public Appointment getAppointment(String inputAppointmentId) throws IllegalArgumentException {
		// Find the appointment
		Appointment tempAppointment = appointments.get(inputAppointmentId);
		if(tempAppointment != null) {
			return tempAppointment;
		}
		// If item does not exist in the list, notify of error
		throw new IllegalArgumentException("The appointment id " + inputAppointmentId + " was not found.");
	}
	
	// Check if the list is empty
	public boolean checkEmpty() {
		if(appointments.isEmpty()) {
			return true;
		}
		return false;
	}
}
