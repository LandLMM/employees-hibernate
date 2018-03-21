package com.sda.hibernate.employees.examples;

import com.sda.hibernate.employees.model.Department;
import com.sda.hibernate.employees.model.Employee;
import com.sda.hibernate.employees.model.Project;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class AddProjectExample {

    public static void main(String[] args) {

        try (SessionFactory factory = new Configuration().configure().buildSessionFactory()) {
            try (Session session = factory.openSession()) {
                Transaction trans = null;
                try {
                    trans = session.beginTransaction();
                    Query<Employee> q = session.createQuery("FROM Employee emp " +
                            "WHERE emp.department.deptName = 'Research'");
                    List<Employee> result = q.getResultList();
                    Project project = new Project(("Tesla"));
                    result.stream().forEach((emp) -> project.getEmployees().add(emp));
                    session.save(project);
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