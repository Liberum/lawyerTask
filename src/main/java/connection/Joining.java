package connection;

import java.io.IOException;
import java.net.ServerSocket;



public class Joining{
	private int port = 2233;
	
	public void StartConnection() throws ClassNotFoundException {
		ServerSocket srvSocket = null;
		try {
			srvSocket = new ServerSocket(port);
		} catch (IOException e) {
			System.out.println("Извините пожалуйста, у меня не получилось создать серверный сокет на порту " + port + " :(( ");
			e.printStackTrace();
		}
		
		try {
			while (true){
			Request req = new Request(srvSocket.accept());
			req.start();
		}
			
						
		} catch (IOException e) {
			System.out.println("Не удалось соединиться с клиентом");
			e.printStackTrace();
			
		}
		

	}

}
