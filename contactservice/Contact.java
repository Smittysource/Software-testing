/*	Contact.java:  This file contains the class specification for a contact for the mobile application
 * 
 * 	Author: 	David Smith
 * 	Course: 	CS-320
 * 	Date:		August 8, 2021 	
 * 	Instructor: Professor A. Luo
 * 	Version:	2.0
 */

package contactservice;

// Contact Class
public class Contact {
	// Fields to store data for a Contact
	private String contactId;		// Unique ID
	private String firstName;
	private String lastName;
	private String number;			// Phone number
	private String address;			// Street address
	
	// Constructor
	public Contact(String inputContactId, String inputFirstName, String inputLastName, String inputNumber, String inputAddress) throws IllegalArgumentException {
		// Check for thrown exceptions
		try {
			setAddress(inputAddress);
			setContactId(inputContactId);
			setFirstName(inputFirstName);
			setLastName(inputLastName);
			setNumber(inputNumber);
		} catch(IllegalArgumentException e) {						// Argument passed is invalid
			System.out.println(e + "\n Error creating contact.");
			throw e;												// Pass exception up to calling method
		} finally {}
	}
	
	// Mutator for address
	public void setAddress(String inputAddress) throws IllegalArgumentException {
		// Address cannot be null
		if(inputAddress == null) {
			throw new IllegalArgumentException("Address cannot be null.");
		}
		// Address cannot be more than 30 characters
		if(inputAddress.length() > 30) {
			throw new IllegalArgumentException("Address cannot be more than 30 characters long.");
		}
		// If no exceptions are thrown, assign address to field
		address = inputAddress;
	}
	
	// Mutator for contactId
	public void setContactId(String inputContactId) throws IllegalArgumentException {
		// ContactId cannot be null
		if(inputContactId == null) {
			throw new IllegalArgumentException("Contact ID cannot be null.");
		}
		// ContactId cannot be more than 10 characters
		if(inputContactId.length() > 10) {
			throw new IllegalArgumentException("Contact ID cannot be more than 10 characters long.");
		}
		// If no exceptions are thrown, assign contactId to field
		contactId = inputContactId;
	}
	
	// Mutator for firstName
	public void setFirstName(String inputFirstName) throws IllegalArgumentException{
		// First name cannot be null
		if(inputFirstName == null) {
			throw new IllegalArgumentException("First Name cannot be null.");
		}
		// First name cannot be more than 10 characters
		if(inputFirstName.length() > 10) {
			throw new IllegalArgumentException("First Name cannot be more than 10 characters long.");
		}
		// If no exceptions are thrown, assign first name to field
		firstName = inputFirstName;
	}
	
	// Mutator for lastName
	public void setLastName(String inputLastName) throws IllegalArgumentException{
		// Last name cannot be null
		if(inputLastName == null) {
			throw new IllegalArgumentException("Last Name cannot be null.");
		}
		// Last name cannot be more than 10 characters
		if(inputLastName.length() > 10) {
			throw new IllegalArgumentException("Last Name cannot be more than 10 characters long.");
		}
		// If no exceptions are thrown, assign last name to field
		lastName = inputLastName;	
	}
	
	// Mutator for number
	public void setNumber(String inputNumber) throws IllegalArgumentException {
		// Number cannot be null
		if(inputNumber == null) {
			throw new IllegalArgumentException("Number cannot be null.");
		}
		// Number must be only digits
		if(!(inputNumber.matches("[0-9]+"))) {
			throw new IllegalArgumentException("Number must be only digits.");
		}
		// Number must be exactly 10 characters long
		if(!(inputNumber.length() == 10)) {
			throw new IllegalArgumentException("Number must be exactly 10 digits.");
		}
		// If no exceptions are thrown, assign number to field 
		number = inputNumber;
	}
	
	// Accessor for address
	public String getAddress() {
		return address;
	}
	
	// Accessor for contactId
	public String getContactId(){
		return contactId;
	}
	
	// Accessor for firstName
	public String getFirstName(){
		return firstName;
	}
	
	// Accessor for lastName
	public String getLastName(){
		return lastName;
	}
	
	// Accessor for number
	public String getNumber(){
		return number;
	}
}
