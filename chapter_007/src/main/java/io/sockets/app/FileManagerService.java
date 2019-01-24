package io.sockets.app;

import io.sockets.app.dispather.Servise;
import io.sockets.filemanager.commands.ServerCommandDispatcher;
import io.sockets.filemanager.commands.Command;

import java.io.*;
import java.util.List;

public class FileManagerService implements Servise {
    private final DataOutputStream out;
    private final DataInputStream in;

    public FileManagerService(DataOutputStream out, DataInputStream in) {
        this.out = out;
        this.in = in;
    }

    @Override
    public void start() throws IOException {
        String com;
        Command startCommand = new Command(out, in) {
            @Override
            public List<String> getSendOff() throws IOException {
                return new ServerCommandDispatcher(out, in).getComand("info").getSendOff();
            }

            @Override
            public void send() throws IOException {
                for (String s : this.getSendOff()) {
                    out.writeUTF(s);
                    out.flush();
                }
            }

            @Override
            public String getDescription() {
                return null;
            }

            @Override
            public String getName() {
                return null;
            }
        };
        startCommand.send();
        new ServerCommandDispatcher(out, in).getComand("manager").send();
        do {
            System.out.println("wait command ...");
            com = in.readUTF();
            System.out.println(com);
            if (!(com.toLowerCase()).equals("exit")) {
                new ServerCommandDispatcher(out, in).getComand(com).send();
            }
        } while (!(com.toLowerCase()).equals("exit"));
    }
}