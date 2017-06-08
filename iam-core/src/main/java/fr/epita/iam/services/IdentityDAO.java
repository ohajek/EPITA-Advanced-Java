package fr.epita.iam.services;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import fr.epita.iam.models.Identity;

public class IdentityDAO implements Dao<Identity>{
	
	private static final Logger LOGGER = LogManager.getLogger(IdentityDAO.class);

	@Inject
	SessionFactory sFactory;
	
	private IdentityDAO() throws SQLException {
	}
	
	public void write(Identity identity) throws SQLException{
		LOGGER.info("adding identity : {} ", identity);
		Session session = sFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(identity);
		transaction.commit();
		session.close();
	}
	
	public void update(Identity identity) throws SQLException {
		LOGGER.info("updating identity : {} ", identity);
		Session session = sFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.update(identity);
		transaction.commit();
		session.close();
	}
	
	public void delete(Identity identity) throws SQLException {
		LOGGER.info("deleting identity : {} ", identity);
		Session session = sFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.delete(identity);
		transaction.commit();
		session.close();
	}
	
	public List<Identity> search(Identity identity) {
		LOGGER.info("retrieving identity : {} ", identity);
		Session session = sFactory.openSession();
		String queryString = "from Identity as identity where identity.displayname like :displayname";
		Query query = session.createQuery(queryString);
		query.setParameter("displayname", "%" + identity.getDisplayname()+"%");
		List<Identity> identityList = query.list();
		session.close();
		return identityList;
	}
	
	public List<Identity> search(String searchString) {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		LOGGER.info("Looking for: {}", searchString);
		
		Session session = sFactory.openSession();
		String queryString = "from Identity as identity where identity.displayname like :searchString or identity.email like :searchString or identity.birthDate like :searchString or identity.userType like :searchString";
		Query query = session.createQuery(queryString);
		query.setParameter("searchString", "%" + searchString + "%");
		List<Identity> resultSet = query.list();
		session.close();
		
		LOGGER.info("Fund #: {}", resultSet.size());
		return resultSet;
	}

	public Identity search(Long id) throws SQLException {		
		LOGGER.info("Looking for id: {} ", id);
		Session session = sFactory.openSession();
		String queryString = "from Identity as identity where identity.id = :id";
		Query query = session.createQuery(queryString);
		query.setParameter("id", id);
		List<Identity> identities = query.list();
		session.close();
		return identities.get(0);
	}
}