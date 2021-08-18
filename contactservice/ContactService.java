/*	ContactService.java:  This file contains the class specification for a contact service for the mobile application
 * 
 * 	Author: 	David Smith
 * 	Course: 	CS-320
 * 	Date:		August 8, 2021 	
 * 	Instructor: Professor A. Luo
 * 	Version:	2.0
 */

package contactservice;

import java.util.Map;
import java.util.HashMap;

// ContactService class
public class ContactService {
	// Declare storage for contacts
	private Map<String, Contact> contacts = new HashMap<String, Contact>();

	// Add a contact. Check if contactId already exists. If it does, enforce need to use Update method
	public void add(Contact inputContact) throws IllegalStateException{
		// If the list is empty, there are no conflicts. Add the contact
		if(contacts.isEmpty()) {
			contacts.put(inputContact.getContactId(), inputContact);
			return;
		}
		// If the list is not empty, check for conflicts
		if(contacts.containsKey(inputContact.getContactId())) {
			throw new IllegalStateException("This contact id already exists: " + inputContact.getContactId() + ".");
		}
		// If no conflict found, add the contact
		contacts.put(inputContact.getContactId(), inputContact);
	}
	
	// Update contact with same contactId as the input contact. Enforce adding a new contact using Add method.
	public void update(Contact inputContact) throws IllegalArgumentException{
		// Check if id exists. If so, allow update
		if(contacts.containsKey(inputContact.getContactId())) {
			contacts.remove(inputContact.getContactId());
			contacts.put(inputContact.getContactId(), inputContact);
			return;
		}

		// If input contact's contactId is not in the list, enforce adding new contact through the Add method
		throw new IllegalArgumentException("The contact id " + inputContact.getContactId() + " was not found.");
	}
	
	// Delete a contact from the list based on contactId
	public boolean delete(String inputContactId) {
		// If list is empty, there is nothing to do
		if(contacts.isEmpty()) {
			return true;
		}
		// Iterate through list
		if(contacts.containsKey(inputContactId)) {
			contacts.remove(inputContactId);
			return true;
		}
		return false;
	}
	
	// Empty the list of all contacts
	public void empty() {
		// If the list is empty, there is nothing to do
		contacts.clear();
	}
	
	// Get a contact based on contactId
	public Contact getContact(String inputContactId) throws IllegalArgumentException {
		// Check if contact exists in list
		Contact tempContact = contacts.get(inputContactId);
		if(tempContact != null) {
			// Return the found contact
			return tempContact;
		}
		// If item does not exist in the list, notify of error
		throw new IllegalArgumentException("The contact id " + inputContactId + " was not found.");
	}
	
	// Check if the list is empty
	public boolean checkEmpty() {
		if(contacts.isEmpty()) {
			return true;
		}
		else {
			return false;
		}
	}	

}

