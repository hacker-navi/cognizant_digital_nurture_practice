import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpChatServer {
    public static void main(String[] args) throws Exception {
        int port = 5000;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server listening on port " + port);

            try (Socket clientSocket = serverSocket.accept();
                 BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true)) {

                output.println("Connected to server. Type exit to close.");

                String message;
                while ((message = input.readLine()) != null) {
                    if ("exit".equalsIgnoreCase(message)) {
                        output.println("Goodbye.");
                        break;
                    }
                    output.println("Server received: " + message);
                }
            }
        }
    }
}
