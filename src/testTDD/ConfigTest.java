package testTDD;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.InvalidPathException;

import org.junit.Test;

import exceptions.ConfigInitException;
import persist.Config;

public class ConfigTest {
	
	@Test
	public void testValidConfigPath() {
		String configFileName = "C:/webserver/config/webServerConfig.ini";
		File f = new File(configFileName);
		f.delete();
		
		assertFalse(f.isFile());
		
		new Config(configFileName);

		assertTrue(f.isFile());
	}
	
	@Test
	public void testValidConfigPath2() throws IOException {
		String configFileName = "C:/webserver/config/webServerConfig.ini";
		File f = new File(configFileName);
		f.createNewFile();
		
		assertTrue(f.isFile());
		
		new Config(configFileName);

		assertTrue(f.isFile());
	}
	
//	@Test
//	public void testValidConfigPath3() throws IOException {
//		String configFileName = "C:/webserver/tests/config/webServerConfig1.ini";
//		File f = new File(configFileName);
//		f.createNewFile();
//		
//		FileOutputStream fos = new FileOutputStream(f);
//		
//		assertTrue(f.isFile());
//		
//		Config config = new Config(configFileName);
//
//		assertTrue(f.isFile());
//		
//		config.getSetting("");
//	}
	
	@Test(expected = ConfigInitException.class)
	public void testInvalidConfigPath() {
		Config config = new Config("C:/webserver/config*/webServerConfig.ini");
		config.getSetting("example");
	}

	@Test(expected = ConfigInitException.class)
	public void testNotInitSetting() {
		Config config = new Config("C:/webserver/config/webServerConfig.ini");
		config.getSetting("example");
	}
	
	@Test(expected = ConfigInitException.class)
	public void testSetNotInitSetting() {
		Config config = new Config("C:/webserver/config/webServerConfig.ini");
		config.setSetting("example1", "example2");
	}

}
