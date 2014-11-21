package connection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import connection.dto.Response;
import users.controller.UserController;
import users.dto.User;
import users.services.UserVerification;

public class Request extends Thread{
	
	Socket socket;
	User user;
	
	public Request(Socket socket){
		this.socket = socket;
	}
	
	public void run() {
		System.out.println("Client connected");
		System.out.println("new user connected from " + socket.getInetAddress().toString());
		

		try {
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			try {
				user = (User) ois.readObject();
				System.out.println("Получен юзер с сайта " + user);
			} catch (ClassNotFoundException e) {
				System.out.println("Получен не юзер");
				e.printStackTrace();
			}
			
		} catch (IOException e) {
			System.out.println("Не получилость считать с потока");
			e.printStackTrace();
		}
			
		
		// TODO ЧИСТО ДЛЯ ТЕСТОВОЙ ФОРМЫ
			UserController uControll = new UserController();	
			Response response =uControll.login(user);
			
			try {
				ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
				oos.writeObject(response);
			} catch (IOException e) {
				System.out.println("Не удалось открыть поток(ObjectOutputStream)");
				e.printStackTrace();
			}
						
	}
}

