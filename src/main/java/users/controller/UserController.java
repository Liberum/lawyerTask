package users.controller;


import connection.dto.Response;
import users.dao.DBinmemory;
import users.dto.User;
import users.services.UserVerification;

public class UserController {

	
	

	public	Response login(User user){
	UserVerification uVerif = new UserVerification();
	String state = uVerif.access(user);
	Response response = new Response();
	response.setStatusLogin(state);
	return response;
	}
	
	
	public void testLogin(User user){
		
	}
	
	
	
	
	//TODO доделать методы
	public Response registration(){
		Response res =  new Response();
		return res;
	}
	public Response resetPassword(){
		Response res =  new Response();
		return res;
	}
	
}


