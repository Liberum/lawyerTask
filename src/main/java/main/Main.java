package main;
import users.dao.DBinmemory;
import users.dto.User;
import connection.Joining;


public class Main {
	public static DBinmemory tempDB = new DBinmemory();
	public static void main(String[] args) throws ClassNotFoundException {
		System.out.println("Начинаем");
		
		while (true) {
	
			User user = new User();
			user.setLogin("admin@admin.net");
			user.setPassword("admin");
			tempDB.saveUser(user);
			Joining joining = new Joining();
			joining.StartConnection();
			}
		}
}
