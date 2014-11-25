package users.services;

import users.dao.UserDaoDB;
import users.dto.User;
public class UserVerification {
	//TODO login , logout, registration
	
	public void saveUser (User user){
		UserDaoDB db = new UserDaoDB();
		db.saveUser(user);
	}
	
	public String login (User user){
		UserDaoDB db = new UserDaoDB();
		User userFromBD = db.loadUserByEmail(user.getLogin());
		if (userFromBD != null && user.getPassword().equals(userFromBD.getPassword())) {
			return "allowed";	
		}
		return "denied";
		
	}
	
	public User loadUserByEmail(String email){
		User user = new User();
		return user;
	}
	
	public void registerUser(){
		
	}
	public void resetPassword(){
		
	}
	public void initUser (){
		UserDaoDB db = new UserDaoDB();
		if (db.loadUserByEmail("admin@admin.net") == null) {
			User user = new User();
			user.setLogin("admin@admin.net");
			user.setPassword("admin");
			db.saveUser(user);
		}
	}
	
}
