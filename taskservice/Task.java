/*	Task.java:  This file contains the class specification for a task for the mobile application
 * 
 * 	Author: 	David Smith
 * 	Course: 	CS-320
 * 	Date:		August 8, 2021 	
 * 	Instructor: Professor A. Luo
 * 	Version:	2.0
 */

package taskservice;

// Task Class
public class Task {
	// Fields to store data for a Task
	private String taskId;			// Unique ID
	private String name;			// Name of task
	private String description;		// Description of task
	
	// Constructor
	public Task(String inputTaskId, String inputName, String inputDescription) throws IllegalArgumentException {
		// Check for thrown exceptions
		try {
			setDescription(inputDescription);
			setName(inputName);
			setTaskId(inputTaskId);
		} catch(IllegalArgumentException e) {
			System.out.println(e + "\n Error creating task.");
			throw e;
		} finally {}		
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
	
	// Mutator for name
	public void setName(String inputName) throws IllegalArgumentException{
		// Name cannot be null
		if(inputName == null) {
			throw new IllegalArgumentException("Name cannot be null.");
		}
		// Name cannot be more than 10 characters
		if(inputName.length() > 20) {
			throw new IllegalArgumentException("Name cannot be more than 20 characters long.");
		}
		// If no exceptions are thrown, assign Name to field
		name = inputName;
	}
	
	// Mutator for taskId
	private void setTaskId(String inputTaskId) throws IllegalArgumentException{
		// TaskId cannot be null
		if(inputTaskId == null) {
			throw new IllegalArgumentException("Task Id cannot be null.");
		}
		// TaskId cannot be more than 10 characters
		if(inputTaskId.length() > 10) {
			throw new IllegalArgumentException("Task ID cannot be more than 10 characters long.");
		}
		// If no exceptions are thrown, assign taskId to field
		taskId = inputTaskId;
	}
	
	// Accessor for description
	public String getDescription() {
		return description;
	}
	
	// Accessor for name
	public String getName() {
		return name;
	}
	
	// Accessor for taskId
	public String getTaskId() {
		return taskId;
	}
	
	

}
