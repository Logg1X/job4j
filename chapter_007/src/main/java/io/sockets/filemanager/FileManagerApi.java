package io.sockets.filemanager;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class FileManagerApi implements Manager {
    private final String rootPath;
    private final String sep = File.separator;
    private String currentFolderPath;

    public FileManagerApi(String rootPath) {
        this.rootPath = rootPath;
        this.currentFolderPath = rootPath;
    }

    public String getCurrentFolderPath() {
        return currentFolderPath;
    }


    @Override
    public List<String> getFolder(String folderName) {
        var file = new File(currentFolderPath);
        List<String> result = new ArrayList<>();
        if (file.exists()) {
            result = Arrays.asList(Objects.requireNonNull(new File(currentFolderPath).list()));
            if (!currentFolderPath.equalsIgnoreCase(folderName)) {
                var temp = String.format(
                        "%s%s%s",
                        currentFolderPath,
                        File.separator,
                        folderName
                );
                if (new File(temp).isDirectory()) {
                    currentFolderPath = temp;
                    result = Arrays.asList(Objects.requireNonNull(
                            new File(currentFolderPath).list())
                    );
                } else {
                    result = List.of("Folder is not exists or is it file!");
                }
            }
        } else {
            result = List.of("Folder is not exists");
        }
        return result;
    }

    @Override
    public List<String> getParentFolder() {
        List<String> result = new ArrayList<>();
        if (!this.currentFolderPath.equals(this.rootPath)) {
            int i = this.currentFolderPath.length() - 1;
            while (i != 0) {
                i--;
                if (this.currentFolderPath.charAt(i) == this.sep.charAt(0)) {
                    this.currentFolderPath = this.currentFolderPath.substring(0, i);
                    break;
                }
            }
            result = getFolder(this.currentFolderPath);
        } else {
            result.add("You in root folder! further only space =)");
        }
        return result;
    }

    @Override
    public List<String> getRootFolder() {
        this.currentFolderPath = this.rootPath;
        return Arrays.asList(Objects.requireNonNull(new File(this.rootPath).list()));
    }

    @Override
    public void sendFile(File file, DataOutputStream out) throws IOException {
        try (FileInputStream fis = new FileInputStream(file)) {
            out.writeInt((int) file.length());
            out.flush();
            boolean reading = true;
            while (reading) {
                byte[] buffer = new byte[1024];
                Integer len = fis.read(buffer);
                if (len != -1) {
                    out.write(buffer, 0, len);
                    out.flush();
                } else {
                    reading = false;
                }

            }
        }
    }

    @Override
    public void acceptFile(File file, DataInputStream in) throws FileNotFoundException, IOException {
        try (FileOutputStream fos = new FileOutputStream(file)) {
            int i = 0;
            byte[] buffer = new byte[1024];
            int fileSize = in.readInt();
            if (fileSize != -1) {
                while (i < fileSize) {
                    int len = in.read(buffer);
                    i += len;
                    fos.write(buffer, 0, len);
                    fos.flush();
                }
                System.out.println(String.format("file downloaded to %s ", file.getCanonicalPath()));
            } else {
                System.out.println("File not found. Try again. Enter 'download' ");
            }
        }
    }
}

