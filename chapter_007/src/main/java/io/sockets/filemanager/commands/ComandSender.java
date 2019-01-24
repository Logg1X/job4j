package io.sockets.filemanager.commands;

import java.io.IOException;
import java.util.List;

public interface ComandSender {
    List<String> getSendOff() throws IOException;

    void send() throws IOException;

    String getDescription();

    String getName();
}
