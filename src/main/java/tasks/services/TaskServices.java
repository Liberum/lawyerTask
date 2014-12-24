package tasks.services;

import java.util.Map;

import tasks.dao.TaskDaoDB;
import tasks.dto.Task;
import users.dto.User;

public class TaskServices {
	public Map <Integer, Task>loadAllTasks(User user){
		TaskDaoDB dbTasks = new TaskDaoDB();
		Map <Integer, Task> allTasks =dbTasks.loadAllTasksForUser(user);
		return allTasks;
	}
	
	public void saveNewTask(Task newTask){
		TaskDaoDB db = new TaskDaoDB();
		db.saveTask(newTask);
	}
	
	public void editTask(Task newTask){
		TaskDaoDB db = new TaskDaoDB();
		db.updateTask(newTask);
	}
	
}
