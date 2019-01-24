package io.sockets.bot.dispatcher;

import io.sockets.bot.Message;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.BiFunction;

public class SendMessager {
    private final DataOutputStream out;
    private final Message message;

    public SendMessager(DataOutputStream printWriter, Message message) {
        this.out = printWriter;
        this.message = message;
    }


    void writeMessage(Message message) throws IOException {
        out.writeUTF(message.getMessage());
        out.writeUTF("");
        out.flush();
    }


    public void send() {
        Message mess = this.findMessageByString(MessageStorage.getInstance().loadMessages(), this.message.getMessage());
        try {
            writeMessage(mess);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
