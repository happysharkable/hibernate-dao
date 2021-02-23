package ru.gb.hibernate;

import org.hibernate.Session;
import java.io.Serializable;
import java.util.List;

public class DAO<Entity, Id extends Serializable> {
    private Class<Entity> entity;
    private Class<Id> id;

    public DAO(Class<Entity> entityClass, Class<Id> idClass) {
        entity = entityClass;
        id = idClass;
    }

    public Entity findById(Id id) {
        Entity e;
        try (Session session = DBService.getFactory().getCurrentSession()) {
            session.beginTransaction();
            e = session.get(entity, id);
        }
        return e;
    }

    public List<Entity> findAll() {
        List<Entity> students;
        try (Session session = DBService.getFactory().getCurrentSession()) {
            session.beginTransaction();
            students = session.createQuery("SELECT s FROM Student s", entity).getResultList();
        }
        return students;
    }

    public void save(Entity newEntity) {
        try (Session session = DBService.getFactory().getCurrentSession()) {
            session.beginTransaction();
            session.save(newEntity);
            session.getTransaction().commit();
        }
    }

    public void delete(Entity entity) {
        try (Session session = DBService.getFactory().getCurrentSession()) {
            session.beginTransaction();
            session.delete(entity);
            session.getTransaction().commit();
        }
    }
}
