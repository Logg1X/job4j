package io.sockets.filemanager.commands;

import java.io.*;

public abstract class Command implements ComandSender {
    private final DataOutputStream out;
    private final DataInputStream in;


    protected Command(DataOutputStream out, DataInputStream in) {
        this.out = out;
        this.in = in;
    }

    @Override
    public void send() throws IOException {
        for (String s : this.getSendOff()) {
            out.writeUTF(s);
        }
        out.writeUTF("");
        out.flush();
    }
}
