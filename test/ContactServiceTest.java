/*	ContactServiceTest.java:  This file contains the test class specification for the contact service class
 * 
 * 	Author: 	David Smith
 * 	Course: 	CS-320
 * 	Date:		August 8, 2021 	
 * 	Instructor: Professor A. Luo
 * 	Version:	2.0
 */
package test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import contactservice.Contact;
import contactservice.ContactService;

// Test class for ContactService class
class ContactServiceTest {
	// Variable used for storing data for each test
	ContactService service = new ContactService();
	
	// Perform this before each test. Empties previous values from service and sets it up for next test.
	@BeforeEach
	 void setupContactServiceTests() {
		// Remove all data from service
		service.empty();
		// Create a contact
		Contact myContact = new Contact("A125B9", "Walter", "White", "5558675309", "308 Negra Arroyo Ln,Albuquerqu");
		// Add contact to service
		service.add(myContact);
		// Create another contact
		myContact = new Contact("A125B8", "Dexter", "Morgan", "5558675309", "8420 Palm Terrace #10B, Miami.");
		// Add contact to service
		service.add(myContact);
	}
	
	// Check that added contact is correct
	@Test
	void testContactServiceContactAdded() {
		// Create a contact
		Contact myContact = new Contact("A125B7", "Frank", "Castle", "5558675309", "60 Lafayette Street, New York");
		// Add contact to service
		service.add(myContact);
		// Retrieve contact from service using contactId
		Contact TestContact = service.getContact(myContact.getContactId());
		// Check that all values match original contact
		Assertions.assertTrue(myContact.getContactId().equals(TestContact.getContactId()));
		Assertions.assertTrue(myContact.getFirstName().equals(TestContact.getFirstName()));
		Assertions.assertTrue(myContact.getLastName().equals(TestContact.getLastName()));
		Assertions.assertTrue(myContact.getAddress().equals(TestContact.getAddress()));
		Assertions.assertTrue(myContact.getNumber().equals(TestContact.getNumber()));
	}
	
	// Check process when adding a contact that already exists. This should throw error since already existing contactIds should be updated instead of added
	@Test
	void testContactServiceContactAddAlreadyExists() {
		// Create contact
		Contact myContact = new Contact("A125B9", "Walter", "White", "5558675309", "308 Negra Arroyo Ln,Albuquerqu");
		// Check for exception when adding to service
		Assertions.assertThrows(IllegalStateException.class, () -> {
			service.add(myContact);
		});
	}
	
	// Check for empty service after calling Empty()
	@Test
	void testContactServiceEmpty() {
		// Data is currently in service from @BeforeEach
		Assertions.assertFalse(service.checkEmpty());
		// Empty service
		service.empty();
		// Make sure that service is empty
		Assertions.assertTrue(service.checkEmpty());
	}
	
	// Check that a contact has actually been deleted
	@Test
	void testContactServiceContactDeleted() {
		// Remove an existing contact
		service.delete("A125B9");
		// Try to retrieve the same contact from service
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			service.getContact("A125B9");
		});
	}
	
	// Check that service can handle attempting to delete an item in an empty list
	@Test
	void testContactServiceDeleteWhenEmpty() {
		// Remove all contacts from list
		service.empty();
		// Attempt to delete contact that does not exist in empty list
		Assertions.assertTrue(service.delete("A125B9"));
	}
	
	// Check that service notifies that it did not delete an item it couldn't find
	@Test
	void testContactServiceDeleteNotFound() {
		// Attempt to delete a contact that doesn't exist in populated list
		Assertions.assertFalse(service.delete("12345"));
	}
	
	// Check that a contact that exists in service is updated
	@Test
	void testContactServiceContactUpdated() {
		// Retrieve contact from service
		Contact TestContact = service.getContact("A125B9");
		// Create contact with existing contactId
		Contact myContact = new Contact("A125B9", "Mike", "Myers", "5555555555", "224 Rodeo Boulevard");
		// Update service list with contact
		service.update(myContact);
		
		// Ensure that data is the same as data updated
		Assertions.assertTrue(TestContact.getContactId().equals(myContact.getContactId()));
		Assertions.assertFalse(TestContact.getFirstName().equals(myContact.getFirstName()));
		Assertions.assertFalse(TestContact.getLastName().equals(myContact.getLastName()));
		Assertions.assertFalse(TestContact.getNumber().equals(myContact.getNumber()));
		Assertions.assertFalse(TestContact.getAddress().equals(myContact.getAddress()));
	}
	
	// Check that contact not updated if contactId does not exist in service list
	@Test
	void testContactServiceContactNotUpdataed() {
		// Attempt to use Update for a contactId that does not exist in the list
		Contact myContact = new Contact("B125A9", "Mike", "Myers", "5558675309", "308 Negra Arroyo Ln,Albuquerqu");
		// Ensure using Add is enforced
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			service.update(myContact);
		});
	}
}
