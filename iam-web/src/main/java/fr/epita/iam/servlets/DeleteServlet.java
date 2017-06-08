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

@WebServlet(name="DeleteServlet", urlPatterns={"/delete"})
public class DeleteServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOGGER = LogManager.getLogger(DeleteServlet.class);

	@Inject
	Dao<Identity> dao;
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	
		String selectedId = req.getParameter("selection");
		Long userId = (Long) req.getSession().getAttribute("userID");

		if(selectedId == null) {
			resp.sendRedirect("searchIdentity.jsp");
			return;
		}
		Identity toDelete = null;
		try {
			toDelete = dao.search(Long.parseLong(selectedId));
		} catch (SQLException e) {
			LOGGER.error("Error getting identity by id: {} , {}", selectedId, e);
		}
		
		if(toDelete.getId() == userId) {
			req.setAttribute("message", "You cannot delete yourself.");
			req.setAttribute("message_color", "red");
			req.getRequestDispatcher("welcome.jsp").forward(req, resp);
			return;
		}
		
		
		try {
			dao.delete(toDelete);
			req.setAttribute("message", "Selected identity deleted successfully.");
			req.setAttribute("message_color", "green");
			req.getRequestDispatcher("welcome.jsp").forward(req, resp);
		} catch (SQLException e) {
			LOGGER.error("SQL error deleting identity: {}, {}", selectedId, e);
		}

	}
}
