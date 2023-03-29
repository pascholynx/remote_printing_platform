package src;

public class PrintSystem {

    public static void main(String[] args) {
        System.out.println("Welcome to the Remote Printing System!");

        Thread serverThread = new Thread(() -> {
            PrintServer server = new PrintServer();
            server.run();
        });

        Thread clientThread = new Thread(() -> {
            PrintClient client = new PrintClient();
            client.run();
        });

        serverThread.start();
        clientThread.start();

        try {
            serverThread.join();
            clientThread.join();
        } catch (InterruptedException e) {
            System.err.println("Error joining threads: " + e.getMessage());
        }
    }
}
