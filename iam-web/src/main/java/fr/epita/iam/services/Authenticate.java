package fr.epita.iam.services;


import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import fr.epita.iam.models.Identity;

/**
 * Singleton class for authenticating the user
 * when logging into the application.
 * @author ohajek
 *
 */
public class Authenticate {

	@Autowired
	SessionFactory sFactory;
	
	private static final Logger LOGGER = LogManager.getLogger(Authenticate.class);
	
	private static Authenticate instance = null;
	
	protected Authenticate() {
	}
	
	/**
	 * Method for getting the singleton instance
	 * @return Instance of class Authenticate
	 */
	public static Authenticate getInstance() {
		if(instance == null) {
			instance = new Authenticate();
		}
		
		return instance;
	}
	
	/**
	 * Method that authenticates the user while logging
	 * into the database. It checks his username and password
	 * with the database. It also checks if the user is admin type.
	 * If not, he is not authenticated.
	 * @param username	Username of user trying to log in
	 * @param password	Password of user trying to log in
	 * @return	Returns Identity object of use trying to log in. Null otherwise.
	 */
	public Identity authenticate(String username, String password) {
		
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		LOGGER.info("Authenticating with username: {}", password);
		
		Session session = sFactory.openSession();
		String queryString = "from Identity as identity where identity.password = :password and identity.displayname = :displayname and identity.userType = :admin";
		Query query = session.createQuery(queryString);
		query.setParameter("displayname", username);
		query.setParameter("password", password);
		query.setParameter("admin", "admin");
		List<Identity> resultSet = query.list();
		session.close();
		
		if(resultSet.isEmpty()) {
			return null;
		}
		else {
			LOGGER.info("FOUND: {} {}", resultSet.get(0).getDisplayname(), resultSet.get(0).getPassword());
			return resultSet.get(0);
		}
	}
}
