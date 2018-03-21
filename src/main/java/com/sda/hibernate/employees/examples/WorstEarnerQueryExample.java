package com.sda.hibernate.employees.examples;

import com.sda.hibernate.employees.model.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class WorstEarnerQueryExample {

    public static void main(String[] args) {

        try (SessionFactory factory = new Configuration().configure().buildSessionFactory()) {
            try (Session session = factory.openSession()) {

                Query<Employee> q = session.createQuery("SELECT emp FROM Employee emp " +
                        "WHERE emp.salary = (SELECT min(emp2.salary) FROM Department dept JOIN dept.employees emp2 " +
                        "WHERE dept = emp.department)");
                q.getResultList().stream().map((emp) -> emp.getFirstName() + " " + emp.getLastName())
                        .forEach(System.out::println);
            }
            factory.close(); // nalezy zamknac dzialanie programu, poniewaz bedzie nadal dzialal w tle
        }
    }
}