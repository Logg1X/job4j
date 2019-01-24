package io.sockets.filemanager.commands;

import io.sockets.filemanager.FileManagerApi;
import io.sockets.filemanager.Manager;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ClientCommandDispatcher {
    private static Manager manager;
    private final DataOutputStream out;
    private final DataInputStream in;
    private final Scanner console = new Scanner(System.in);
    Map<String, Command> dispatcher;


    public ClientCommandDispatcher(DataOutputStream out, DataInputStream in) throws IOException {
        this.out = out;
        this.in = in;
        dispatcher = createCommandStore();
        manager = new FileManagerApi(" ");
    }

    private Map<String, Command> createCommandStore() {
        return Map.of(
                "download", new Download(out, in),
                "upload", new Upload(out, in)
        );
    }

    public ComandSender getComand(String s) {
        ComandSender result = dispatcher.get(s.toLowerCase());
        if (result == null) {
            result = new Command(out, in) {
                @Override
                public List<String> getSendOff() throws IOException {
                    return List.of(s);
                }

                @Override
                public void send() throws IOException {
                    for (String s1 : this.getSendOff()) {
                        ask(s1);
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
        }
        return result;
    }

    private class Download extends Command {

        protected Download(DataOutputStream out, DataInputStream in) {
            super(out, in);
        }

        @Override
        public List<String> getSendOff() throws IOException {
            ask("download");
            String res;
            while (!(res = in.readUTF()).isEmpty()) {
                System.out.println(res);
            }
            String cons = console.nextLine();
            String precons = cons;
            ask(cons);
            try {
                File file = new File(String.format("%s%sDownload", System.getProperty("user.home"), File.separator));
                manager.acceptFile(file, in);
            } catch (FileNotFoundException e) {
                System.out.println("File not found. Try again 'download' or enter other command.");
            }
            return List.of("готово");
        }

        @Override
        public void send() throws IOException {
            getSendOff();
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

    private class Upload extends Command {
        protected Upload(DataOutputStream out, DataInputStream in) {
            super(out, in);
        }

        @Override
        public List<String> getSendOff() throws IOException {
            ask("upload");
            boolean sending = true;
            List<String> result = new ArrayList<>();
            System.out.println("Enter path to upload file:");
            String cons = console.nextLine();
            File file = new File(cons);
            while (sending) {
                while (!file.exists() && sending) {
                    System.out.println("File not found. Try again or enter 'stop'");
                    cons = console.nextLine();
                    if (cons.equals("stop")) {
                        sending = false;
                        break;
                    }
                    file = new File(cons);
                }
                if (file.exists() && file.isFile()) {
                    String filename = file.getName();
                    ask(filename);
                    manager.sendFile(file, out);
                    sending = false;
                } else {
                    ask("File not found");
                }
            }
            return result;
        }

        @Override
        public void send() throws IOException {
            getSendOff();
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


    private void ask(String s) throws IOException {
        out.writeUTF(s);
        out.flush();
    }
}
