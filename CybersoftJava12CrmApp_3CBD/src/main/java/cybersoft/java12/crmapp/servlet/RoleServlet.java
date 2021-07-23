package cybersoft.java12.crmapp.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cybersoft.java12.crmapp.dto.RoleCreateDto;
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
        switch(req.getServletPath()) {
        case UrlConst.ROLE_DASHBOARD:
            getRoleDashboard(req,resp);
            break;
        case UrlConst.ROLE_ADD:
            getRoleAdd(req,resp);
            break;
        case UrlConst.ROLE_UPDATE:
            getRoleUpdate(req,resp);
            break;
        case UrlConst.ROLE_DELETE:
            getRoleDelete(req,resp);
            break;
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch(req.getServletPath()) {
        case UrlConst.ROLE_DASHBOARD:
            getRoleDashboard(req, resp);
            break;
        case UrlConst.ROLE_ADD:
            getRoleAdd(req,resp);
            break;
        case UrlConst.ROLE_UPDATE:
            getRoleUpdate(req, resp);
            break;
        case UrlConst.ROLE_DELETE:
            getRoleDelete(req, resp);
            break;
        }
    }
    
    private void getRoleDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        
        service.deleteById(id);
        
        resp.sendRedirect(req.getContextPath() + UrlConst.ROLE_DASHBOARD);
    }

    private void getRoleUpdate(HttpServletRequest req, HttpServletResponse resp) {
        // TODO Auto-generated method stub
        
    }

    private void getRoleAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        req.getRequestDispatcher(JspConst.ROLE_ADD)
            .forward(req, resp);
    }

    private void getRoleProfile(HttpServletRequest req, HttpServletResponse resp) {
         //TODO Auto-generated method stub
        
    }

    private void getRoleDashboard(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Role> roles = service.findAll();
        
        if(roles != null && !roles.isEmpty())
            req.setAttribute("roles", roles);
        
        req.getRequestDispatcher(JspConst.ROLE_DASHBOARD)
            .forward(req, resp);
    }
    
    private void postRoleAdd(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		RoleCreateDto dto = extractDtoFromReq(req);
		
		service.add(dto);
		
		resp.sendRedirect(req.getContextPath() + UrlConst.ROLE_DASHBOARD);
	}
	
	private RoleCreateDto extractDtoFromReq(HttpServletRequest req) {
		String name = req.getParameter("name");
		String description = req.getParameter("description");
		return new RoleCreateDto(name, description);
	}   
}