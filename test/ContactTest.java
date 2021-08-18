/*	ContactTest.java:  This file contains the test class specification for the contact class
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

import contactservice.Contact;

// Unit testing for Contact class in ContactTest class
class ContactTest {

	// Test constructor
	@Test
	void testContactClass() {
		// Construct with passed in values
		Contact contact = new Contact("A125B9", "Dexter", "Morgan", "5558675309", "8420 Palm Terrace #10B, Miami.");
		// Check that each value contains the correct string
		assertTrue(contact.getContactId().equals("A125B9"));
		assertTrue(contact.getFirstName().equals("Dexter"));
		assertTrue(contact.getLastName().equals("Morgan"));
		assertTrue(contact.getNumber().equals("5558675309"));
		assertTrue(contact.getAddress().equals("8420 Palm Terrace #10B, Miami."));
	}

	// Check if address is too long
	@Test
	void testContactClassAddressTooLong() {
		// Address supplied is too long
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Contact("A125B9", "Walter", "White", "5558675309", "308 Negra Arroyo Ln,Albuquerque");});
	}
	
	// Check if address is null
	@Test
	void testContactClassAddressNull() {
		// Supplied address is null
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Contact("A125B9", "Dexter", "Morgan", "5558675309", null);});
	}

	// Check if contact id is too long
	@Test
	void testContactClassContactIdTooLong() {
		// Supplied contact id is too long
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Contact("A125B9C8D34", "Dexter", "Morgan", "5558675309", "8420 Palm Terrace #10B, Miami.");});
	}

	// Check if contact id is null
	@Test
	void testContactClassContactIdNull() {
		// Contact id is null
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Contact(null, "Dexter", "Morgan", "5558675309", "8420 Palm Terrace #10B, Miami.");});
	}
	
	// Check if first name is too long
	@Test
	void testContactClassFirstNameTooLong() {
		// Supplied first name is too long
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Contact("A125B9", "Bay Harbor ", "Butcher", "5558675309", "8420 Palm Terrace #10B, Miami.");});
	}
	
	// Check if first name is null
	@Test
	void testContactClassFirstNameNull() {
		// Supplied first name is null
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Contact("A125B9", null, "Morgan", "5558675309", "8420 Palm Terrace #10B, Miami.");});
	}
	
	// Check if last name is too long
	@Test
	void testContactClassLastNameTooLong() {
		// Supplied last name is too long
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Contact("A125B9", "Dexter", "Bay Butcher", "5558675309", "8420 Palm Terrace #10B, Miami.");});
	}
	
	// Check if last name is null
	@Test
	void testContactClassLastNameNull() {
		// Supplied last name is too long
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Contact("A125B9", "Dexter", null, "5558675309", "8420 Palm Terrace #10B, Miami.");});
	}

	// Check if phone number is too long
	@Test
	void testContactClassNumberTooLong() {
		// Supplied phone number is too long
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Contact("A125B9", "Dexter", "Morgan", "15558675309", "8420 Palm Terrace #10B, Miami.");});
	}
	
	// Check if phone number is only digits
	@Test
	void testContactClassNumberNotNumeric() {
		// Phone number supplied is alphanumeric
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Contact("A125B9", "Dexter", "Morgan", "1555A75309", "8420 Palm Terrace #10B, Miami.");});
	}

	// Check if phone number is null
	@Test
	void testContactClassNumberNull() {
		// Supplied phone number is null
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Contact("A125B9", "Dexter", "Morgan", null, "8420 Palm Terrace #10B, Miami.");});
	}
}
