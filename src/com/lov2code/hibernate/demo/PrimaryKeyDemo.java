package com.lov2code.hibernate.demo;

import com.lov2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class PrimaryKeyDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {
            // create 3 students object
            System.out.println("Creating 3 student objects...");
            Student tempStudent1 = new Student("John", "Doe", "john@lov2code.com");
            Student tempStudent2 = new Student("Mary", "Public", "mary@lov2code.com");
            Student tempStudent3 = new Student("Bonita", "Applebum", "bonita@lov2code.com");

            // start the transaction
            session.beginTransaction();

            // save the student object
            /*
                PK: To change the starting index: ALTER TABLE hb_student_tracker.student AUTO_INCREMENT = 3000;
                Reset to 1: truncate student;
             */
            System.out.println("Saving the students...");
            session.save(tempStudent1);
            session.save(tempStudent2);
            session.save(tempStudent3);

            // commit the transaction
            session.getTransaction().commit();

            System.out.println("Done!");
        }
        finally {
            factory.close();
            System.out.println("Close Factory!");
        }
    }

}
