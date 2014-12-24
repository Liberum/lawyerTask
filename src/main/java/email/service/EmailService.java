package email.service;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import users.dto.User;
import email.dto.EmailInfo;


public class EmailService {
	
	public void prepareEmail(EmailInfo email, User user, EmailService service){
		email.setFromEmail("lawyertask@gmail.com"); // psw = itrtkm1!
		
		switch (email.getState()) {
		case "registration":
			//testing...
			email.setSubject("Регистрация на FJV");
			email.setText("Спасибо за регистрацию, " + user.getName() +"\n Login: " + user.getLogin() + "\n Password: " + user.getPassword());
			service.sendEmail(email);
			
			break;
			
			
			//TODO когда нить доделать при желании
		case "reset_password":
			email.setSubject("Восстановление пароля от FJV");
			email.setText(user.getName()+ ", Ваши регистрационные данные:" +"\n Login: " + user.getLogin() + "\n Password: " + user.getPassword());
			service.sendEmail(email);
			break;
			
		case "send_task":
			email.setSubject("тестовая тема письма");
			email.setText("Запланированная задача \n" + "текст задачи");
			break;
	
		default:
			//TODO Exption
			break;
		}
	
	}
	

	public void sendEmail(final EmailInfo email){
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        
		System.out.println("\n" + email.getText() + " " + email.getSubject() + " " + email.getToEmail() + " " + email.getFromEmail());
		Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email.getFromEmail(), "itrtkm1!");
            }
        });
		try {
            Message message = new MimeMessage(session);
            //от кого
            message.setFrom(new InternetAddress(email.getFromEmail()));
            //кому
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email.getToEmail()));
            //Заголовок письма
            message.setSubject(email.getSubject());
            //Содержимое
            message.setText(email.getText());
 
            //Отправляем сообщение
            Transport.send(message);
            System.out.println("отправил и-мэйл");
//            System.out.println(message.getSubject() + " " + email.getText() + " " + email.getSubject() + " " + email.getToEmail() + " " + email.getFromEmail());
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
		
		
	}
}
