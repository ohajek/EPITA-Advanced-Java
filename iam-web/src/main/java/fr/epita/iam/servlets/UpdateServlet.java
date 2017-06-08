package fr.epita.iam.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import fr.epita.iam.models.Identity;
import fr.epita.iam.services.Dao;

@WebServlet(name="UpdateServlet", urlPatterns={"/update"})
public class UpdateServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOGGER = LogManager.getLogger(UpdateServlet.class);

	@Inject
	Dao<Identity> dao;
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	
		String selectedId = req.getParameter("selection");
		if(selectedId == null) {
			resp.sendRedirect("searchIdentity.jsp");
			return;
		}
		Identity toModify = null;
		try {
			toModify = dao.search(Long.parseLong(selectedId));
		} catch (SQLException e) {
			LOGGER.error("Error getting identity by id: {} , {}", selectedId, e);
		}
		
		req.setAttribute("identity", toModify);
		req.getRequestDispatcher("modifyIdentity.jsp").forward(req, resp);
	}
}
