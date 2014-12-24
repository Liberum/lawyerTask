package users.services;

import java.util.List;

import users.dao.UserDaoDB;
import users.dto.User;
import connection.dto.Response;
public class UserVerification {
	//TODO login , logout, registration
	
	public void saveUser (User user){
		UserDaoDB db = new UserDaoDB();
		db.saveUser(user);
	}
	
	public Response login (User user){
		UserVerification uv = new UserVerification();
		Response res = new Response();
		User userFromBD = uv.loadUserByEmail(user.getLogin());
		if (userFromBD != null && user.getPassword().equals(userFromBD.getPassword())) {
			res = new Response();
			res.setUser(userFromBD);
			res.setStatusLogin("allowed");
			return res;	
		}
	
		res.setStatusLogin("denied");
		return res;
		
	}
	
	public User loadUserByEmail(String email){
		UserDaoDB db = new UserDaoDB();
		return db.loadUserByEmail(email);
		
	}
	
	public String registerUser(User user){
		//TODO проверять на пусты строки , полную проверку, на цифры буквы , англ и русс и все что Саня захочет
		
		UserVerification uv = new UserVerification();
		User userFromBD = uv.loadUserByEmail(user.getLogin());
		if (userFromBD != null){
			return "denied";
		}
		uv.saveUser(user);
		return "allowed";
		
		//TODO добавить в юзера id которое он получил в БД это можно сднлать и тут 

	}
	public User resetPassword(String email, UserVerification uv){
		User userFromDB = uv.loadUserByEmail(email);
		if (userFromDB != null){
			return userFromDB;
		}
		return null;
	}
	
	public void initUser (){
		UserVerification uv = new UserVerification();
		if (uv.loadUserByEmail("admin@admin.net") == null) {
			User user = new User();
			user.setLogin("admin@admin.net");
			user.setPassword("admin");
			user.setName("Sashko");
			uv.saveUser(user);
		}
		UserDaoDB db = new UserDaoDB();
		List<User> allUser = db.loadAllUsers();
		System.out.println("\n");
		for (User user : allUser){
			System.out.println(user);
		}
	}
	
}
