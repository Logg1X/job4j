package io.sockets.filemanager.commands;

import com.google.common.base.Joiner;
import io.sockets.filemanager.FileManagerApi;
import io.sockets.filemanager.Manager;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ServerCommandDispatcher {
    private static Manager manager;
    private final DataOutputStream out;
    private final DataInputStream in;
    public static Map<String, Command> dispatcher;


    public ServerCommandDispatcher(DataOutputStream out, DataInputStream in) throws IOException {
        this.out = out;
        this.in = in;
        dispatcher = createCommandStore();
    }

    private Map<String, Command> createCommandStore() {
        return Map.of(
                "open", new Open(this.out, this.in),
                "manager", new GetManager(this.out, this.in),
                "back", new Back(this.out, this.in),
                "info", new Info(this.out, this.in),
                "home", new Home(this.out, this.in),
                "download", new Download(this.out, this.in),
                "upload", new Upload(this.out, this.in),
                "update", new Update(this.out, this.in)
        );
    }

    public ComandSender getComand(String s) {
        ComandSender result = dispatcher.get(s.toLowerCase());
        if (result == null) {
            result = new Error(this.out, this.in);
        }
        return result;
    }

    private class Error extends Command {
        protected Error(DataOutputStream out, DataInputStream in) {
            super(out, in);
        }

        @Override
        public List<String> getSendOff() {
            return List.of("Command not recognized");
        }

        @Override
        public String getDescription() {
            return null;
        }

        @Override
        public String getName() {
            return null;
        }
    }

    private class GetManager extends Command {
        protected GetManager(DataOutputStream out, DataInputStream in) {
            super(out, in);
        }

        @Override
        public List<String> getSendOff() throws IOException {
           ask("Enter the root path:");
            List<String> result = new ArrayList<>();
            try {
                String rootFolder = in.readUTF();
                if (!new File(rootFolder).isDirectory()) {
                    throw new IOException();
                }
                manager = new FileManagerApi(rootFolder);
                result.add("Connect to manager successfully");
                result.addAll(manager.getFolder(rootFolder));
            } catch (IOException e) {
                result = List.of("Connect to manager failure.Enter 'manager'");
            }
            return result;
        }

        @Override
        public String getDescription() {
            return "Start manager.Setup of the main directory";
        }

        @Override
        public String getName() {
            return "MANAGER";
        }
    }

    private class Info extends Command {

        protected Info(DataOutputStream out, DataInputStream in) {
            super(out, in);
        }

        @Override
        public List<String> getSendOff() {
            List<String> result = new ArrayList<>();
            result.add("Information on available commands: ".toUpperCase());
            result.add("____________________________________");
            dispatcher.values().forEach(command -> result.add(String.format(
                    "%s - %s", command.getName(), command.getDescription()
            )));
            result.add("____________________________________");
            result.add("Enter 'manager' for quick start.");
            return result;
        }

        @Override
        public String getDescription() {
            return "Show INFO for all commands";
        }

        @Override
        public String getName() {
            return "INFO";
        }
    }

    private class Open extends Command {

        protected Open(DataOutputStream out, DataInputStream in) {
            super(out, in);
        }

        @Override
        public List<String> getSendOff() throws IOException {
            List<String> result = new ArrayList<>();
           ask("Enter the folder name:");
            try {
                result = manager.getFolder(in.readUTF());
            } catch (IOException | NullPointerException e) {
                result.add(Joiner.on(System.lineSeparator()).join(
                        "Folder is not exists!",
                        "Or root folder is not set.",
                        "Сommand again..."
                ));
            }
            return result;
        }

        @Override
        public String getDescription() {
            return "Open folder.";
        }

        @Override
        public String getName() {
            return "OPEN";
        }
    }

    private class Back extends Command {
        protected Back(DataOutputStream out, DataInputStream in) {
            super(out, in);
        }

        @Override
        public List<String> getSendOff() {
            return manager.getParentFolder();
        }

        @Override
        public String getDescription() {
            return "To back.";
        }

        @Override
        public String getName() {
            return "BACK";
        }
    }

    private class Home extends Command {
        protected Home(DataOutputStream out, DataInputStream in) {
            super(out, in);
        }

        @Override
        public List<String> getSendOff() {
            return manager.getRootFolder();
        }

        @Override
        public String getDescription() {
            return "Back to root directory";
        }

        @Override
        public String getName() {
            return "HOME";
        }
    }

    private class Upload extends Command {
        protected Upload(DataOutputStream out, DataInputStream in) {
            super(out, in);
        }

        @Override
        public List<String> getSendOff() throws IOException {
            List<String> result = new ArrayList();
            File file = new File(String.format("%s%s%s", manager.getCurrentFolderPath(), File.separator, in.readUTF()));
            try {
                manager.acceptFile(file, in);
                result.add("File sent!");

            } catch (FileNotFoundException e) {
                result.add("File not sent!");
            }
            return result;
        }

        @Override
        public String getDescription() {
            return "Upload file on server";
        }

        @Override
        public String getName() {
            return "UPLOAD";
        }
    }

    private class Download extends Command {
        protected Download(DataOutputStream out, DataInputStream in) {
            super(out, in);
        }

        @Override
        public List<String> getSendOff() throws IOException {
            ask("Enter the FileName:");
            String fileName = in.readUTF();
            File file = new File(String.format("%s\\%s", manager.getCurrentFolderPath(), fileName));
            if (!file.exists() && !file.isFile()) {
                out.writeInt(-1);
            } else {
                manager.sendFile(file, out);
            }
            return List.of();
        }

        @Override
        public void send() throws IOException {
            this.getSendOff();
        }

        @Override
        public String getDescription() {
            return "Download file";
        }

        @Override
        public String getName() {
            return "DOWNLOAD";
        }
    }

    private class Update extends Command {
        protected Update(DataOutputStream out, DataInputStream in) {
            super(out, in);
        }

        @Override
        public List<String> getSendOff() throws IOException {
            manager.getFolder(manager.getCurrentFolderPath());
            return manager.getFolder(manager.getCurrentFolderPath());
        }

        @Override
        public String getDescription() {
            return "Update current folder.";
        }

        @Override
        public String getName() {
            return "UPDATE";
        }
    }

    private void ask(String s) throws IOException {
        out.writeUTF(s);
        out.writeUTF("");
        out.flush();
    }
}
