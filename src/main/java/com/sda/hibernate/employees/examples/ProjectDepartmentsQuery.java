package com.sda.hibernate.employees.examples;

import com.sda.hibernate.employees.model.Department;
import com.sda.hibernate.employees.model.Employee;
import com.sda.hibernate.employees.model.Project;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ProjectDepartmentsQuery {

    public static void main(String[] args) {
        try (SessionFactory factory = new Configuration().configure().buildSessionFactory()) {
            try (Session session = factory.openSession()) {
                Transaction trans = null;
                try {
                    trans = session.beginTransaction();
                    Department dept = new Department("Engineering");
                    Employee emp1, emp2;
                    emp1 = new Employee("Andrew", "Kovalski", 371.0f, dept);
                    emp2 = new Employee("", "Kovalski", 371.0f, dept);
                    session.save(emp1);
                    session.save(emp2);
                    session.save(dept);
                    trans.commit();
                } catch (RuntimeException ex) {
                    if (trans != null) {
                        trans.rollback();
                    }
                    throw ex;
                }
            }
        }

    }
}
