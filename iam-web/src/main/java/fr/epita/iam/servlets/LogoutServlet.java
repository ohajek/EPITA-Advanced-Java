package fr.epita.iam.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet(name="LogoutServlet", urlPatterns={"/logout"})
public class LogoutServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private static final Logger LOGGER = LogManager.getLogger(LogoutServlet.class);
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		LOGGER.info("Logging out user: {}", req.getSession().getAttribute("userName"));
		
		req.getSession().invalidate();
		
		req.setAttribute("message", "Successfuly logged out.");
		req.setAttribute("message_color", "green");
		req.getRequestDispatcher("index.jsp").forward(req, resp);
	}
}
