package cybersoft.java12.crmapp.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cybersoft.java12.crmapp.model.Project;
import cybersoft.java12.crmapp.model.User;
import cybersoft.java12.crmapp.service.ProjectService;
import cybersoft.java12.crmapp.util.JspConst;
import cybersoft.java12.crmapp.util.UrlConst;

@WebServlet(name = "projectServlet", urlPatterns = {
		UrlConst.PROJECT_DASHBOARD,
		UrlConst.PROJECT_MANAGE,
		UrlConst.PROJECT_ADD,
		UrlConst.PROJECT_UPDATE,
		UrlConst.PROJECT_DELETE,
		UrlConst.PROJECT_STAFF,
		UrlConst.PROJECT_STAFF_ADD,
		UrlConst.PROJECT_STAFF_REMOVE
})
public class ProjectServlet extends HttpServlet {
	private ProjectService service;
	
	@Override
	public void init() throws ServletException {
		super.init();
		service = new ProjectService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch (req.getServletPath()) {
		case UrlConst.PROJECT_DASHBOARD:
			getDashboard(req,resp);
			break;
		case UrlConst.PROJECT_MANAGE:
			
			break;
		case UrlConst.PROJECT_ADD:
			getAddNewProject(req, resp);
			break;
		case UrlConst.PROJECT_UPDATE:
			getUpdate(req, resp);
			break;
		case UrlConst.PROJECT_DELETE:
			delete(req, resp);
			break;
		case UrlConst.PROJECT_STAFF:
			
			break;
		case UrlConst.PROJECT_STAFF_ADD:
			
			break;
		case UrlConst.PROJECT_STAFF_REMOVE:
			
			break;
		default:
			
			break;
		}
	}
	
	private void getAddNewProject(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher(JspConst.PROJECT_ADD)
		.forward(req, resp);
	}
	
	private void getUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int code = Integer.parseInt(req.getParameter("id"));
		
		Project project = service.findProjectById(code);
		
		req.setAttribute("project", project);
		
		req.getRequestDispatcher(JspConst.PROJECT_UPDATE).forward(req, resp);
	}

	private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		int code = Integer.parseInt(req.getParameter("id"));
		
		service.delete(code);
		
		resp.sendRedirect(req.getContextPath()+UrlConst.PROJECT_DASHBOARD);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch (req.getServletPath()) {
		case UrlConst.PROJECT_DASHBOARD:
			getDashboard(req,resp);
			break;
		case UrlConst.PROJECT_MANAGE:
			
			break;
		case UrlConst.PROJECT_ADD:
			postAddNewProject(req, resp);
			break;
		case UrlConst.PROJECT_UPDATE:
			postUpdate(req, resp);
			break;
		case UrlConst.PROJECT_DELETE:
			
			break;
		case UrlConst.PROJECT_STAFF:
			
			break;
		case UrlConst.PROJECT_STAFF_ADD:
			
			break;
		case UrlConst.PROJECT_STAFF_REMOVE:
			
			break;
		default:
			
			break;
		}
	}

	private void postAddNewProject(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Project project = new Project();
		project.setName(req.getParameter("name"));
		project.setDescription(req.getParameter("description"));
		project.setStart_date(req.getParameter("start_date"));
		project.setEnd_date(req.getParameter("end_date"));
		User user = new User();
		user.setId(Integer.parseInt(req.getParameter("owner")));
		project.setOwner(user);
		
		service.add(project);
		
		resp.sendRedirect(req.getContextPath() + UrlConst.PROJECT_DASHBOARD);
	}

	private void postUpdate(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		int code = Integer.parseInt(req.getParameter("id"));
		
		Project project = service.findProjectById(code);
		
		project.setName(req.getParameter("name"));
		project.setDescription(req.getParameter("description"));
		project.setStart_date(req.getParameter("start_date"));
		project.setEnd_date(req.getParameter("end_date"));
		User user = new User();
		user.setId(Integer.parseInt(req.getParameter("owner")));
		
		project.setOwner(user);
		
		service.updateProduct(project);
		
		resp.sendRedirect(req.getContextPath() + UrlConst.PROJECT_DASHBOARD);

	}

	private void getDashboard(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Project> projects =  service.findAllProject();
		
		req.setAttribute("projects", projects);
		
		req.getRequestDispatcher(JspConst.PROJECT_DASHBOARD)
			.forward(req, resp);
	}
}
