package ru.job4j.threads.nonblockingcashe;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@ThreadSafe
public class Cache {


    @GuardedBy("this")
    private final ConcurrentHashMap<Integer, Base> cache;

    public Cache() {
        this.cache = new ConcurrentHashMap<>();
    }

    public boolean add(Base model) {
        return cache.putIfAbsent(model.id, model) == null;
    }

    public boolean update(Base model) {
        this.cache.computeIfPresent(model.id,
                (integer, base) -> {
                    if (base.version != model.version) {
                        throw new OptimisticException("В изменении отказанно!");
                    }
                    base.setName(model.getName());
                    base.setVersion(base.getVersion() + 1);
                    return base;

                });
        return true;
    }

    public Base delete(Base model) {
        return this.cache.remove(model.id);
    }

    public static class Base {
        private int id = 0;
        private int version;
        private String name;

        public Base(int id, String name) {
            this.id = id;
            this.version = 0;
            this.name = name;
        }

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
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
            Base base = (Base) o;
            return id == base.id
                    && version == base.version;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, version);
        }

        @Override
        public String toString() {
            return "Base{"
                    + "id=" + id
                    + ", version=" + version
                    + ", name='" + name + '\''
                    + '}';
        }
    }

}
