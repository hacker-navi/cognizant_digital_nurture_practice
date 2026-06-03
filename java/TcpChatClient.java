import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class TcpChatClient {
    public static void main(String[] args) throws Exception {
        try (Socket socket = new Socket("localhost", 5000);
             BufferedReader serverInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter serverOutput = new PrintWriter(socket.getOutputStream(), true);
             Scanner scanner = new Scanner(System.in)) {

            System.out.println(serverInput.readLine());

            while (true) {
                System.out.print("Message: ");
                String message = scanner.nextLine();
                serverOutput.println(message);

                String response = serverInput.readLine();
                if (response == null) {
                    break;
                }

                System.out.println(response);
                if ("Goodbye.".equals(response)) {
                    break;
                }
            }
        }
    }
}
