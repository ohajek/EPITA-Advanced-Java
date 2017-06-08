package fr.epita.iam.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import fr.epita.iam.models.Identity;
import fr.epita.iam.services.Dao;

@WebServlet(name="CreationServlet", urlPatterns={"/creation"})
public class CreationServlet extends HttpServlet {
	
	@Autowired
	Dao<Identity> dao;
	
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOGGER = LogManager.getLogger(LogoutServlet.class);
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String email = req.getParameter("email");
		String birthdate = req.getParameter("birthdate");
		String isAdmin = req.getParameter("isAdmin");
		
		LOGGER.info("tried to create: {} {} {} {}", username, password, birthdate, isAdmin);
		
		Identity newUser = new Identity();
		newUser.setDisplayname(username);
		newUser.setEmail(email);
		newUser.setPassword(password);
		newUser.setBirthDate(birthdate);
		if(isAdmin == null) {
			newUser.setUserType("user");
		} else {
			newUser.setUserType(isAdmin);
		}
		try {
			dao.write(newUser);
			req.setAttribute("message", "Successfuly created new user.");
			req.setAttribute("message_color", "green");
			req.getRequestDispatcher("welcome.jsp").forward(req, resp);
		} catch (SQLException e) {
			LOGGER.info("Failed to create new identity!");
		}
	}
}
