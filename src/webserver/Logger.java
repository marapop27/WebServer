package WebServer;

public class Logger {
    private Logger() {}

    public static void log(String ip, String request, int code) {
        System.out.println("[" + new java.util.Date().toString() + "] " + ip + " \"" + request + "\" " + code);
    }

    public static void serverStarted(int port, String root) {
        System.out.println("[" + new java.util.Date().toString() + "] Server listening on port: " + port + " and root: " + root);
    }
}
