import java.net.ServerSocket;
import java.net.Socket;

public class ListenToBroadcast extends Thread {

    int port = 0;
    Site mySite = null;

    public ListenToBroadcast(Site thisSite, int port) {
        this.port = port;
        this.mySite = thisSite;
    }

    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while (true) {
                Socket socket = serverSocket.accept();
                new ProcessRequest(socket, mySite).start();
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
