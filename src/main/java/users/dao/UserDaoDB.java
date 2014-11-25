package users.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import users.dto.User;

import com.mysql.jdbc.PreparedStatement;



public class UserDaoDB {
	//TODO SQL
	
	public void saveUser (User user)  {
		
        Connection conn = null;
        User existingUser = loadUserByEmail(user.getLogin());
		try {
			conn = DriverManager.getConnection(
			        "jdbc:mysql://localhost:3306/fjv",
			        "admin", "3015");
	        if (conn == null) {
	            System.out.println("Нет соединения с БД!");
	            System.exit(0);
	        }
	        
	        if (existingUser != null) { 
	        PreparedStatement ps = (PreparedStatement) conn.prepareStatement("UPDATE users SET pass = ? WHERE email = ?");
	        ps.setString(1, user.getPassword());
	        ps.setString(2, user.getLogin());
	        ps.executeUpdate();
	        System.out.println("Updated user " + user.getLogin() + " pass " + user.getPassword()); 
	        conn.close();
	        } else {
		    PreparedStatement ps = (PreparedStatement) conn.prepareStatement("INSERT INTO users (email, pass) values (?,?)");
		    ps.setString(1, user.getLogin());
		    ps.setString(2, user.getPassword());
		    ps.executeUpdate();
		    System.out.println("Saved user " + user.getLogin() + " pass " + user.getPassword());
		    conn.close();
	        }
	        
		} catch (SQLException e) {
			System.out.println("Ошибка базы данных " + e);
			e.printStackTrace();
		}finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	

	public User loadUserByEmail(String login) {
		
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(
			        "jdbc:mysql://localhost:3306/fjv",
			        "admin", "3015");
	        if (conn == null) {
	            System.out.println("Нет соединения с БД!");
	            System.exit(0);
	        }
	        
	        PreparedStatement ps = (PreparedStatement) conn.prepareStatement("SELECT id, email, pass FROM users WHERE email = ?");
	        ps.setString(1, login);
	        ResultSet rs = ps.executeQuery();
	        while (rs.next()) {
	        	User user = new User();
	        	user.setLogin(rs.getString("email"));
	        	user.setPassword(rs.getString("pass"));
	        	System.out.println("Загружен пользователь:" + user);
	        	return user;
	        }
			
	        conn.close();
		} catch (SQLException e) {
			System.out.println("Ошибка базы данных " + e);
			e.printStackTrace();
		}finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return null;
	}


public List<User> loadAllUsers() {
	List<User> res = new ArrayList<>();
	Connection conn = null;
	try {
		conn = DriverManager.getConnection(
		        "jdbc:mysql://localhost:3306/fjv",
		        "admin", "3015");
		
		PreparedStatement ps = (PreparedStatement) conn.prepareStatement("select * from users");
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			User user = new User();
			user.setLogin(rs.getString("email"));
			user.setPassword(rs.getString("name"));
			res.add(user);
		}
		System.out.println("All users were loaded");
		return res;
	} catch (SQLException e) {
		throw new RuntimeException(e);
	} finally {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}

}

