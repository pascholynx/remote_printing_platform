package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class PrintServer {

    private static final int SERVER_PORT = 9000;

    public static void main(String[] args) {
        PrintServer server = new PrintServer();
        server.run();
    }

    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(SERVER_PORT)) {
            System.out.println("Print server is running on port " + SERVER_PORT);
            while (true) {
                Socket socket = serverSocket.accept();
                handleClient(socket);
            }
        } catch (IOException e) {
            System.err.println("Failed to start the print server: " + e.getMessage());
        }
    }

    private void handleClient(Socket socket) {
        try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                if (inputLine.startsWith("PRINT_JOB")) {
                    String content = inputLine.substring("PRINT_JOB".length()).trim();
                    int jobId = processPrintJob(content);
                    out.println("JOB_ID " + jobId);
                } else {
                    out.println("INVALID_COMMAND");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int processPrintJob(String content) {
        // Simulate processing the print job by generating a random job ID
        int jobId = new Random().nextInt(10000);
        System.out.println("Print job received. Job ID: " + jobId + ", Content: " + content);
        return jobId;
    }
}
