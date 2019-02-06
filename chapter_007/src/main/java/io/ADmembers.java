package io;

import java.io.*;
import java.util.*;

import static java.nio.charset.StandardCharsets.ISO_8859_1;

public class ADmembers {
    private List<User> readerForMail(String path) throws IOException {
        File file = new File(path);
        List<User> result = new ArrayList<>();
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r")) {
            randomAccessFile.seek(0);
            long cursor = randomAccessFile.getFilePointer();
            long length = randomAccessFile.length();
            while ((cursor = randomAccessFile.getFilePointer()) != length) {
                String worck = correctEncoding(randomAccessFile.readLine()).strip();
                if (worck.contains("@")) {
                    result.add(new User(worck.strip()));
                }
            }

        }
        return result;
    }

    private String correctEncoding(String line) throws UnsupportedEncodingException {
        return line == null ? "" : new String(line.getBytes(ISO_8859_1), "CP866");
    }

    private String correctEncodingWin(String line) throws UnsupportedEncodingException {
        return line == null ? "" : new String(line.getBytes(ISO_8859_1), "windows-1251");
    }

    private void vipolnit(String command) {
        String zet = "cmd /c  cmd.exe /K \"" + command + "\"";
        Runtime rt = Runtime.getRuntime();
        try {
            Process p = Runtime.getRuntime().exec(zet);
            p.getOutputStream().close();
            try (BufferedReader stdout = new BufferedReader(new InputStreamReader(
                    p.getInputStream()))) {
                String str;
                while ((str = stdout.readLine()) != null) {
                    System.out.println(correctEncoding(str));
//                    System.out.println(new String(str.getBytes(ISO_8859_1),"CP866"));
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private User vipolnitTOmail(String command) {
        String zet = "cmd /c  cmd.exe /K \"" + command + "\"";
        Runtime rt = Runtime.getRuntime();
        User result = null;
        try {
            Process p = Runtime.getRuntime().exec(zet);
            p.getOutputStream().close();
            try (BufferedReader stdout = new BufferedReader(new InputStreamReader(
                    p.getInputStream()))) {
                String str;
                while ((str = stdout.readLine()) != null) {
                    String b = correctEncodingWin(str).strip();
                    if (b.contains("@")) {
                        result = new User(b);
                    }
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }

    private List<User> getMailFromFile(String path) throws IOException {
        File file = new File(path);
        List<User> result = new ArrayList<>();
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r")) {
            randomAccessFile.seek(0);
            long cursor = randomAccessFile.getFilePointer();
            long length = randomAccessFile.length();
            String s;
            while ((cursor = randomAccessFile.getFilePointer()) != length) {
                s = correctEncoding(randomAccessFile.readLine()).strip();
                String[] a = s.split(" ");
                if (a.length > 1) {
                    result.add(this.vipolnitTOmail("dsquery * -filter \"(displayname=" + a[0] + " " + a[1] + " *)\" | dsget user -email"));
                }
            }

        }
        return result;
    }

    private Set<User> comparatorForAdd(List<User> all, List<User> hrlist) {
        Set<User> result = new HashSet<>();
        for (User user : hrlist) {
            if (!all.contains(user) && user != null) {
                result.add(new User(user.mail));
            }
        }
        return result;
    }

    private Set<User> comparatorForDelete(List<User> all, List<User> hrlist) {
        Set<User> result = new HashSet<>();
        for (User user : all) {
            if (!hrlist.contains(user) && user != null) {
                result.add(new User(user.mail));
            }
        }
        return result;
    }

    private void writeFile(Set<User> users, String filename) throws IOException {
        File file = new File(filename);
        try (PrintWriter pw = new PrintWriter(file)) {
            for (User user : users) {
                pw.println(user.mail);
            }
        }
    }


    public static void main(String[] args) throws IOException, InterruptedException {
        String path;
        do {
            System.out.println("Для запуска необходим файл с сотрудниками от кадров в формате .тхт в кодировке CP866 \n"
                    + "Введите путь до файла:\n");
            Scanner scanner = new Scanner(System.in);
            path = scanner.nextLine();
        } while (!new File(path).exists());
        System.out.println("Начало выгрузки 'CHE'........");
        ADmembers reader = new ADmembers();
        reader.vipolnit("dsquery group -name \"che\" | dsget group -members | dsget user -email >CheADUsers.txt");
        System.out.println("Список загружен................");
        System.out.println("Получение E-mail`ов из списка от HR........................");
        List<User> hrList = reader.getMailFromFile(path);
        System.out.println("E-Mail`ы загружены................................................");
        System.out.println("Подготовка файла на добавление....................................................");
        reader.writeFile(reader.comparatorForAdd(reader.readerForMail("C:\\Users\\p.toporov\\IdeaProjects\\job4j13131313\\CheADUsers.txt"), hrList), "Added.txt");
        System.out.println("Файла готов..........................................................................................");
        System.out.println("Подготовка файла на удаление..............................................................................................");
        reader.writeFile(reader.comparatorForDelete(reader.readerForMail("C:\\Users\\p.toporov\\IdeaProjects\\job4j13131313\\CheADUsers.txt"), hrList), "Deleted.txt");
        System.out.println("Файла готов.................................................................................................................................................");
        System.out.println("==============================================================================================================================================================================");
        System.out.println("================Готово!========================");
    }

    private class User {
        private final String mail;

        private User(String mail) {
            this.mail = mail;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            User user = (User) o;
            return Objects.equals(mail, user.mail);
        }

        @Override
        public int hashCode() {
            return Objects.hash(mail);
        }

        @Override
        public String toString() {
            return "User{"
                    + "mail='" + mail + '\''
                    + '}';
        }
    }
}
