package WebServer;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServer {
    private int port;
    private File root;
    private File maintance;
    private ServerStatus status;
    private boolean running = false;


    public WebServer(String root, int port) throws WebServerException {
        try {
            this.root = new File(root).getCanonicalFile();
        } catch (IOException e) {
            throw new WebServerException("Cannot get the file passed as root");
        }
        if (!this.root.isDirectory()) {
            throw new WebServerException("The file passed as root is not a directory");
        }
        this.port = port;
    }

    public void start() throws WebServerException {
    	running = true;
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            Logger.serverStarted(port, root.getAbsolutePath());
        } catch (Exception e) {
            throw new WebServerException("Cannot start the web server on port " + port + ".");
        }

        ThreadGroup clients = new ThreadGroup("HTTP Clients");
        while ((status == ServerStatus.RUNNING || status == ServerStatus.MAINTENANCE) && running) {
            try {
                Socket socket = serverSocket.accept();
                ClientThread clientThread = new ClientThread (socket, root, this);
                new Thread(clients, clientThread)
                        .start();
            }
            catch (Exception ex) {
                  new WebServerException("Cannot get new connection " + ex);
            }
        }
    }
    
    public void stop() {
    	running = false;
    }
    
    public ServerStatus getStatus() {return status;}
    public File getMaintance () { return maintance;  }
    public void setMaintance(File maintance) { this.maintance = maintance;  }
    public void setServerStatus(ServerStatus stat) {
        this.status = stat;
    }
    public void setRoot(String root) throws WebServerException {
        try {
            this.root = new File(root).getCanonicalFile();
        } catch (IOException e) {
            throw new WebServerException("Cannot get the file passed as root");
        }
        if (!this.root.isDirectory()) {
            throw new WebServerException("The file passed as root is not a directory");
        }
    }
}
