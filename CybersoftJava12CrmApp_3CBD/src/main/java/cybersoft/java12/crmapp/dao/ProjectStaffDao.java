package cybersoft.java12.crmapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import cybersoft.java12.crmapp.dbconnection.MySqlConnection;
import cybersoft.java12.crmapp.model.Project;
import cybersoft.java12.crmapp.model.ProjectUser;
import cybersoft.java12.crmapp.model.User;

public class ProjectStaffDao {

	public List<ProjectUser> findAll() throws SQLException {
		List<ProjectUser> projectUsers = new LinkedList<ProjectUser>();
		
		Connection connection = MySqlConnection.getConnection();
		String query = "select u.id as uid, u.name as uname, x.join_date as xjoin, x.role_description as xdescription, p.id as pid, p.name as pname, p.description as pdescription, p.start_date as pstart, p.end_date as pend\r\n"
				+ "	from project_user as x, user as u, project as p\r\n"
				+ "	where x.projectid = p.id and x.userid = u.id\r\n"
				+ "	order by u.id";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				ProjectUser pUser = new ProjectUser();
				
				User user = new User();
				user.setId(resultSet.getInt("uid"));
				user.setName(resultSet.getString("uname"));
				
				pUser.setUser(user);
				
				pUser.setJoin_date(resultSet.getString("xjoin"));
				pUser.setJoin_description(resultSet.getString("xdescription"));
				
				Project pro = new Project();
				pro.setId(resultSet.getInt("pid"));
				pro.setName(resultSet.getString("pname"));
				pro.setDescription(resultSet.getString("pdescription"));
				pro.setStart_date(resultSet.getString("pstart"));
				pro.setEnd_date(resultSet.getString("pend"));				

				pUser.setProject(pro);
				
				projectUsers.add(pUser);
			}
	
		} catch (SQLException e) {
			System.out.println("findAll. Unable to connect to database.");
			e.printStackTrace();
		} finally {
			connection.close();
		}
		
		return projectUsers;
	}

	public int addStaff(ProjectUser pUser) {
		int result = -1;
		Connection connection = MySqlConnection.getConnection();
		String query = "INSERT INTO project_user (projectid, userid, join_date, role_description)\r\n"
				+ "VALUE (?, ?, ?, ?)";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, pUser.getProject().getId());
			ps.setInt(2, pUser.getUser().getId());
			ps.setString(3, pUser.getJoin_date());
			ps.setString(4, pUser.getJoin_description());
			
			result = ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println("findAll. Unable to connect to database.");
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	public int deleteStaff(int uid, int pid) {
		int result = -1;
		Connection connection = MySqlConnection.getConnection();
		String query = "DELETE FROM project_user where projectid = ? and userid = ?";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, pid);
			ps.setInt(2, uid);
			
			result = ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println("DeleteStaff. Unable to connect to database.");
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	public ProjectUser findStaff(int uid, int pid) {
		ProjectUser pUser = new ProjectUser();
		Connection connection = MySqlConnection.getConnection();
		String query = "SELECT projectid, userid, join_date, role_description FROM project_user WHERE projectid = ? and userid = ?";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, pid);
			ps.setInt(2, uid);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Project p = new Project();
				p.setId(rs.getInt("projectid"));
				pUser.setProject(p);
				User u = new User();
				u.setId(rs.getInt("userid"));
				pUser.setUser(u);
				pUser.setJoin_date(rs.getString("join_date"));
				pUser.setJoin_description(rs.getString("role_description"));
			}
		} catch (SQLException e) {
			System.out.println("DeleteStaff. Unable to connect to database.");
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return pUser;
	}

	public int updateStaff(ProjectUser pUser, int pid, int uid) {
		int result = -1;
		String query = "UPDATE project_user SET join_date = ?, role_description = ? WHERE projectid = ? and userid = ?";
		Connection conn = MySqlConnection.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, pUser.getJoin_date());
			ps.setString(2, pUser.getJoin_description());
			ps.setInt(3, pid);
			ps.setInt(4, uid);
			
			result = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("updateStaff. Unable to connect to database.");
			e.printStackTrace();
		}
		return result;
	}

}
