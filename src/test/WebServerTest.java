package test;

import static org.junit.Assert.*;

import org.junit.Test;

import WebServer.WebServer;
import WebServer.WebServerException;

public class WebServerTest {

	@Test(expected = WebServerException.class)
	public void testWebServerWhenPathInvalid() throws WebServerException {
		WebServer ws = new WebServer("invalidpath", 8080);
	}
	
	@Test(expected = WebServerException.class)
	public void testWebServerWhenNotDir() throws WebServerException {
		WebServer ws = new WebServer("C:\\Users\\mara\\Desktop\\TestSite\\maintenance.html", 8080);
	}
	
	@Test(expected = WebServerException.class)
	public void testSetRootWhenPathInvalid() throws WebServerException {
		WebServer ws = new WebServer("invalidpath", 8080);
		ws.setRoot("otherinvalidpath");
	}
	
	@Test(expected = WebServerException.class)
	public void testSetRootWhenNotDir() throws WebServerException {
		WebServer ws = new WebServer("C:\\Users\\mara\\Desktop\\TestSite", 8080);
		ws.setRoot("C:\\Users\\mara\\Desktop\\TestSite\\maintenance.html");
	}
	
	@Test(expected = WebServerException.class)
	public void teststartWhenPortNumberInvalid() throws WebServerException {
		WebServer ws = new WebServer("C:\\Users\\mara\\Desktop\\TestSite", 58);
		ws.start();
	}
	

}
