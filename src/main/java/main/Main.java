package main;
import users.services.UserVerification;
import connection.Joining;


public class Main {
	
	public static void main(String[] args) throws ClassNotFoundException {
		System.out.println("Начинаем");
		
		UserVerification verif = new UserVerification();
		verif.initUser();
		
		while (true) {
			Joining joining = new Joining();
			joining.StartConnection();
			}
		}
}
