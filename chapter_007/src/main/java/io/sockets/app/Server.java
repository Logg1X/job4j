package io.sockets.app;

import io.sockets.app.dispather.ServiseDispatcher;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private Socket serverSocket;

    public Server(Socket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public static void main(String[] args) throws IOException {
        new Server(
                new ServerSocket(
                        Integer.valueOf(PropsLoader.getPropsLoader()
                                .load(Server.class, "socket.properties")
                                .getProperty("port")
                        )
                ).accept()
        ).start();
    }

    public void start() throws IOException {
        try (DataOutputStream out = new DataOutputStream(serverSocket.getOutputStream());
             DataInputStream in = new DataInputStream(serverSocket.getInputStream());
        ) {
            String com;
            do {
                com = in.readUTF();
                new ServiseDispatcher(out, in)
                        .getServise(com).start();
            } while (!(com.toLowerCase()).equals("close program"));
        }
    }
}
