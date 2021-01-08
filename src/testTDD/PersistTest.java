package testTDD;

import static org.junit.Assert.*;

import java.nio.file.InvalidPathException;

import org.junit.Test;

import exceptions.ConfigInitException;
import exceptions.InvalidPortNumberException;
import persist.Persist;

public class PersistTest {

	@Test(expected = InvalidPortNumberException.class)
	public void testPortNumberInvalid() {
		Persist persist = new Persist();
		persist.setPort(65480); 
		
	}
	
	@Test
	public void testGetPortNumber() {
		Persist persist = new Persist();
		persist.setPort(8080);
		assertEquals(8080, persist.getPort());
	}
	
	@Test(expected = ConfigInitException.class)
	public void testGetMaintenanceDirNotInit() {
		Persist persist = new Persist();
		persist.getMaintenanceDir();
	}
	
	@Test(expected = InvalidPathException.class)
	public void testGetRootDirInvalid() {
		Persist persist = new Persist();
		persist.setRootDir("example");
		assertEquals("example", persist.getRootDir());
	}
	
	@Test()
	public void testGetRootDir() {
		Persist persist = new Persist();
		persist.setRootDir("./www_root");
		assertEquals("./www_root", persist.getRootDir());
	}
	
	@Test
	public void testGetMaintenanceDir() {
		Persist persist = new Persist();
		persist.setRootDir("example");
		// sugestie    config.saveConfiguration(null);
		assertEquals("example", persist.getMaintenanceDir());
	}

}
