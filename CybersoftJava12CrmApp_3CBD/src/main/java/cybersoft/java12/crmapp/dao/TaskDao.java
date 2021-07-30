package cybersoft.java12.crmapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import cybersoft.java12.crmapp.dbconnection.MySqlConnection;
import cybersoft.java12.crmapp.model.Project;
import cybersoft.java12.crmapp.model.Status;
import cybersoft.java12.crmapp.model.Task;
import cybersoft.java12.crmapp.model.User;

public class TaskDao {

	public List<Task> findAll() {
		List<Task> tasks = new LinkedList<Task>();
		Connection conn = MySqlConnection.getConnection();
		String query = "SELECT t.id as id, t.name as tname, t.description as description, t.start_date as start_date, t.end_date as end_date, t.project as project, t.user as user, t.status as status, s.name as sname\r\n"
				+ "FROM task AS t, status as s\r\n"
				+ "WHERE t.status = s.id";

		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Task task = new Task();
				task.setId(rs.getInt("id"));
				task.setName(rs.getString("tname"));
				task.setDescription(rs.getString("description"));
				task.setStart_date(rs.getString("start_date"));
				task.setEnd_date(rs.getString("end_date"));
				Project p = new Project();
				p.setId(rs.getInt("project"));
				task.setProject(p);
				User u = new User();
				u.setId(rs.getInt("user"));
				task.setUser(u);
				Status s = new Status();
				s.setId(rs.getInt("status"));
				s.setName(rs.getString("sname"));
				task.setStatus(s);
				
				tasks.add(task);	
			}
		} catch (Exception e) {
			System.out.println("findAll. Disconnected !");
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return tasks;
	}

	public int delete(int id) {
		int result = -1;
		Connection conn = MySqlConnection.getConnection();
		String query = "DELETE FROM task where id = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			result = ps.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("delete. Disconnected !");
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return result;
	}

	public int add(Task task) {
		int result = -1;
		Connection conn = MySqlConnection.getConnection();
		String query = "INSERT INTO task (name, description, start_date, end_date, project, user, status)\r\n"
				+ "VALUE (?, ?, ?, ?, ?, ?, ?)";

		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, task.getName());
			ps.setString(2, task.getDescription());
			ps.setString(3, task.getStart_date());
			ps.setString(4, task.getEnd_date());
			ps.setInt(5, task.getProject().getId());
			ps.setInt(6, task.getUser().getId());
			ps.setInt(7, task.getStatus().getId());
			
			result = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("add. Disconnected !");
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	public int update(Task task, int idUpdate) {
		int result = -1;
		Connection conn = MySqlConnection.getConnection();
		String query = "UPDATE task SET name = ?, description = ?, start_date = ?, end_date = ?, project = ?, user = ?, status = ? WHERE id = ?";

		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, task.getName());
			ps.setString(2, task.getDescription());
			ps.setString(3, task.getStart_date());
			ps.setString(4, task.getEnd_date());
			ps.setInt(5, task.getProject().getId());
			ps.setInt(6, task.getUser().getId());
			ps.setInt(7, task.getStatus().getId());
			ps.setInt(8, idUpdate);
			
			result = ps.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("update. Disconnected !");
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	public Task findTaskById(int idByUpdate) {
		Task task = null;
		Connection conn = MySqlConnection.getConnection();
		String query = "SELECT t.id as id, t.name as tname, t.description as description, t.start_date as start_date, t.end_date as end_date, t.project as project, t.user as user, t.status as status, s.name as sname\r\n"
				+ "FROM task AS t, status as s\r\n"
				+ "WHERE t.status = s.id and t.id = ?";

		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, idByUpdate);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				task = new Task();
				task.setId(rs.getInt("id")); 
				task.setName(rs.getString("tname"));
				task.setDescription(rs.getString("description"));
				task.setStart_date(rs.getString("start_date"));
				task.setEnd_date(rs.getString("end_date"));
				Project p = new Project();
				p.setId(rs.getInt("project"));
				task.setProject(p);
				User u = new User();
				u.setId(rs.getInt("user"));
				task.setUser(u);
				Status s = new Status();
				s.setId(rs.getInt("status"));
				s.setName(rs.getString("sname"));
				task.setStatus(s);
			}
				
		} catch (Exception e) {
			System.out.println("findTaskById. Disconnected !");
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return task;
	}

}
