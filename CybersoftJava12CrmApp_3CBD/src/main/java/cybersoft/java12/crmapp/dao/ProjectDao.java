package cybersoft.java12.crmapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import cybersoft.java12.crmapp.dbconnection.MySqlConnection;
import cybersoft.java12.crmapp.model.Project;
import cybersoft.java12.crmapp.model.User;

public class ProjectDao {
	
	public List<Project> findAll() throws SQLException {
		List<User> users = new LinkedList<>();
		List<Project> projects = new ArrayList<>();
		
		Connection connection = MySqlConnection.getConnection();
		String query = "select p.id as pid, p.name as pname, p.description as pdescription, p.start_date as pstart, p.end_date as pend, p.owner as powner, u.email as uemail, u.name as uname, u.address as uaddress, u.phone as uphone\r\n"
				+ "from project as p, user as u\r\n"
				+ "where p.owner = u.id";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				Project pro = new Project();
				
				pro.setId(resultSet.getInt("pid"));
				pro.setName(resultSet.getString("pname"));
				pro.setDescription(resultSet.getString("pdescription"));
				pro.setStart_date(resultSet.getString("pstart"));
				pro.setEnd_date(resultSet.getString("pend"));				
				
				int owner_id = resultSet.getInt("powner");
				User user = getUserFromList(users, owner_id);
				
				if(user == null) {
					user = new User();
					user.setId(resultSet.getInt("powner"));
					user.setEmail(resultSet.getString("uemail"));
					user.setName(resultSet.getString("uname"));
					user.setAddress(resultSet.getString("uaddress"));
					user.setPhone(resultSet.getString("uphone"));
				
					users.add(user);
				}
				
				pro.setOwner(user);

				projects.add(pro);
			}
			
		} catch (SQLException e) {
			System.out.println("findAll. Unable to connect to database.");
			e.printStackTrace();
		} finally {
			connection.close();
		}
		
		return projects;
	}
	
	
	private User getUserFromList(List<User> users, int userid) {
		for(User user : users)
			if(user.getId() == userid)
				return user;
		return null;
	}

	public Project findProjectById(int code) {
		List<User> users = new LinkedList<>();
		Project project = null;		
		Connection conn = MySqlConnection.getConnection();
		String query = "select p.id as id, p.name as name, p.description as description, p.start_date as start_date, p.end_date as end_date, p.owner as owner, \r\n"
				+ "		u.name as uname, u.email as uemail, u.address as uaddress, u.phone as uphone\r\n"
				+ "from project p, user u \r\n"
				+ "where p.owner = u.id and p.id = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, code);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				project = new Project();
				project.setId(rs.getInt("id"));
				project.setName(rs.getString("name"));
				project.setDescription(rs.getString("description"));
				project.setStart_date(rs.getString("start_date"));
				project.setEnd_date(rs.getString("end_date"));


				int owner_id = rs.getInt("owner");
				User user = getUserFromList(users, owner_id);
				
				if(user == null) {
					user = new User();
					user.setId(rs.getInt("owner"));
					user.setEmail(rs.getString("uemail"));
					user.setName(rs.getString("uname"));
					user.setAddress(rs.getString("uaddress"));
					user.setPhone(rs.getString("uphone"));
				
					users.add(user);
				}
				
				project.setOwner(user);		
			}
			
		} catch (Exception e) {
			System.out.println("findProjectById. Disconnected !");
			e.printStackTrace();
		}
		return project;
	}

	public int deleteProject(int code) {
		int result = -1;
		Connection conn = MySqlConnection.getConnection();
		String query = "DELETE FROM project where id = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, code);
			result = ps.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("deleteProject. Disconnected !");
			e.printStackTrace();
		}
		
		return result;
	}


	public int update(Project project) {
		int result = -1;
		int index = -1;
		Connection conn = MySqlConnection.getConnection();
		String query = "UPDATE project SET name = ?, description = ?, start_date = ?, end_date = ?, owner = ? WHERE id = ?";
		
		if(project.getStart_date().isEmpty() || project.getStart_date().isBlank()) {
			if(project.getEnd_date().isEmpty() || project.getEnd_date().isBlank()) {
				query = "UPDATE project SET name = ?, description = ?, owner = ? WHERE id = ?";
				index = 1;
			}else {
				query = "UPDATE project SET name = ?, description = ?, end_date = ?, owner = ? WHERE id = ?";
				index = 2;
			}
		}else {
			if(!project.getEnd_date().isEmpty() || !project.getEnd_date().isBlank()) {
				query = "UPDATE project SET name = ?, description = ?, start_date = ?, end_date = ?, owner = ? WHERE id = ?";
				index = 3;
			}else {
				query = "UPDATE project SET name = ?, description = ?, start_date = ?, owner = ? WHERE id = ?";
				index = 4;
			}
			
		}
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, project.getName());
			ps.setString(2, project.getDescription());			
			switch (index) {
			case 1:
				ps.setInt(3, project.getOwner().getId());
				ps.setInt(4, project.getId());
				break;
			case 2:;
				ps.setString(3, project.getEnd_date());
				ps.setInt(4, project.getOwner().getId());
				ps.setInt(5, project.getId());
				break;
			case 3:
				ps.setString(3, project.getStart_date());
				ps.setString(4, project.getEnd_date());
				ps.setInt(5, project.getOwner().getId());
				ps.setInt(6, project.getId());
				break;
			case 4:
				ps.setString(3, project.getStart_date());
				ps.setInt(4, project.getOwner().getId());
				ps.setInt(5, project.getId());
				break;
			default:
				break;
			}
			
			result = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("update. Disconnected !");
			e.printStackTrace();
		}
		
		return result;
	}
	
	
}
