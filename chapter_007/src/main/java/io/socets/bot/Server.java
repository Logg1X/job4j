package io.socets.bot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private final Socket socket;

    public Server(Socket socket) {
        this.socket = socket;
    }

    public static void main(String[] args) throws IOException {
        try (Socket socket = new ServerSocket(6111).accept();) {
            new Server(socket).start();
        }
    }

    public void start() throws IOException {
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        Message message;
        do {
            System.out.println("wait command ...");
            message = new Message(in.readLine());
            System.out.println(message.getMessage());
            new SendMessager(out, message).send();
        } while (!"exit".equals(message.getMessage()));
    }
}
