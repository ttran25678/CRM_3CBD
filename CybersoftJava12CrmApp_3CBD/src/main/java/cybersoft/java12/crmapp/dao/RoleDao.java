package cybersoft.java12.crmapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import cybersoft.java12.crmapp.dbconnection.MySqlConnection;
import cybersoft.java12.crmapp.dto.RoleCreateDto;
import cybersoft.java12.crmapp.model.Role;

public class RoleDao {

    public List<Role> findAll() throws SQLException {
        //List<Role> roles = new ArrayList<>();
        List<Role> roles = new LinkedList<>();
        
        Connection connection = MySqlConnection.getConnection();
        String query = "SELECT * FROM role";
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

    public void add(RoleCreateDto roledto) throws SQLException {
        String query = "INSERT INTO role (name, description)"
                + "VALUES(?,?)";    
        Connection connection = MySqlConnection.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setNString(1, roledto.getName());
            statement.setNString(2, roledto.getDescription());
            statement.executeUpdate();
            
        }catch (SQLException e) {
            System.out.println("Unable to connect to database.");
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }
}