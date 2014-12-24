package tasks.controller;

import java.util.Calendar;

import tasks.dto.AllTasks;
import tasks.dto.Task;
import tasks.services.TaskServices;
import users.dto.User;
import connection.dto.Response;

public class TaskController {
	public Response viewAllTasks(User user){
		TaskServices services = new TaskServices();
		AllTasks allTasks = new AllTasks();
		allTasks.setAllTasks(services.loadAllTasks(user));
		Response response = new Response();
		response.setTasks(allTasks);
		return response;
	}
	
	public Response saveNewTask(User user, Task task){
		TaskServices services = new TaskServices();
		// тестовая логика
		
		Calendar calendar = Calendar.getInstance();
		 long d = calendar.getTimeInMillis();
		 long d2 = d + 86400000 * 14;
		 long d3 = d + 86400000 * 30;
		
		task.setNextReminder(d2);
		task.setDeadLine(d3);
		//	
		services.saveNewTask(task);
		AllTasks allTasks = new AllTasks();
		allTasks.setAllTasks(services.loadAllTasks(user));
		Response response = new Response();
		response.setTasks(allTasks);
		
		//TODO генерация логики для напоминалки
		return response;
	}
	
	public Response editTask(User user, Task task){
		TaskServices services = new TaskServices();
		// тестовая логика
		
		Calendar calendar = Calendar.getInstance();
		 long d = calendar.getTimeInMillis();
		 long d2 = d + 86400000 * 14;
		 long d3 = d + 86400000 * 30;
		
		task.setNextReminder(d2);
		task.setDeadLine(d3);
		//	
		services.editTask(task);
		AllTasks allTasks = new AllTasks();
		allTasks.setAllTasks(services.loadAllTasks(user));
		Response response = new Response();
		response.setTasks(allTasks);
		
		//TODO генерация логики для напоминалки
		return response;
	}
	
}
