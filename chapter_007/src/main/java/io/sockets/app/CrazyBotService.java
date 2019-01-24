package io.sockets.app;

import io.sockets.app.dispather.Servise;
import io.sockets.bot.Message;
import io.sockets.bot.dispatcher.SendMessager;

import java.io.*;

public class CrazyBotService implements Servise {
    private final DataOutputStream out;
    private final DataInputStream in;

    public CrazyBotService(DataOutputStream out, DataInputStream in) {
        this.out = out;
        this.in = in;
    }

    @Override
    public void start() throws IOException {
        Message message;
        out.writeUTF("");
        out.flush();
        do {
            System.out.println("wait command ...");
            message = new Message(in.readUTF());
            System.out.println(message.getMessage());
            if (!"exit".equals(message.getMessage())) {
                new SendMessager(out, message).send();
            }
        } while (!"exit".equals(message.getMessage()));
    }
}
