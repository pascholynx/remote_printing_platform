package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class PrintClient {

    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 9000;
    private Authentication authentication;

    public static void main(String[] args) {
        PrintClient client = new PrintClient();
        client.run();
    }

    public PrintClient() {
        authentication = new Authentication();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Select an option: (1) Register, (2) Login, (3) Exit");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    register(scanner);
                    break;
                case 2:
                    login(scanner);
                    break;
                case 3:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void register(Scanner scanner) {
        System.out.print("Enter a username: ");
        String username = scanner.nextLine();

        System.out.print("Enter a password: ");
        String password = scanner.nextLine();

        if (authentication.register(username, password)) {
            System.out.println("Registration successful.");
        } else {
            System.out.println("Username already exists. Please choose a different one.");
        }
    }

    private void login(Scanner scanner) {
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        if (authentication.authenticate(username, password)) {
            System.out.println("Login successful.");
            connectToPrintServer();
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    private void connectToPrintServer() {
        try (Socket socket = new Socket(SERVER_HOST, SERVER_PORT);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            System.out.println("Connected to the print server.");
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("Enter print job content or type 'exit' to disconnect:");
                String content = scanner.nextLine();

                if ("exit".equalsIgnoreCase(content)) {
                    break;
                }

                out.println("PRINT_JOB " + content);
                String response = in.readLine();

                if (response.startsWith("JOB_ID")) {
                    int jobId = Integer.parseInt(response.substring("JOB_ID".length()).trim());
                    System.out.println("Print job submitted. Job ID: " + jobId);
                } else {
                    System.out.println("Invalid response from the server: " + response);
                }
            }
        } catch (IOException e) {
            System.err.println("Failed to connect to the print server: " + e.getMessage());
        }
    }
}

