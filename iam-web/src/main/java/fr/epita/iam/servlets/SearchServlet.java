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

@WebServlet(name="SearchServlet", urlPatterns={"/search"})
public class SearchServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOGGER = LogManager.getLogger(SearchServlet.class);

	@Inject
	Dao<Identity> dao;
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	
		String searchString = req.getParameter("searchString");
		
		List<Identity> results = null;
		try {
			results = dao.search(searchString);
			LOGGER.info("Searching for {}", searchString);
			req.setAttribute("identities", results);
			req.setAttribute("visibility", "block");
		    req.getRequestDispatcher("searchIdentity.jsp").forward(req, resp);
		} catch (SQLException e) {
			LOGGER.error("An error occured during the search: {}", e);
		}


	}
}
