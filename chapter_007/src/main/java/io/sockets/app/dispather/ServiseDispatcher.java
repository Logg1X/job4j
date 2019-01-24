package io.sockets.app.dispather;

import io.sockets.app.CrazyBotService;
import io.sockets.app.FileManagerService;

import java.io.*;
import java.util.Map;

public class ServiseDispatcher {
//    private final PrintWriter out;
    private final DataOutputStream out;
    private final DataInputStream in;
    private final Map<String, Servise> servisStorage;


    public ServiseDispatcher(DataOutputStream out, DataInputStream in) {
        this.out = out;
        this.in = in;
        servisStorage = this.createServiceStorage();
    }

    public Servise getServise(String servise) {
        Servise result = this.servisStorage.get(servise);
        if (result == null) {
            result = new Servise() {
                @Override
                public void start() throws IOException {
                    out.writeUTF("Service not found! Try again");
                    out.writeUTF("");
                }
            };
        }
        return result;
    }

    public Map<String, Servise> getServisStorage() {
        return servisStorage;
    }

    private Map<String, Servise> createServiceStorage() {
        return Map.of(
                "manager",
                new FileManagerService(out, in),
                "crazybot",
                new CrazyBotService(out, in),
                "close program",
                new Servise() {
                    @Override
                    public void start() throws IOException {
                        out.writeUTF("close program");
                        out.writeUTF("");
                        out.flush();
                    }
                }
        );
    }

}
