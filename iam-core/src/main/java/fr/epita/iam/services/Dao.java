package fr.epita.iam.services;

import java.sql.SQLException;
import java.util.List;

/**
 * Interface for DAO type object to make
 * specification more easy and clear
 * @author ohajek
 *
 * @param <T> Type of which this class is DAO of
 */
public interface Dao<T> {
	/**
	 * Writes the object into the database
	 * @param instance		object to be written into the database
	 * @throws SQLException
	 */
	public void write(T instance) throws SQLException;
	
	/**
	 * Deletes object from the database
	 * @param instance		Object to be deleted from database
	 * @throws SQLException
	 */
	public void delete(T instance) throws SQLException;
	
	/**
	 * Updates given object in the database
	 * @param instance		Object to be updated
	 * @throws SQLException
	 */
	public void update(T instance) throws SQLException;
	
	/**
	 * Searches for object in the database based on given object
	 * @param instance		Object to be searched for
	 * @return				List of found objects, NULL otherwise
	 * @throws SQLException
	 */
	public List<T> search(T instance) throws SQLException;
	
	/**
	 * Searches for object in the database based on given string.
	 * 
	 * @param searchString	Search string
	 * @return				List of found objects, null otherwise
	 * @throws SQLException
	 */
	public List<T> search(String searchString) throws SQLException;
	
	/**
	 * Searches the database for object with given id
	 * @param id			Id of object to be searched for
	 * @return				Found object, null otherwise
	 * @throws SQLException
	 */
	public T search(Long id) throws SQLException;
}