package users.controller;


import users.dto.User;
import users.services.UserVerification;
import connection.dto.Response;

public class UserController {

	
	

	public	Response login(User user){
	UserVerification uVerif = new UserVerification();
	String state = uVerif.login(user);
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


