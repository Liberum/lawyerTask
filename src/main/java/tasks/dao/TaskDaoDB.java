package tasks.dao;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import tasks.dto.Task;
import users.dto.User;

import com.mysql.jdbc.PreparedStatement;

public class TaskDaoDB {
	

	public Map<Integer, Task> loadAllTasksForUser(User user){

        Connection conn = null;
		try {
			conn = DriverManager.getConnection(
			        "jdbc:mysql://localhost:3306/fjv",
			        "admin", "3015");
			if (conn == null) {
	            System.out.println("Нет соединения с БД!");
	            System.exit(0);
	        }
						
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement("SELECT * FROM tasks WHERE user_id = ?");
			 ps.setInt(1, user.getId());
			 ResultSet rs = ps.executeQuery();
			 Map <Integer, Task> allTasks = new HashMap<Integer, Task>();
		        while (rs.next()) {
		        	Task task = new Task();
		        	task.setTaskId(rs.getInt("id"));
		        	task.setUserId(rs.getInt("user_id"));
		        	task.setClaimAbout(rs.getString("claim_about"));
		        	task.setCaseTask(rs.getString("case_task"));
		        	task.setJudge(rs.getString("judge"));
		        	task.setDefendant(rs.getString("defendant"));
		        	task.setClaimant(rs.getString("claimant"));
		        	task.setGroupClaim(rs.getString("group_claim"));
		        	task.setCreatingData(rs.getTimestamp("creating_date").getTime());
		        	task.setNextReminder(rs.getTimestamp("next_reminder").getTime());
		        	task.setCurrentReminder(rs.getTimestamp("current_reminder").getTime());
		        	task.setDeadLine(rs.getTimestamp("dead_line").getTime());
		        	allTasks.put(task.getTaskId(), task);
		        }
			return allTasks;
				
	        
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
	
	
	
	
	public void saveTask(Task newTask){
		 Connection conn = null;
		try {
			conn = DriverManager.getConnection(
			        "jdbc:mysql://localhost:3306/fjv",
			        "admin", "3015");
	        if (conn == null) {
	            System.out.println("Нет соединения с БД!");
	            System.exit(0);
	        }
		
	        
		    PreparedStatement ps = (PreparedStatement) conn.prepareStatement("INSERT INTO tasks "
		    		+ "(user_id, claim_about, case_task, judge, defendant, claimant, group_claim, next_reminder, current_reminder, dead_line) "
		    		+ "values (?,?,?,?,?,?,?,?,?,?)");
        	ps.setInt(1, newTask.getUserId());
        	ps.setString(2, newTask.getClaimAbout());
        	ps.setString(3, newTask.getCaseTask());
        	ps.setString(4, newTask.getJudge());
        	ps.setString(5, newTask.getDefendant());
        	ps.setString(6, newTask.getClaimant());
        	ps.setString(7, newTask.getGroupClaim());
        	ps.setTimestamp(8, new Timestamp(newTask.getNextReminder()));
        	ps.setTimestamp(9,  new Timestamp(newTask.getCurrentReminder()));
        	ps.setTimestamp(10,  new Timestamp(newTask.getDeadLine()));
        	ps.executeUpdate();
        	System.out.println("Saved task: " + newTask.getClaimAbout());
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
	}	
	
	public void updateTask(Task existingT){
		
		 Connection conn = null;
		try {
			conn = DriverManager.getConnection(
			        "jdbc:mysql://localhost:3306/fjv",
			        "admin", "3015");
	        if (conn == null) {
	            System.out.println("Нет соединения с БД!");
	            System.exit(0);
	        }
		
	        
		    PreparedStatement ps = (PreparedStatement) conn.prepareStatement("UPDATE tasks SET "
		    		+ "user_id = ?, claim_about = ?, case_task = ?, judge = ?, defendant = ?, claimant = ?, group_claim = ?, "
		    		+ "next_reminder = ?, current_reminder = ?, dead_line = ? "
		    		+ "WHERE id = ?");
       	ps.setInt(1, existingT.getUserId());
       	ps.setString(2, existingT.getClaimAbout());
       	ps.setString(3, existingT.getCaseTask());
       	ps.setString(4, existingT.getJudge());
       	ps.setString(5, existingT.getDefendant());
       	ps.setString(6, existingT.getClaimant());
       	ps.setString(7, existingT.getGroupClaim());
       	ps.setTimestamp(8, new Timestamp(existingT.getNextReminder()));
       	ps.setTimestamp(9, new Timestamp(existingT.getCurrentReminder()));
       	ps.setTimestamp(10, new Timestamp(existingT.getDeadLine()));
       	ps.setInt(11, existingT.getTaskId());
       	ps.executeUpdate();
       	System.out.println("Update task: " + existingT.getClaimAbout());
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
	}
	
}

