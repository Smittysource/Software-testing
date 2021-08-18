/*	TaskServiceTest.java:  This file contains the test class specification for the task service class
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

import taskservice.Task;
import taskservice.TaskService;

class TaskServiceTest {
	// Variable used to store data for each test 
	TaskService service = new TaskService();
	private String tenChar1 = "12A34B56C7";
	private String tenChar2 = "12A34B56C8";
	private String tenChar3 = "12A34B56C9";
	private String twentyChar = tenChar1 + tenChar2;
	private String fiftyChar = twentyChar + twentyChar + tenChar3;

	
	// Perform this before each test. Empties previous values from service and sets it up for next test.
	@BeforeEach
	void setupTaskServiceTests() {
		// Remove all data from service
		service.empty();
		// Create a task
		Task myTask = new Task(tenChar1, twentyChar, fiftyChar);
		// Add task to service
		service.add(myTask);
		// Create another task
		myTask = new Task(tenChar2, tenChar1, tenChar2);
		// Add task to service
		service.add(myTask);		
	}
	
	// Check that added task is correct
	@Test
	void testTaskServiceAddTask() {
		// Create a task
		Task myTask = new Task(tenChar3, tenChar1, tenChar2);
		// Add task to service
		service.add(myTask);
		// Retrieve task from service
		Task TestTask = service.getTask(tenChar3);
		// Check that all values match original task
		Assertions.assertTrue(myTask.getTaskId().equals(TestTask.getTaskId()));
		Assertions.assertTrue(myTask.getName().equals(TestTask.getName()));
		Assertions.assertTrue(myTask.getDescription().equals(TestTask.getDescription()));
	}
	
	// Check process when adding a task that already exists. This should throw error since already existing taskIds should be updated instead of added
	@Test
	void testTaskServiceTaskAddAlreadyExists() {
		// Create task
		Task myTask = new Task(tenChar1, tenChar1, tenChar2);
		// Check for exception when adding to service
		Assertions.assertThrows(IllegalStateException.class, () -> {
			service.add(myTask);
		});
	}
 
	// Check for empty service after calling Empty()
	@Test
	void testTaskServiceEmpty() {
		// Data is currently in service from @BeforeEach
		Assertions.assertFalse(service.checkEmpty());
		// Empty service
		service.empty();
		// Make sure that service is empty
		Assertions.assertTrue(service.checkEmpty());
	}
	
	// Check that a task has actually been deleted
	@Test
	void testTaskServiceTaskDeleted() {
		// Remove an existing task
		service.delete(tenChar1);
		// Try to retrieve the same task from service
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			service.getTask(tenChar1);
		});
	}
	
	// Check that service can handle attempting to delete an item in an empty list
	@Test
	void testTaskServiceDeleteWhenEmpty() {
		// Remove all tasks from list
		service.empty();
		// Attempt to delete task that does not exist in empty list
		Assertions.assertTrue(service.delete(tenChar1));
	}

	// Check that service notifies that it did not delete an item it couldn't find
	@Test
	void testTaskServiceDeleteNotFound() {
		// Attempt to delete a task that doesn't exist in populated list
		Assertions.assertFalse(service.delete(tenChar3));
	}
	
	// Check that a task that exists in service is updated
	@Test
	void testTaskServiceTaskUpdated() {
		// Retrieve task from service
		Task TestTask = service.getTask(tenChar1);
		// Create task with existing taskId
		Task myTask = new Task(tenChar1, tenChar2, tenChar3);
		// Update service list with task
		service.update(myTask);
		
		// Ensure that data is the same as data updated
		Assertions.assertTrue(TestTask.getTaskId().equals(myTask.getTaskId()));
		Assertions.assertFalse(TestTask.getName().equals(myTask.getName()));
		Assertions.assertFalse(TestTask.getDescription().equals(myTask.getDescription()));
	}
	
	// Check that task not updated if taskId does not exist in service list
	@Test
	void testTaskServiceTaskNotUpdated() {
		// Attempt to use Update for a taskId that does not exist in the list
		Task myTask = new Task(tenChar3, tenChar2, tenChar3);
		// Ensure using Add is enforced
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			service.update(myTask);
		});
	}
}
