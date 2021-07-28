package cybersoft.java12.crmapp.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cybersoft.java12.crmapp.model.Role;
import cybersoft.java12.crmapp.service.RoleService;
import cybersoft.java12.crmapp.util.JspConst;
import cybersoft.java12.crmapp.util.UrlConst;

@WebServlet(name = "roleServlet", urlPatterns = {
        UrlConst.ROLE_DASHBOARD,
        UrlConst.ROLE_ADD,
        UrlConst.ROLE_DELETE,
        UrlConst.ROLE_UPDATE
})
public class RoleServlet extends HttpServlet {
    private RoleService service;
    
    @Override
    public void init() throws ServletException {
        super.init();
        service = new RoleService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	switch (req.getServletPath()) {
		case UrlConst.ROLE_DASHBOARD:
			getDashboard(req,resp);
			break;
		case UrlConst.ROLE_ADD:
			getAdd(req, resp);
			break;
		case UrlConst.ROLE_DELETE:
			getDelete(req, resp);
			break;
		case UrlConst.ROLE_UPDATE:
			getUpdate(req, resp);
			break;
		default:
			break;
		}
    }

    private void getUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      int id = Integer.parseInt(req.getParameter("id"));
      
      Role role = service.findRoleById(id);
      
      req.setAttribute("role", role);
      
      req.getRequestDispatcher(JspConst.ROLE_UPDATE).forward(req, resp);
	}

	private void getDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		
		service.deleteById(id);
		resp.sendRedirect(req.getContextPath()+UrlConst.ROLE_DASHBOARD);
	}

	private void getAdd(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
    	req.getRequestDispatcher(JspConst.ROLE_ADD).forward(req, resp);
	}

	private void getDashboard(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	 List<Role> roles = service.findAll();
	  
	  if(roles != null && !roles.isEmpty())
	      req.setAttribute("roles", roles);
	  
	  req.setAttribute("roles", roles);
	  
	  req.getRequestDispatcher(JspConst.ROLE_DASHBOARD).forward(req, resp);
	}

    
    
    
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	switch (req.getServletPath()) {
		case UrlConst.ROLE_ADD:
			postAdd(req, resp);
			break;
		case UrlConst.ROLE_DELETE:
			break;
		case UrlConst.ROLE_UPDATE:
			postUpdate(req, resp);
			break;
		
		default:
			break;
		}
    }

	private void postUpdate(HttpServletRequest req, HttpServletResponse resp) throws IOException {
      int id = Integer.parseInt(req.getParameter("id"));
      
      Role role = service.findRoleById(id);
      role.setName(req.getParameter("name"));
      role.setDescription(req.getParameter("description"));
      
      service.updateRoleById(role, id);
           
      resp.sendRedirect(req.getContextPath()+UrlConst.ROLE_DASHBOARD);
	}

	private void postAdd(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Role role = new Role();
		role.setName(req.getParameter("name"));
		role.setDescription(req.getParameter("description"));
		
		service.add(role);
		
		resp.sendRedirect(req.getContextPath()+UrlConst.ROLE_DASHBOARD);
	}
    
    
//    private void postRoleUpdate(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
//		int id = Integer.parseInt(req.getParameter("id"));
//		
//		Role role = service.findRoleById(id);
//		
//		
//	}

//	private void getRoleDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        int id = Integer.parseInt(req.getParameter("id"));
//        
//        service.deleteById(id);
//        
//        resp.sendRedirect(req.getContextPath() + UrlConst.ROLE_DASHBOARD);
//    }

//    private void getRoleUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, SQLException, IOException{
//        int id = Integer.parseInt(req.getParameter("id"));
//        
//        Role role = service.findRoleById(id);
//        
//        req.setAttribute("role", role);
//        
//        req.getRequestDispatcher(JspConst.ROLE_UPDATE)
//		.forward(req, resp);
//    }
//
//    private void getRoleAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        
//        req.getRequestDispatcher(JspConst.ROLE_ADD)
//            .forward(req, resp);
//    }
//

//    

//	
//	private RoleCreateDto extractDtoFromReq(HttpServletRequest req) {
//		String name = req.getParameter("name");
//		String description = req.getParameter("description");
//		return new RoleCreateDto(name, description);
//	}   
}