/*	TaskTest.java:  This file contains the test class specification for the task class
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

import taskservice.Task;

class TaskTest {
	private String tenChar = "12A34B56C7";
	private String twentyChar = tenChar + tenChar;
	private String fiftyChar = twentyChar + twentyChar + tenChar;

	// Test constructor
	@Test
	void testTaskClass() {
		// Construct with passed in values
		Task task = new Task(tenChar, twentyChar, fiftyChar);
		// Check that each value contains the correct string
		assertTrue(task.getDescription().equals(fiftyChar));
		assertTrue(task.getName().equals(twentyChar));
		assertTrue(task.getTaskId().equals(tenChar));
	}

	// Check if description is too long
	@Test
	void testTaskSetDescriptionTooLong() {
		// Description supplied is too long
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Task(tenChar, twentyChar, fiftyChar + "A");});
	}
	
	// Check if description is null
	@Test
	void testTaskSetDescriptionNull() {
		// Description supplied is null
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Task(tenChar, twentyChar, null);});
	}

	// Check if name is too long
	@Test
	void testTaskSetNameTooLong() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Task(tenChar, twentyChar + "A", fiftyChar);});
	}

	// Test if name is null
	@Test
	void testTaskSetNameNULL() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Task(tenChar, null, fiftyChar);});
	}
	
	// Test if taskId is too long
	@Test
	void testTaskSetTaskIdTooLong() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Task(tenChar + "A", twentyChar, fiftyChar);});
	}

	// Test if taskId is null
	@Test
	void testTaskSetTaskIdNull() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Task(null, twentyChar, fiftyChar);});
	}
}
