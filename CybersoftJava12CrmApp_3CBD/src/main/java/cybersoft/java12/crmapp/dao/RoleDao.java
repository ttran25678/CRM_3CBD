package cybersoft.java12.crmapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import cybersoft.java12.crmapp.dbconnection.MySqlConnection;
import cybersoft.java12.crmapp.model.Role;

public class RoleDao {

    public List<Role> findAll() throws SQLException {
        List<Role> roles = new LinkedList<>();
        
        Connection connection = MySqlConnection.getConnection();
        String query = "SELECT id, name, description FROM role";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                Role role = new Role();
                
                role.setId(resultSet.getInt("id"));
                role.setName(resultSet.getString("name"));
                role.setDescription(resultSet.getString("description"));
                
                roles.add(role);
                }                        
        } catch (SQLException e) {
            System.out.println("Unable to connect to database.");
            e.printStackTrace();
        } finally {
            connection.close();
        }    
        return roles;
    }    
    
    public void deleteById(int id) throws SQLException {
        String query = "DELETE FROM role WHERE id = ?";
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

    public void add(Role role) throws SQLException {
        String query = "INSERT INTO role (name, description)"
                + "VALUES(?,?)";    
        Connection connection = MySqlConnection.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setNString(1, role.getName());
            statement.setNString(2, role.getDescription());
            statement.executeUpdate();
            
        }catch (SQLException e) {
            System.out.println("Unable to connect to database.");
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }

	public Role findRoleById(int id){
		Role role = new Role();
		Connection connection = MySqlConnection.getConnection();
        String query = "SELECT id, name, description FROM role WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
            	
            	role.setId(resultSet.getInt("id"));
                role.setName(resultSet.getString("name"));
                role.setDescription(resultSet.getString("description"));
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
        return role;
	}

	public int update(Role role, int id) throws SQLException {
		int result = -1;
		Connection conn = MySqlConnection.getConnection();
		String query = "UPDATE role SET name = ?, description = ? WHERE id = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, role.getName());
			ps.setString(2, role.getDescription());
			ps.setInt(1, id);
			result = ps.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("update. Disconnected !");
			e.printStackTrace();
		}finally {
			conn.close();
		}
		
		return result;
	}
}