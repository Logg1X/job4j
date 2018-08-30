package ru.job4j.threads.sinhronizy;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.ArrayList;
import java.util.List;

@ThreadSafe
public class UserStorage {
    @GuardedBy("this")
    private final List<User> storage;

    public UserStorage() {
        this.storage = new ArrayList<>();
    }

    public List<User> getStorage() {
        return storage;
    }

    public synchronized boolean add(User user) {
        return storage.add(user);
    }

    public synchronized boolean update(User user) {
        boolean result = false;
        int index = this.getIndextUserId(user.getId());
        if (index != -1 && storage.get(index).getAmount() != user.getAmount()) {
            storage.set(index, user);
            result = true;
        }

        return result;
    }

    public synchronized boolean delete(User user) {
        return storage.remove(user);
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        boolean result = false;
        int indexUser1 = this.getIndextUserId(fromId);
        int indexUser2 = this.getIndextUserId(toId);
        if (indexUser1 != -1 && indexUser2 != -1) {
            if (storage.get(indexUser1).getAmount() >= amount) {
                storage.get(indexUser1)
                        .setAmount(storage.get(indexUser1).getAmount() - amount);
                storage.get(indexUser2)
                        .setAmount(storage.get(indexUser2).getAmount() + amount);
                result = true;
            }
        }
        return result;
    }

    private synchronized int getIndextUserId(int id) {
        int index = -1;
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getId() == id) {
                index = i;
                break;
            }
        }
        return index;
    }

    @ThreadSafe
    public static class User {
        @GuardedBy("this")
        private int id;
        @GuardedBy("this")
        private int amount;

        public User(int id, int amount) {
            this.id = id;
            this.amount = amount;
        }

        public synchronized int getId() {
            return id;
        }

        public synchronized int getAmount() {
            return amount;
        }

        public synchronized void setAmount(int amount) {
            this.amount = amount;
        }
    }

}
