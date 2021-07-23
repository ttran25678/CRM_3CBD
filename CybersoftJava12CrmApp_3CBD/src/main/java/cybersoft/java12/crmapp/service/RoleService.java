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

    public void add(RoleCreateDto roledto) {
        try {
            dao.add(roledto);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}