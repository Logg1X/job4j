package io.sockets.filemanager;

import java.io.*;
import java.util.List;

public interface Manager {

    List<String> getFolder(String path);

    List<String> getParentFolder();

    List<String> getRootFolder();

    void sendFile(File file, DataOutputStream out) throws IOException;

    void acceptFile(File file, DataInputStream in) throws IOException;

    String getCurrentFolderPath();
}
