package connection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import tasks.controller.TaskController;
import users.controller.UserController;
import connection.dto.Response;

public class Request extends Thread{
	
	Socket socket;
	Response response;
	
	public Request(Socket socket){
		this.socket = socket;
	}
	
	@SuppressWarnings("null")
	public void run() {
		System.out.println("Client connected");
		System.out.println("new user connected from " + socket.getInetAddress().toString());
		

		try {
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			try {
				response = (Response) ois.readObject();
				System.out.println("Получен юзер с сайта " + response.getUser());
			} catch (ClassNotFoundException e) {
				System.out.println("Получен не юзер");
				e.printStackTrace();
			}
			
		} catch (IOException e) {
			System.out.println("Не получилость считать с потока");
			e.printStackTrace();
		}
			

		UserController uControll = new UserController();
		Response responseFromServer = null;
		switch (response.getControllerType()) {
		case "registration":
				
			responseFromServer =uControll.registration(response.getUser());	
			break;
		case "login":
			responseFromServer =uControll.login(response.getUser());			
			break;
		case "reset":
			responseFromServer =uControll.resetPassword((response.getUser()).getLogin());			
			break;
		case "task_list":
			TaskController taskC = new TaskController();
			responseFromServer = taskC.viewAllTasks(response.getUser());
			break;
			
		case "new_task":
			TaskController controllerForSave = new TaskController();
			responseFromServer = controllerForSave.saveNewTask(response.getUser(), response.getTask());
			break;
		
		case "update_task":
			TaskController controllerForEdit = new TaskController();
			responseFromServer = controllerForEdit.editTask(response.getUser(), response.getTask());
			break;

		default:
			//TODO Exption
			break;
		}

			try {
				ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
				oos.writeObject(responseFromServer);
			} catch (IOException e) {
				System.out.println("Не удалось открыть поток(ObjectOutputStream)");
				e.printStackTrace();
			}
						
	}
}

