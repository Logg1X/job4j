package io.crazybot;

import java.io.*;

import static java.nio.charset.StandardCharsets.ISO_8859_1;
import static java.nio.charset.StandardCharsets.UTF_8;

public class CrazyBot {

    public void crazyChat() {
        boolean chat = true;
        boolean pause = false;
        try {
            try (var reader = new BufferedReader(new InputStreamReader(System.in));
                 var writer = new FileWriter(new File("ChatLog.txt"));
                 var currentReadebleFile = new RandomAccessFile(new File("Chat.txt"), "r")
            ) {
                class BotExecutor {
                    BotExecutor(String botPhrase) throws IOException {
                        System.out.println(String.format("Бот: %s", botPhrase));
                        writer.write(String.format("Бот: %s%s", botPhrase, System.lineSeparator()));
                    }
                }
                String consoleLine;
                StringBuilder info = new StringBuilder("Привет! Я Crazy-Bot!").append(System.lineSeparator())
                        .append("Чтобы со мной пообщаться напиши мне что нибудь.").append(System.lineSeparator())
                        .append("Если вы от меня устали введите 'стоп' и можете продолжать общение сами с собой...").append(System.lineSeparator())
                        .append("Для продолжения общения со мной введите 'продолжить'.").append(System.lineSeparator())
                        .append("Для прекращения общения введите 'закончить'.").append(System.lineSeparator());
                System.out.println(info.toString());
                while (chat) {
                    System.out.print("Вы:");
                    consoleLine = reader.readLine();
                    writer.write(String.format("Вы: %s%s", consoleLine, System.lineSeparator()));
                    if (consoleLine.equalsIgnoreCase("стоп")) {
                        pause = true;
                        new BotExecutor("Ок!...молчу..");
                    } else if (consoleLine.equalsIgnoreCase("продолжить") && pause) {
                        pause = false;
                        new BotExecutor("Ура!!! Мне так много нужно тебе рассказать!");
                    } else if (consoleLine.equalsIgnoreCase("закончить")) {
                        chat = false;
                        new BotExecutor("Ой все! Пока!");
                    } else if (!pause) {
                        new BotExecutor(getRandomLineFromFile(currentReadebleFile));
                    }
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getRandomLineFromFile(RandomAccessFile raf) throws IOException {
        String result;
        raf.seek(Math.round(Math.random() * raf.length()));
        while (raf.read() != System.lineSeparator().getBytes()[0]) {
            raf.read();
        }
        result = new String(raf.readLine().getBytes(ISO_8859_1), UTF_8);
        return result;
    }

    public static void main(String[] args) throws IOException {
        CrazyBot crazyBot = new CrazyBot();
        crazyBot.crazyChat();
    }
}
