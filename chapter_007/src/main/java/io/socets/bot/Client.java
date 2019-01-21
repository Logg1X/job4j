package io.socets.bot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private final Socket socket;

    public Client(Socket socket) {
        this.socket = socket;
    }

    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket(InetAddress.getByName("127.0.0.1"), 6111)) {
            new Client(socket).start();
        }
    }

    public void start() throws IOException {
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        Scanner console = new Scanner(System.in);
        String cons;
        String str;
        do {
            cons = console.nextLine();
            out.println(cons);
            str = in.readLine();
            while (!str.isEmpty()) {
                System.out.println(str);
            }
        } while (!cons.equals("exit"));
    }
}
