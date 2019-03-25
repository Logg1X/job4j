package ru.job4j.todo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.todo.models.Task;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;

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

    public static void main(String[] args) {
    }

    @Override
    public void addTask(Task task) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.save(task);
            session.getTransaction().commit();
        } catch (Exception e) {
            this.factory.getCurrentSession().getTransaction().rollback();
        }
    }

    @Override
    public void updateStatus(String id) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            Task temp = session.get(Task.class, Integer.valueOf(id));
            temp.setDone(!temp.isDone());
            session.getTransaction().commit();
        } catch (Exception e) {
            this.factory.getCurrentSession().getTransaction().rollback();
        }

    }

    @Override
    public List<Task> getAllTasks() {
        List<Task> result;
        try (Session session = factory.openSession()) {
            result = session.createQuery("from Task").list();
        }
        return result;
    }

    @Override
    public List<Task> getNoCompletedTasks() {
        List<Task> result;
        try (Session session = factory.openSession()) {
            result = session.createQuery("from Task where done = false ").list();
        }
        return result;
    }

    @Override
    public void close() throws IOException {
        this.factory.close();
    }
}
