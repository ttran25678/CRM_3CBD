package cybersoft.java12.crmapp.service;

import java.sql.SQLException;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import cybersoft.java12.crmapp.dao.RoleDao;
import cybersoft.java12.crmapp.dto.RoleCreateDto;
import cybersoft.java12.crmapp.model.Role;
import cybersoft.java12.crmapp.util.PwdUtils;

public class RoleService {
    private RoleDao dao;
    
    public RoleService() {
        dao = new RoleDao();
    }

    public List<Role> findAll() {
        List<Role> roles = null;
        try {
            roles = dao.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roles;
    }

    public void deleteById(int id) {
        try {
            dao.deleteById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void add(Role role) {
        try {
            dao.add(role);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	public Role findRoleById(int id){
		return dao.findRoleById(id);
	}

	public void updateRoleById(Role role, int id) {
		try {
            dao.update(role, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
}