package users.services;

import main.Main;
import users.dao.DBinmemory;
import users.dto.User;
public class UserVerification {
	
	DBinmemory db;
	

	public void setDb(DBinmemory db) {
		this.db = db;
	}

	public String access(User unloggedUser){
	if (Main.tempDB.exist(unloggedUser)){
		return "allowed";
		}
	return "denied";
	}
	
	
	
	//TODO login , logout, registration
	
	public static void saveUser (User user){
		Main.tempDB.saveUser(user);
	}
	
	public void login (){

	}
	
	public User loadUserByEmail(String email){
		User user = new User();
		return user;
	}
	
	public void registerUser(){
		
	}
	public void resetPassword(){
		
	}
	
}
