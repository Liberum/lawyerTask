package users.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import users.dto.User;

public class DBinmemory {
	private Map<String, User> tempDB = new HashMap<String, User>();
		
	public void saveUser(User user){
		tempDB.put(user.getLogin(), user);
		System.out.println(user + "Добавлен в базу данных");
	}
	public User loadUserByEmail (String email){
		return tempDB.get(email);
	}
	
	
	
	public boolean exist(User user){
		System.out.println(tempDB.toString());
		if (tempDB.containsKey(user.getLogin())) {
			return true;			
		}
		return false;
	}
	public List <User> loadAllUsers(){
		return new ArrayList<>(tempDB.values());
	}
	
	public Map<String , User> loadMap(){
		return tempDB;
	}
	
	public String toString(){
		
		List <User> allUsers = loadAllUsers();
		StringBuffer sb = new StringBuffer();
		sb.append("BD: \n");
		for (User u: allUsers){
			sb = sb.append(u.getLogin() + ": ");
			sb = sb.append(u.getPassword() + "; \n");
		}
		return sb.toString();
	}
}
