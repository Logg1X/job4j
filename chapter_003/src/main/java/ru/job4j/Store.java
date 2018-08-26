package ru.job4j;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Store {

    public Info diff(List<User> previoues, List<User> current) {
        Info info = new Info();
        HashMap<Integer, User> previouesMap = new HashMap<>();
        HashMap<Integer, User> currentMap = new HashMap<>();
        previoues.forEach(user -> previouesMap.put(user.id, user));
        current.forEach(user -> currentMap.put(user.id, user));
        this.addedAndChangedInfo(previouesMap, currentMap, info);
        this.deletedInfo(previouesMap, currentMap, info);
        return info;
    }

    private void addedAndChangedInfo(HashMap<Integer, User> previouesMap, HashMap<Integer, User> currentMap, Info info) {
        currentMap.forEach((integer, user) -> {
            if (previouesMap.containsKey(integer)
                    && !user.name.equals(previouesMap.get(integer).name)) {
                info.info[info.changedIndex]++;
            } else {
                info.info[info.addedIndex]++;
            }
        });
    }

    private void deletedInfo(HashMap<Integer, User> previouesMap, HashMap<Integer, User> currentMap, Info info) {
        previouesMap.forEach((integer, user) -> {
            if (!currentMap.containsKey(integer)) {
                info.info[info.deletedIndex]++;
            }
        });
    }


    static class Info {
        private final int[] info;
        private final int addedIndex = 0;
        private final int deletedIndex = 1;
        private final int changedIndex = 2;

        Info() {
            this.info = new int[3];
        }

        @Override
        public String toString() {
            return String.format("New: %s, Deleted: %s, Changed: %s",
                    this.info[addedIndex], this.info[deletedIndex], this.info[changedIndex]);
        }
    }

    public static class User {
        private int id;
        private String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
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
            return id == user.id
                    && Objects.equals(name, user.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }
    }
}
