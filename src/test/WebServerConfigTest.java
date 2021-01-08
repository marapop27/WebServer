package test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Properties;

import org.junit.Test;

import WebServer.WebServerConfig;
import WebServer.WebServerException;

public class WebServerConfigTest {

	@Test
	public void testLoadConfigFileWhenOK() {
		Properties prop = WebServerConfig.loadConfigFile("serverTest.properties");
		assertEquals(8080, WebServerConfig.getPort());
	}
	
	@Test(expected = java.lang.NullPointerException.class)
	public void testLoadConfigFileWhenFileNotFound() {
		Properties prop = WebServerConfig.loadConfigFile("invalid.properties");
	}
    
    @Test
	public void testgetRootFolder() {
		Properties prop = WebServerConfig.loadConfigFile("serverTest.properties");
		assertEquals("C:\\Users\\mara\\Desktop\\TestSite", WebServerConfig.getRootFolder());
	}
    
    @Test
	public void testgetMaintenanceFile() {
		Properties prop = WebServerConfig.loadConfigFile("serverTest.properties");
		assertEquals("C:\\Users\\mara\\Desktop\\TestSite\\maintenance.html", WebServerConfig.getMaintenanceFile());
	}
    
    @Test
	public void testgetServerStatus() {
		Properties prop = WebServerConfig.loadConfigFile("serverTest.properties");
		assertEquals("RUNNING", WebServerConfig.getServerStatus());
	}
    
    @Test
	public void testgetPort() {
		Properties prop = WebServerConfig.loadConfigFile("serverTest.properties");
		assertEquals(8080, WebServerConfig.getPort());
	}
    
    @Test
	public void testgetExtension() {
		File file = new File("maintenance.html");
		assertEquals(".html", WebServerConfig.getExtension(file));
	}
    
    @Test
	public void testgetExtensionWhenNoExtension() {
		File file = new File("maintenancehtml");
		assertEquals("", WebServerConfig.getExtension(file));
	}
    
    
	

}
