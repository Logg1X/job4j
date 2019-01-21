package io.socets.bot;

import java.util.List;
import java.util.Map;

public class MessageStorage {
    private Map<List<String>, Message> messegeStor;

    private MessageStorage() {
        this.messegeStor = createStorage();
    }

    public static MessageStorage getInstance() {
        return MessageStorageHolder.STORAGE;
    }

    public Map<List<String>, Message> loadMessages() {
        return this.messegeStor;
    }

    private Map<List<String>, Message> createStorage() {
        return Map.of(
                List.of("hi", "hello", "hello oracle"),
                new Message("Hello, dear friend, I'm a oracle."),

                List.of("how are u?", "how are you?", "whats up?", "are you ok?"),
                new Message("I'm fine!"),

                List.of("exit"),
                new Message("")
        );
    }

    private static class MessageStorageHolder {
        private static final MessageStorage STORAGE = new MessageStorage();
    }


}
