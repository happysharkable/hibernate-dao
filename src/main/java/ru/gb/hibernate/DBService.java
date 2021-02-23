package ru.gb.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class DBService {
    private static final SessionFactory factory = new Configuration()
            .configure("/hibernate.cfg.xml")
            .buildSessionFactory();

    public static SessionFactory getFactory() {
        return factory;
    }

    public static void init() {
        try (Session session = factory.getCurrentSession()){
            String sql = Files.lines(Paths.get("./data.sql")).collect(Collectors.joining(" "));
            session.beginTransaction();
            session.createNativeQuery(sql).executeUpdate();
            session.getTransaction().commit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void close() {
        factory.close();
    }
}
