package io.socets.bot;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.BiFunction;

public class SendMessager {
    private final PrintWriter printWriter;
    private final Message message;

    public SendMessager(PrintWriter printWriter, Message message) {
        this.printWriter = printWriter;
        this.message = message;
    }


    void writeMessage(Message message) {
        printWriter.println(message.getMessage());
        printWriter.println();
    }


    public void send() {
        Message mess = this.findMessageByString(MessageStorage.getInstance().loadMessages(), this.message.getMessage());
        writeMessage(mess);
    }

    private Message findMessageByString(Map<List<String>, Message> messageStor, String str) {
        Message result;
        try {
            BiFunction<Map<List<String>, Message>, String, Message> message =
                    (map1, s) -> Optional.ofNullable(map1.get(
                            map1.keySet().stream().filter(strings -> strings.contains(s.toLowerCase())).findFirst().get()
                    )).get();
            result = message.apply(messageStor, str);
        } catch (NoSuchElementException e) {
            result = new Message("I'm don't understand what you mean...");
        }
        return result;
    }
}
