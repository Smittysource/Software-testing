/*	TaskService.java:  This file contains the class specification for a task service for the mobile application
 * 
 * 	Author: 	David Smith
 * 	Course: 	CS-320
 * 	Date:		August 8, 2021 	
 * 	Instructor: Professor A. Luo
 * 	Version:	2.0
 */

package taskservice;

import java.util.HashMap;
import java.util.Map;

// TaskService class
public class TaskService {
	// Declare storage for tasks
	private final Map<String, Task> tasks = new HashMap<String, Task>();
	
	
	// Add a task. Check if taskId already exists. If it does, enforce need to use Update method
	public void add(Task inputTask) throws IllegalStateException{
		// If the list is empty, there are no conflicts. Add the task
		if(tasks.isEmpty()) {
			tasks.put(inputTask.getTaskId(), inputTask);
			return;
		}
		// If the list is not empty, check for conflicts
		if(tasks.containsKey(inputTask.getTaskId())) {
			throw new IllegalStateException("This task id already exists: " + inputTask.getTaskId() + ".");
		}
		// If no conflict found, add the task
		tasks.put(inputTask.getTaskId(), inputTask);
	}
	
	// Update task with same taskId as the input task. Enforce adding a new task using Add method.
	public void update(Task inputTask) throws IllegalArgumentException{
		// Check if id exists. If so, allow update
		if(tasks.containsKey(inputTask.getTaskId())) {
			tasks.remove(inputTask.getTaskId());
			tasks.put(inputTask.getTaskId(), inputTask);
			return;
		}

		// If input task's taskId is not in the list, enforce adding new task through the Add method
		throw new IllegalArgumentException("The task id " + inputTask.getTaskId() + " was not found.");
	}
	
	// Delete a task from the list based on taskId
	public boolean delete(String inputTaskId) {
		// If list is empty, there is nothing to do
		if(tasks.isEmpty()) {
			return true;
		}
		// Iterate through list
		if(tasks.containsKey(inputTaskId)) {
			tasks.remove(inputTaskId);
			return true;
		}
		return false;
	}
	
	// Empty the list of all tasks
	public void empty() {
		// If the list is empty, there is nothing to do
		tasks.clear();
	}
	
	// Get a task based on taskId
	public Task getTask(String inputTaskId) throws IllegalArgumentException {
		// Check if task exists in list
		Task tempTask = tasks.get(inputTaskId);
		if(tempTask != null) {
			// Return the found task
			return tempTask;
		}
		// If item does not exist in the list, notify of error
		throw new IllegalArgumentException("The task id " + inputTaskId + " was not found.");
	}
	
	// Check if the list is empty
	public boolean checkEmpty() {
		if(tasks.isEmpty()) {
			return true;
		}
		else {
			return false;
		}
	}	
}
