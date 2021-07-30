package cybersoft.java12.crmapp.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cybersoft.java12.crmapp.model.Project;
import cybersoft.java12.crmapp.model.Status;
import cybersoft.java12.crmapp.model.Task;
import cybersoft.java12.crmapp.model.User;
import cybersoft.java12.crmapp.service.TaskService;
import cybersoft.java12.crmapp.util.JspConst;
import cybersoft.java12.crmapp.util.UrlConst;

@WebServlet(urlPatterns = {
		UrlConst.TASK_DASHBOARD,
		UrlConst.TASK_ADD,
		UrlConst.TASK_UPDATE,
		UrlConst.TASK_DELETE
})
public class TaskServlet extends HttpServlet{

	private TaskService service;
	
	@Override
	public void init() throws ServletException {
		super.init();
		service = new TaskService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch (req.getServletPath()) {
		case UrlConst.TASK_DASHBOARD:
			getTaskDashboard(req, resp);
			break;
		case UrlConst.TASK_ADD:
			getAdd(req, resp);
			break;
		case UrlConst.TASK_DELETE:
			getDelete(req, resp);
			break;
		case UrlConst.TASK_UPDATE:
			getUpdate(req, resp);
			break;
		}
	}
	
	private void getUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int idByUpdate = Integer.parseInt(req.getParameter("id"));
		
		Task task = service.findTaskById(idByUpdate);
		
		req.setAttribute("task", task);
		
		req.getRequestDispatcher(JspConst.TASK_UPDATE).forward(req, resp);
	}

	private void getAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getRequestDispatcher(JspConst.TASK_ADD).forward(req, resp);
	}

	private void getDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		
		service.delete(id);
		
		resp.sendRedirect(req.getContextPath()+UrlConst.TASK_DASHBOARD);
	}

	private void getTaskDashboard(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Task> tasks = service.findAll();
		
		if(tasks != null && !tasks.isEmpty())
			req.setAttribute("tasks", tasks);
		
		req.getRequestDispatcher(JspConst.TASK_DASHBOARD).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch (req.getServletPath()) {
		case UrlConst.TASK_DASHBOARD:
			getTaskDashboard(req, resp);
			break;
		case UrlConst.TASK_ADD:
			postAdd(req, resp);
			break;
		case UrlConst.TASK_UPDATE:
			postUpdate(req, resp);
			break;
		}
	}

	private void postUpdate(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		int idUpdate = Integer.parseInt(req.getParameter("id"));
		Task task = new Task();
		task.setId(Integer.parseInt(req.getParameter("id")));
		task.setName(req.getParameter("tname"));
		task.setDescription(req.getParameter("description"));
		task.setStart_date(req.getParameter("start_date"));
		task.setEnd_date(req.getParameter("end_date"));
		Project p = new Project();
		p.setId(Integer.parseInt(req.getParameter("pid")));
		task.setProject(p);
		User u = new User();
		u.setId(Integer.parseInt(req.getParameter("uid")));
		task.setUser(u);
		Status s = new Status();
		s.setId(Integer.parseInt(req.getParameter("sname")));
		task.setStatus(s);
		
		service.update(task, idUpdate);
		
		resp.sendRedirect(req.getContextPath()+UrlConst.TASK_DASHBOARD);
	}

	private void postAdd(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Task task = new Task();
		task.setName(req.getParameter("tname"));
		task.setDescription(req.getParameter("description"));
		task.setStart_date(req.getParameter("start_date"));
		task.setEnd_date(req.getParameter("end_date"));
		Project p = new Project();
		p.setId(Integer.parseInt(req.getParameter("pid")));
		task.setProject(p);
		User u = new User();
		u.setId(Integer.parseInt(req.getParameter("uid")));
		task.setUser(u);
		Status s = new Status();
		s.setId(Integer.parseInt(req.getParameter("sname")));
		task.setStatus(s);
		
		service.add(task);
		
		resp.sendRedirect(req.getContextPath()+UrlConst.TASK_DASHBOARD);
		
	}

}

