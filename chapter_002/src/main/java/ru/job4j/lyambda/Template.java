package ru.job4j.lyambda;


import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.function.Function;

public class Template {

    <T> Function<Session, T> universalMethod(Function<Session, T> actions) {
        return session -> {
            Transaction tx = session.beginTransaction();
            T result = null;
            try {
                result = actions.apply(session);
                tx.commit();
            } catch (Exception e) {
                tx.rollback();
            } finally {
                session.close();
            }
            return result;
        };
    }
}
