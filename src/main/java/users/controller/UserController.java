package users.controller;


import users.dto.User;
import users.services.UserVerification;
import connection.dto.Response;
import email.dto.EmailInfo;
import email.service.EmailService;

public class UserController {

	public	Response login(User user){
		UserVerification uVerif = new UserVerification();
		Response response = new Response();
		response = uVerif.login(user);
		return response;
	}
	
	//TODO доделать методы
	public Response registration(User user){
		UserVerification uVerif = new UserVerification();
		String state = uVerif.registerUser(user);
		if (state.equals("allowed")){
			EmailService emailSrvc = new EmailService();
			EmailInfo email =new EmailInfo();
			email.setToEmail(user.getLogin());
			email.setState("registration");
			emailSrvc.prepareEmail(email, user, emailSrvc);
		}
		Response res = new Response();
		res.setStatusLogin(state);
		return res;
	}
	
	
	public Response resetPassword(String email){
		UserVerification uv = new UserVerification();
		final User userFromDB = uv.resetPassword(email, uv);
		Response res =  new Response();
		if (userFromDB == null){
			res.setStatusLogin("denied");
		} else {
			final EmailInfo eInfo =new EmailInfo();
			eInfo.setToEmail(userFromDB.getLogin());
			eInfo.setState("reset_password");
			
			//TODO сделали поток, что бы ответ шел сразу к серверу а не ждал отсылки почты
			new Thread(new Runnable() {
				@Override
				public void run() {
					EmailService emailSrvc =new EmailService();
					emailSrvc.prepareEmail(eInfo, userFromDB, emailSrvc);
				}}).start();
			res.setStatusLogin("allowed");
			
		}
		return res;
	}
	
}


