package com.sda.hibernate.employees.examples;

import com.sda.hibernate.employees.model.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import static jdk.nashorn.internal.objects.NativeArray.forEach;

public class AverageInProjectQuery {

    public static void main(String[] args) {

        try (SessionFactory factory = new Configuration().configure().buildSessionFactory()) {
            try (Session session = factory.openSession()) {
                Query<Object[]> q = session.createQuery("SELECT proj.projName, avg(emp.salary) "
                        + "FROM  Project proj JOIN proj.employees emp GROUP BY proj");
                q.getResultList().stream()
                        .map((record) -> record[0] + ": " + record[1])
                        .forEach(System.out::println);
            }
        }
    }
}