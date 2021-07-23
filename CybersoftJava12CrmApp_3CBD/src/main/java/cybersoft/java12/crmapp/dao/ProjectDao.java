package cybersoft.java12.crmapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cybersoft.java12.crmapp.dbconnection.MySqlConnection;
import cybersoft.java12.crmapp.model.Project;
import cybersoft.java12.crmapp.model.User;

public class ProjectDao {
	
	public List<Project> findAll() throws SQLException {
		List<Project> projects = new ArrayList<>();
		
		Connection connection = MySqlConnection.getConnection();
		String query = "select p.id as pid, p.name as pname, p.description as pdescription, p.start_date as pstart, p.end_date as pend, p.owner as powner\r\n"
				+ "from project as p \r\n"
				+ "order by p.id";
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

				User user = new User();
				user.setId(resultSet.getInt("powner"));
				
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
	
	public Project findProjectById(int code) {
		Project project = null;		
		Connection conn = MySqlConnection.getConnection();
		String query = "select id, name, description, start_date, end_date, owner from project where id = ?;";
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
				
				User user = new User();
				user.setId(rs.getInt("owner"));

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
		String query = "";
		
		if(project.getStart_date().isEmpty()) {
			if(project.getEnd_date().isEmpty()) {
				if(project.getOwner().getId() == -1) {
					query = "UPDATE project SET name = ?, description = ? WHERE id = ?";
					index = 1;
				}else {
					query = "UPDATE project SET name = ?, description = ?, owner = ? WHERE id = ?";
					index = 2;
				}
			}else {
				if(project.getOwner().getId() == -1) {
					query = "UPDATE project SET name = ?, description = ?, end_date = ? WHERE id = ?";
					index = 3;
				}else {
					query = "UPDATE project SET name = ?, description = ?, end_date = ?, owner = ? WHERE id = ?";
					index = 4;
				}
			}
		}else {
			if(project.getEnd_date().isEmpty()) {
				if(project.getOwner().getId() == -1) {
					query = "UPDATE project SET name = ?, description = ?, start_date = ? WHERE id = ?";
					index = 5;
				}else {
					query = "UPDATE project SET name = ?, description = ?, start_date = ?, owner = ? WHERE id = ?";
					index = 6;
				}
			}else {
				if(project.getOwner().getId() == -1) {
					query = "UPDATE project SET name = ?, description = ?, start_date = ?, end_date = ? WHERE id = ?";
					index = 7;
				}else {
					query = "UPDATE project SET name = ?, description = ?, start_date = ?, end_date = ?, owner = ? WHERE id = ?";
					index = 8;
				}
			}
		}

		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, project.getName());
			ps.setString(2, project.getDescription());			
			switch (index) {
			case 1:
				ps.setInt(3, project.getId());
				break;
			case 2:
				ps.setInt(3, project.getOwner().getId());
				ps.setInt(4, project.getId());
				break;
			case 3:
				ps.setString(3, project.getEnd_date());
				ps.setInt(4, project.getId());
				break;
			case 4:
				ps.setString(3, project.getEnd_date());
				ps.setInt(4, project.getOwner().getId());
				ps.setInt(5, project.getId());
				break;
			case 5:
				ps.setString(3, project.getStart_date());
				ps.setInt(4, project.getId());
				break;
			case 6:
				ps.setString(3, project.getStart_date());
				ps.setInt(4, project.getOwner().getId());
				ps.setInt(5, project.getId());
				break;
			case 7:
				ps.setString(3, project.getStart_date());
				ps.setString(4, project.getEnd_date());
				ps.setInt(5, project.getId());
				break;
			case 8:
				ps.setString(3, project.getStart_date());
				ps.setString(4, project.getEnd_date());
				ps.setInt(5, project.getOwner().getId());
				ps.setInt(6, project.getId());
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


	public int addNewProject(Project project) {
		int result = -1;
		String query = "";
		int index = -1;
		if(project.getStart_date().isEmpty()) {
			if(project.getEnd_date().isEmpty()) {
				if(project.getOwner().getId() == -1) {
					query = "INSERT INTO project (name, description)\r\n"
							+ "VALUE (?, ?)";
					index = 1;
				}else {
					query = "INSERT INTO project (name, description, owner)\r\n"
							+ "VALUE (?, ?, ?)";
					index = 2;
				}
			}else {
				if(project.getOwner().getId() == -1) {
					query = "INSERT INTO project (name, description, end_date)\r\n"
							+ "VALUE (?, ?, ?)";
					index = 3;
				}else {
					query = "INSERT INTO project (name, description, end_date, owner)\r\n"
							+ "VALUE (?, ?, ?, ?)";
					index = 4;
				}
			}
		}else {
			if(project.getEnd_date().isEmpty()) {
				if(project.getOwner().getId() == -1) {
					query = "INSERT INTO project (name, description, start_date)\r\n"
							+ "VALUE (?, ?, ?)";
					index = 5;
				}else {
					query = "INSERT INTO project (name, description, start_date, owner)\r\n"
							+ "VALUE (?, ?, ?, ?)";
					index = 6;
				}
			}else {
				if(project.getOwner().getId() == -1) {
					query = "INSERT INTO project (name, description, start_date, end_date)\r\n"
							+ "VALUE (?, ?, ?, ?)";
					index = 7;
				}else {
					query = "INSERT INTO project (name, description, start_date, end_date, owner)\r\n"
							+ "VALUE (?, ?, ?, ?, ?)";
					index = 8;
				}
			}
		}
		
		Connection conn = MySqlConnection.getConnection();

		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, project.getName());
			ps.setString(2, project.getDescription());
			
			switch (index) {
			case 1:
				// next
				break;
			case 2:
				ps.setInt(3, project.getOwner().getId());
				break;
			case 3:
				ps.setString(3, project.getEnd_date());
				break;
			case 4:
				ps.setString(3, project.getEnd_date());
				ps.setInt(4, project.getOwner().getId());
				break;
			case 5:
				ps.setString(3, project.getStart_date());
				break;
			case 6:
				ps.setString(3, project.getStart_date());
				ps.setInt(4, project.getOwner().getId());
				break;
			case 7:
				ps.setString(3, project.getStart_date());
				ps.setString(4, project.getEnd_date());
				break;
			case 8:
				ps.setString(3, project.getStart_date());
				ps.setString(4, project.getEnd_date());
				ps.setInt(5, project.getOwner().getId());
				break;
			default:
				break;
			}
			
			result = ps.executeUpdate();	
			
		} catch (Exception e) {
			System.out.println("Add New Project. Disconnected !");
			e.printStackTrace();
		}
		
		return result;
	}
	
	
}
