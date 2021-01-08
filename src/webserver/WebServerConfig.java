package WebServer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;
import java.util.HashSet;

public  class WebServerConfig {
    public static final String[] DEFAULT_FILES = new String[] {"index.html", "index.htm", "default.html"};

    public static Properties prop = new Properties();
    public static final HashMap FILE_TYPES = new HashMap();

    public static String getExtension(File file) {
        String extension = "";
        String filename = file.getName();
        int dotPos = filename.lastIndexOf(".");
        if (dotPos >= 0) {
            extension = filename.substring(dotPos);
        }
        return extension.toLowerCase();
    }

    public static void startServerWithPropertiesValues(String propertiesFilepath) {
    	Properties prop = loadConfigFile(propertiesFilepath);
    	
        WebServer ws;
		try {
			ws = new WebServer(getRootFolder(), getPort());
	        ws.setServerStatus(ServerStatus.valueOf(getServerStatus()));
	        ws.start();
	        
		} catch (WebServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public static String getRootFolder () {
    	return prop.getProperty("root");
    }
    public static String getMaintenanceFile () {
    	return prop.getProperty("maintenance");
    }
    public static String getServerStatus() {
    	return prop.getProperty("status");
    }
    public static int getPort() {
    	return Integer.parseInt(prop.getProperty("port"));
    }
    
    public static Properties loadConfigFile(String propertiesFilepath) {
		InputStream is = null;
	    try {
	        is = new FileInputStream(propertiesFilepath);
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }
	    try {
	        prop.load(is);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

		return prop;
    }
    static {
    	


        // Set up the filename extension to mime type associations.

        String ps = "application/postscript";
        FILE_TYPES.put(".ai", ps);
        FILE_TYPES.put(".ps", ps);
        FILE_TYPES.put(".eps", ps);

        String rtf = "application/rtf";
        FILE_TYPES.put(".rtf", rtf);

        String au = "audio/basic";
        FILE_TYPES.put(".au", au);
        FILE_TYPES.put(".snd", au);

        String exe = "application/octet-stream";
        FILE_TYPES.put(".bin", exe);
        FILE_TYPES.put(".dms", exe);
        FILE_TYPES.put(".lha", exe);
        FILE_TYPES.put(".lzh", exe);
        FILE_TYPES.put(".exe", exe);
        FILE_TYPES.put(".class", exe);

        String doc = "application/msword";
        FILE_TYPES.put(".doc", doc);

        String pdf = "application/pdf";
        FILE_TYPES.put(".pdf", pdf);

        String ppt = "application/powerpoint";
        FILE_TYPES.put(".ppt", ppt);

        String smi = "application/smil";
        FILE_TYPES.put(".smi", smi);
        FILE_TYPES.put(".smil", smi);
        FILE_TYPES.put(".sml", smi);

        String js = "application/x-javascript";
        FILE_TYPES.put(".js", js);

        String zip = "application/zip";
        FILE_TYPES.put(".zip", zip);

        String midi = "audio/midi";
        FILE_TYPES.put(".midi", midi);
        FILE_TYPES.put(".kar", midi);

        String mp3 = "audio/mpeg";
        FILE_TYPES.put(".mpga", mp3);
        FILE_TYPES.put(".mp2", mp3);
        FILE_TYPES.put(".mp3", mp3);

        String wav = "audio/x-wav";
        FILE_TYPES.put(".wav", wav);

        String gif = "image/gif";
        FILE_TYPES.put(".gif", gif);

        String ief = "image/ief";
        FILE_TYPES.put(".ief", ief);

        String jpeg = "image/jpeg";
        FILE_TYPES.put(".jpeg", jpeg);
        FILE_TYPES.put(".jpg", jpeg);
        FILE_TYPES.put(".jpe", jpeg);

        String png = "image/png";
        FILE_TYPES.put(".png", png);

        String tiff = "image/tiff";
        FILE_TYPES.put(".tiff", tiff);
        FILE_TYPES.put(".tif", tiff);

        String vrml = "model/vrml";
        FILE_TYPES.put(".wrl", vrml);
        FILE_TYPES.put(".vrml", vrml);

        String css = "text/css";
        FILE_TYPES.put(".css", css);

        String html = "text/html";
        FILE_TYPES.put(".html", html);
        FILE_TYPES.put(".htm", html);
        FILE_TYPES.put(".shtml", html);
        FILE_TYPES.put(".shtm", html);
        FILE_TYPES.put(".stm", html);
        FILE_TYPES.put(".sht", html);

        String txt = "text/plain";
        FILE_TYPES.put(".txt", txt);
        FILE_TYPES.put(".inf", txt);
        FILE_TYPES.put(".nfo", txt);

        String xml = "text/xml";
        FILE_TYPES.put(".xml", xml);
        FILE_TYPES.put(".dtd", xml);

        String mpeg = "video/mpeg";
        FILE_TYPES.put(".mpeg", mpeg);
        FILE_TYPES.put(".mpg", mpeg);
        FILE_TYPES.put(".mpe", mpeg);

        String avi = "video/x-msvideo";
        FILE_TYPES.put(".avi", avi);

    }

}
