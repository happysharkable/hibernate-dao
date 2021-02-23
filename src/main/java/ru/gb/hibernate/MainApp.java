package ru.gb.hibernate;

import ru.gb.hibernate.entities.Student;

public class MainApp {
    public static void main(String[] args) {
        try {
            DBService.init();
            DAO<Student, Long> studentDAO = new DAO<>(Student.class, Long.class);
            Student s = studentDAO.findById(1L);
            System.out.println(s);

            System.out.println(studentDAO.findAll());
            Student newStudent = new Student("Roman", 30);
            studentDAO.save(newStudent);
            System.out.println(studentDAO.findAll());

            studentDAO.delete(s);
            System.out.println(studentDAO.findAll());

        } finally {
            DBService.close();
        }
    }
}
