package cybersoft.java12.crmapp.service;

import java.sql.SQLException;
import java.util.List;

import cybersoft.java12.crmapp.dao.ProjectDao;
import cybersoft.java12.crmapp.model.Project;

public class ProjectService {
	private ProjectDao dao;
	

	public ProjectService() {
		dao = new ProjectDao();
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

	

}
