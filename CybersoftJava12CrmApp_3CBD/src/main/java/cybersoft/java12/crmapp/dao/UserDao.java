package cybersoft.java12.crmapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import cybersoft.java12.crmapp.dbconnection.MySqlConnection;
import cybersoft.java12.crmapp.dto.UserCreateDto;
import cybersoft.java12.crmapp.model.Role;
import cybersoft.java12.crmapp.model.User;

public class UserDao {

	public List<User> findAll() throws SQLException {
		List<User> users = new LinkedList<>();
		List<Role> roles = new ArrayList<>();
		
		Connection connection = MySqlConnection.getConnection();
		String query = "SELECT u.id as id, u.name as name, u.email as email, u.address as address, u.phone as phone, \r\n"
				+ "r.id as role_id, r.name as role_name, r.description as role_description \r\n"
				+ "FROM user u, role r WHERE u.role_id = r.id ORDER BY u.id;";
		  
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				User user = new User();
				
				user.setId(resultSet.getInt("id"));
				user.setName(resultSet.getString("name"));
				user.setEmail(resultSet.getString("email"));
				user.setAddress(resultSet.getString("address"));
				user.setPhone(resultSet.getString("phone"));
				
				int roleId = resultSet.getInt("role_id");
				Role role = getRoleFromList(roles, roleId);
				
				if(role == null) {
					role = new Role();
					role.setId(resultSet.getInt("role_id"));
					role.setName(resultSet.getString("role_name"));
					role.setDescription(resultSet.getString("role_description"));
					
					roles.add(role);
				}
				
				user.setRole(role);
				
				users.add(user);
			}
			
		} catch (SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		} finally {
			connection.close();
		}
		
		return users;
	}

	public void deleteById(int id) throws SQLException {
		String query = "DELETE FROM user WHERE id = ?";
		Connection connection = MySqlConnection.getConnection();
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			
			statement.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		} finally {
			connection.close();
		}
		
	}
	
	private Role getRoleFromList(List<Role> roles, int roleId) {
		for(Role role : roles)
			if(role.getId() == roleId)
				return role;
		return null;
	}

	public void add(UserCreateDto dto) throws SQLException {
		String query = "INSERT INTO user(email, password, name, address, phone, role_id)"
				+ "VALUES(?,?,?,?,?,?)";
		
		Connection connection = MySqlConnection.getConnection();
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setNString(1, dto.getEmail());
			statement.setNString(2, dto.getPassword());
			statement.setNString(3, dto.getName());
			statement.setNString(4, dto.getAddress());
			statement.setNString(5, dto.getPhone());
			statement.setInt(6, dto.getRoleId());
			
			statement.executeUpdate();
			
		}catch (SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		} finally {
			connection.close();
		}
	}

	public User findUserById(int id) {
		User user = null;
		Connection connection = MySqlConnection.getConnection();
        String query = "SELECT u.id as uid, u.email as email, u.name as uname, u.address as address, u.phone as phone, r.id as rid, r.name as rname \r\n"
        		+ "FROM user as u, role as r\r\n"
        		+ "WHERE u.role_id = r.id and u.id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            
            ResultSet resultSet = statement.executeQuery();
          	while(resultSet.next()) {
          		user = new User();
          		user.setId(resultSet.getInt("uid"));
            	user.setEmail(resultSet.getString("email"));
            	user.setName(resultSet.getString("uname"));
            	user.setAddress(resultSet.getString("address"));
            	user.setPhone(resultSet.getString("phone"));
            	user.setEmail(resultSet.getString("email"));
            	
            	Role role = new Role();
            	role.setId(resultSet.getInt("rid"));
            	role.setName(resultSet.getString("rname"));
            	user.setRole(role);
          	}
            	
                                
        } catch (SQLException e) {
            System.out.println("Unable to connect to database.");
            e.printStackTrace();
        } finally {
        	try {
				connection.close();
			} catch (SQLException e) {
				System.out.println("Unable to connect to database.");
				e.printStackTrace();
			}
        }    
        return user;
	}

	public int update(User user, int id) {
		int result = -1;
		Connection conn = MySqlConnection.getConnection();
		String query = "UPDATE user SET email = ?, name = ?, address = ?, phone = ?, role_id = ? WHERE id = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, user.getEmail());
			ps.setString(2, user.getName());
			ps.setString(3, user.getAddress());
			ps.setString(4, user.getPhone());
			ps.setInt(5, user.getRole().getId());
			ps.setInt(6, id);
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

}
