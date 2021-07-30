package cybersoft.java12.crmapp.service;

import java.util.List;

import cybersoft.java12.crmapp.dao.TaskDao;
import cybersoft.java12.crmapp.model.Task;

public class TaskService {

	private TaskDao dao;
	
	public TaskService() {
		dao = new TaskDao();
	}



	public List<Task> findAll() {
		return dao.findAll();
	}



	public void delete(int id) {
		int result = dao.delete(id);
		if(result == 1) {
			System.out.println("Delete completed !!!");
		}else {
			System.out.println("Don't complete delete.");
		}
	}



	public void add(Task task) {
		int result = dao.add(task);
		if(result == 1) {
			System.out.println("Add completed !!!");
		}else {
			System.out.println("Don't complete Add.");
		}
	}



	public void update(Task task, int idUpdate) {
		int result = dao.update(task, idUpdate);
		if(result == 1) {
			System.out.println("Update completed !!!");
		}else {
			System.out.println("Don't complete Update.");
		}
	}



	public Task findTaskById(int idByUpdate) {
		return dao.findTaskById(idByUpdate);
	}

}
