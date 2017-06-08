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

import fr.epita.iam.models.Address;

public class AddressDAO implements Dao<Address>{

	private static final Logger LOGGER = LogManager.getLogger(AddressDAO.class);
	
	@Inject
	SessionFactory sFactory;
	
	private AddressDAO(){
	}
	
	public void write(Address address) throws SQLException{
		LOGGER.info("adding address : {} ", address);
		Session session = sFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(address);
		transaction.commit();
		session.close();
	}
	
	public void update(Address address) throws SQLException {
		LOGGER.info("updating address : {} ", address);
		Session session = sFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.update(address);
		transaction.commit();
		session.close();
	}
	
	public void delete(Address address) throws SQLException {
		LOGGER.info("deleting address : {} ", address);
		Session session = sFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.delete(address);
		transaction.commit();
		session.close();
	}
	
	public List<Address> search(Address address) throws SQLException {
		LOGGER.info("retrieving address : {} ", address);
		Session session = sFactory.openSession();
		String queryString = "from Address as address where address.addressDesc like :addressDesc";
		Query query = session.createQuery(queryString);
		query.setParameter("addressDesc", "%" + address.getAddressDesc() +"%");
		List<Address> addressList = query.list();
		session.close();
		return addressList;
		
	}

	public List<Address> search(String searchString) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public Address search(Long id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}	
}