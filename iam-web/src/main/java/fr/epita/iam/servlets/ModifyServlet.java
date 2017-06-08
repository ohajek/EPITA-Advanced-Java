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

/**
 * Servlet for updating existing identity in the database.
 * @author ohajek
 *
 */
@WebServlet(name="ModifyServlet", urlPatterns={"/modify"})
public class ModifyServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOGGER = LogManager.getLogger(ModifyServlet.class);

	@Inject
	Dao<Identity> dao;
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	
		long uid = Long.parseLong(req.getParameter("userID"));
		String username = req.getParameter("userName");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String birthdate = req.getParameter("birthdate");
		String type = req.getParameter("userType");
		
		Identity updatedIdentity = new Identity();
		updatedIdentity.setId(uid);
		updatedIdentity.setBirthDate(birthdate);
		updatedIdentity.setDisplayname(username);
		updatedIdentity.setEmail(email);
		updatedIdentity.setPassword(password);
		updatedIdentity.setUserType(type);
		
		try {
			dao.update(updatedIdentity);
			req.setAttribute("message", "Selected identity updated sucessfully.");
			req.setAttribute("message_color", "green");
		    req.getRequestDispatcher("welcome.jsp").forward(req, resp);
		} catch (SQLException e) {
			LOGGER.error("Error during update: {}", e);
		}
		
	}
}
