package io.sockets.app;

import com.google.common.base.Joiner;
import io.sockets.app.dispather.ServiseDispatcher;
import io.sockets.filemanager.commands.ClientCommandDispatcher;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.*;

public class Client {
    private Socket socket;

    public Client(Socket socket) {
        this.socket = socket;
    }

    public static void main(String[] args) throws IOException {
        Properties props = PropsLoader.getPropsLoader().load(Client.class, "socket.properties");
        try (Socket socket = new Socket(InetAddress.getByName(props.getProperty("ip")), Integer.valueOf(props.getProperty("port")))) {
            new Client(socket).start();
        }
    }

    public void start() throws IOException {
        try (DataOutputStream out = new DataOutputStream(socket.getOutputStream());
             DataInputStream in = new DataInputStream(socket.getInputStream())) {
            Scanner console = new Scanner(System.in);
            String cons = "";
            String str;
            var map = new ServiseDispatcher(out, in).getServisStorage();
            do {
                while (!map.keySet().contains(cons)) {
                    System.out.println(Joiner.on(System.lineSeparator()).join(
                            "Enter service from list:",
                            "___________________________"
                    ));
                    List<String> menu = new ArrayList<>((map.keySet()));
                    Collections.sort(menu, String::compareTo);
                    menu.forEach(s -> System.out.println(String.format("- %s", s)));
                    System.out.println("___________________________");
                    cons = console.nextLine().toLowerCase();
                }
                out.writeUTF(cons);
                out.flush();
                System.out.println("you select - " + cons);
                while (!(cons.toLowerCase()).equals("exit")
                        && !(cons.toLowerCase()).equals("close program")) {
                    if (!cons.equals("download")) {
                        while (!(str = in.readUTF()).isEmpty()) {
                            System.out.println(str);
                        }
                    }
                    cons = console.nextLine();
                    new ClientCommandDispatcher(out, in).getComand(cons).send();
                }
            } while (!(cons.toLowerCase()).equals("close program"));
        }
    }
}