package ru.job4j.todo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.todo.models.Task;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.function.Function;

public enum DBStore implements Stor, Closeable {
    INSTANCE();

    private SessionFactory factory;

    DBStore() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("todo.cfg.xml")
                .build();
        try {
            this.factory = new MetadataSources(registry)
                    .buildMetadata()
                    .buildSessionFactory();
        } catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    @Override
    public void addTask(Task task) {
        this.tx(session -> session.save(task));
    }

    @Override
    public void updateStatus(String id) {
        this.tx(session -> {
            var task = session.get(Task.class, Integer.valueOf(id));
            task.setDone(!task.isDone());
            return "Status changed";
        });
    }

    @Override
    public List<Task> getAllTasks() {
        return this.tx(session -> session.createQuery("from Task").list());
    }

    @Override
    public List<Task> getNoCompletedTasks() {
        return this.tx(session -> session.createQuery("from Task where done = false ").list());
    }

    @Override
    public void close() throws IOException {
        this.factory.close();
    }

    private <T> T tx(final Function<Session, T> command) {
        T result = null;
        try (final Session session = factory.openSession()) {
            try {
                session.beginTransaction();
                result = command.apply(session);
                session.getTransaction().commit();
            } catch (final Exception e) {
                session.getTransaction().rollback();
                throw e;
            }
        }
        return result;
    }
}
