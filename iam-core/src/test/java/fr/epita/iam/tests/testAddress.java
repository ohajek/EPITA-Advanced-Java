package fr.epita.iam.tests;

import java.sql.SQLException;

import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.epita.iam.models.Identity;
import fr.epita.iam.services.Dao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/applicationContext.xml"})
public class testAddress {
	
	
	@Inject
	Dao<Identity> daoIdentity;
	
	private static final Logger LOGGER = LogManager.getLogger(testAddress.class);
	
	@Test
	public void testDao() throws SQLException{
			
		Identity identity = new Identity();
		identity.setDisplayname("ohajek");
		identity.setUserType("admin");
		identity.setBirthDate("1980-02-02");
		identity.setEmail("ohajek@email.com");
		
		daoIdentity.write(identity);
		daoIdentity.search(identity);
	}

}