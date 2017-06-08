package fr.epita.iam.services;

import java.sql.SQLException;
import java.util.List;

public interface Dao<T> {

	public void write(T instance) throws SQLException;
	public void delete(T instance) throws SQLException;
	public void update(T instance) throws SQLException;
	public List<T> search(T instance) throws SQLException;
	public List<T> search(String searchString) throws SQLException;
	public T search(Long id) throws SQLException;
}