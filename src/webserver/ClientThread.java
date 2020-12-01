package WebServer;

import java.io.*;
import java.net.Socket;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;

public class ClientThread implements Runnable {
    private Socket socket;
    private File root;
    private WebServer webServer;
    public ClientThread(Socket socket, File root, WebServer webServer ) {
        this.socket = socket;
        this.root = root;
        this.webServer = webServer;
    }
    @Override
    public void run() {
        String ip = "";
        String request = "";

        int bytesSent = 0;
        BufferedInputStream reader = null;
        try {
            ip = socket.getInetAddress().getHostAddress();
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedOutputStream out = new BufferedOutputStream(socket.getOutputStream());

            String path = "";

            request = in.readLine();
            if (request != null && request.startsWith("GET ")
                    && (request.endsWith(" HTTP/1.0") || request.endsWith("HTTP/1.1"))) {
                path = request.substring(4, request.length() - 9);
            } else {
                Logger.log(ip, request, 405);
                socket.close();
                return;
            }

            HashMap headers = new HashMap();
            String line = null;
            while ((line = in.readLine()) != null) {
                line = line.trim();
                if (line.equals("")) {
                    break;
                }
                int colonPos = line.indexOf(":");
                if (colonPos > 0) {
                    String key = line.substring(0, colonPos);
                    String value = line.substring(colonPos + 1);
                    headers.put(key, value.trim());
                }
            }



            File file = new File(root, URLDecoder.decode(path));
            file = file.getCanonicalFile();
            if (!file.toString().startsWith(root.toString())) {
                Logger.log(ip, request, 404);
                out.write(("HTTP/1.0 403 Forbidden\r\n" +
                        "Content-Type: text/html\r\n" +
                        "Expires: Thu, 01 Dec 1994 16:00:00 GMT\r\n" +
                        "\r\n" +
                        "<h1>403 Forbidden</h1><code>" + path  + "</code><p><hr>").getBytes());
                out.flush();
                socket.close();
                return;
            }


            if (webServer.getStatus() == ServerStatus.RUNNING) {
                if (file.isDirectory()) {
                    for (int i = 0; i < WebServerConfig.DEFAULT_FILES.length; i++) {
                        File indexFile = new File(file, WebServerConfig.DEFAULT_FILES[i]);
                        if (indexFile.exists() && !indexFile.isDirectory()) {
                            file = indexFile;
                            break;
                        }
                    }
                    if (file.isDirectory()) {
                        Logger.log(ip, request, 200);
                        if (!path.endsWith("/")) {
                            path = path + "/";
                        }
                        File[] files = file.listFiles();
                        out.write(("HTTP/1.0 200 OK\r\n" +
                                "Content-Type: text/html\r\n" +
                                "Expires: Thu, 01 Dec 1994 16:00:00 GMT\r\n" +
                                "\r\n" +
                                "<h1>Directory Listing</h1>" +
                                "<h3>" + path + "</h3>" +
                                "<table border=\"0\" cellspacing=\"8\">" +
                                "<tr><td><b>Filename</b><br></td><td align=\"right\"><b>Size</b></td><td><b>Last Modified</b></td></tr>" +
                                "<tr><td><b><a href=\"../\">../</b><br></td><td></td><td></td></tr>").getBytes());
                        for (int i = 0; i < files.length; i++) {
                            file = files[i];
                            if (file.isDirectory()) {
                                out.write(("<tr><td><b><a href=\"" + path + file.getName() + "/\">" + file.getName() + "/</a></b></td><td></td><td></td></tr>").getBytes());
                            } else {
                                out.write(("<tr><td><a href=\"" + path + file.getName() + "\">" + file.getName() + "</a></td><td align=\"right\">" + file.length() + "</td><td>" + new Date(file.lastModified()).toString() + "</td></tr>").getBytes());
                            }
                        }

                        out.flush();
                        socket.close();
                        return;
                    }
                }
            }

            if (webServer.getStatus()  == ServerStatus.MAINTENANCE) {
                    if (file.isDirectory()) {
                        File files[] = file.listFiles();
                        for (File current : files) {
                            if (current.toString().contains(WebServerConfig.getMaintenanceFile()) && !current.isDirectory()) {
                                file = current;
                                webServer.setMaintance(current);
                                break;
                            }
                        }
                    }
                    if (!file.toString().contains("maintenance")) {
                        Logger.log(ip, request, 403);
                        file = webServer.getMaintance();
                    }
            }


            if (!file.exists()) {
                // The file was not found.
                Logger.log(ip, request, 404);
                out.write(("HTTP/1.0 404 File Not Found\r\n" +
                        "Content-Type: text/html\r\n" +
                        "Expires: Thu, 01 Dec 1994 16:00:00 GMT\r\n" +
                        "\r\n" +
                        "<h1>404 File Not Found</h1><code>" + path  + "</code><p><hr>" ).getBytes());
                out.flush();
                socket.close();
                return;
            }



            String extension = WebServerConfig.getExtension(file);

            reader = new BufferedInputStream(new FileInputStream(file));

            Logger.log(ip, request, 200);
            String contentType = (String)WebServerConfig.FILE_TYPES.get(extension);
            if (contentType == null) {
                contentType = "application/octet-stream";
            }
            out.write(("HTTP/1.0 200 OK\r\n" +
                    "Date: " + new Date().toString() + "\r\n" +
                    "Server: JibbleWebServer/1.0\r\n" +
                    "Content-Type: " + contentType + "\r\n" +
                    "Expires: Thu, 01 Dec 1994 16:00:00 GMT\r\n" +
                    "Content-Length: " + file.length() + "\r\n" +
                    "Last-modified: " + new Date(file.lastModified()).toString() + "\r\n" +
                    "\r\n").getBytes());

            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = reader.read(buffer, 0, 4096)) != -1) {
                out.write(buffer, 0, bytesRead);
                bytesSent += bytesRead;
            }
            out.flush();
            reader.close();
            socket.close();




        }  catch (Exception e) {
            Logger.log(ip, "ERROR " + e.toString() + " " + request, 0);
            if (reader != null) {
                try {
                    reader.close();
                }
                catch (Exception ex) {
                }
            }
        }
    }
}
