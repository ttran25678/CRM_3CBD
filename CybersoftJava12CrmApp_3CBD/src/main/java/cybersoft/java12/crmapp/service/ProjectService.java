package cybersoft.java12.crmapp.service;

import java.sql.SQLException;
import java.util.List;

import cybersoft.java12.crmapp.dao.ProjectDao;
import cybersoft.java12.crmapp.dao.ProjectStaffDao;
import cybersoft.java12.crmapp.model.Project;
import cybersoft.java12.crmapp.model.ProjectUser;

public class ProjectService {
	private ProjectDao dao;
	private ProjectStaffDao pdao;

	public ProjectService() {
		dao = new ProjectDao();
		pdao = new ProjectStaffDao();
	}
	
	public List<Project> findAllProject(){
		List<Project> lst = null;
		try {
			lst = dao.findAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return lst;
	}

	public void updateProduct(Project project) {
		int result = dao.update(project);
		
		if(result == -1)
			System.out.println("Not update !");
		else
			System.out.println("Update completed !");
		
	}

	public void delete(int code) {
		int result = dao.deleteProject(code);
		
		if(result == -1)
			System.out.println("Not delete !");
		else
			System.out.println("Delete completed !");
		
	}

	public Project findProjectById(int code) {
		return dao.findProjectById(code);
	}

	public void add(Project project) {
		int result = dao.addNewProject(project);
		
		if(result == -1)
			System.out.println("Not create !");
		else
			System.out.println("Add new Project completed !");
	}

	public List<ProjectUser> findAllProjectStaff() {
		List<ProjectUser> lst = null;
		try {
			lst = pdao.findAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return lst;
	}

	public void addStaff(ProjectUser pUser) {
		int result = pdao.addStaff(pUser);
		
		if(result == -1)
			System.out.println("Not create !");
		else
			System.out.println("Add new ProjectUser completed !");
	}

	public void deleteStaff(int uid, int pid) {
		int result = pdao.deleteStaff(uid, pid);
		
		if(result == -1)
			System.out.println("Not delete !");
		else
			System.out.println("Add new deleteStaff completed !");
	}

	public void updateStaff(ProjectUser pUser, int pid, int uid) {
		int result = pdao.updateStaff(pUser, pid, uid);
		
		if(result == -1)
			System.out.println("Not update !");
		else
			System.out.println("Add new updateStaff completed !");
	}

	public ProjectUser findStaff(int uid, int pid) {
		return pdao.findStaff(uid, pid);
	}

	

}
