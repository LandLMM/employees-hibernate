package com.sda.hibernate.employees.examples;

import com.sda.hibernate.employees.model.Project;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.sql.rowset.Joinable;
import java.util.List;
import java.util.Queue;

import static org.hibernate.hql.internal.antlr.HqlTokenTypes.JOIN;
import static org.hibernate.hql.internal.antlr.HqlTokenTypes.WHERE;

public class ProjectDepartmentsQueryExample {

    public static void main(String[] args) {
        try (SessionFactory factory = new Configuration().configure().buildSessionFactory()) {
            try (Session session = factory.openSession()) {
                List<Project> projs = getProjectsByDapertment(session, "IT");
                projs.stream().map((proj) -> proj.getProjName()).forEach(System.out::println);
            }
        }
    }

    public static List<Project> getProjectsByDapertment(Session session, String deptName) {
        Query q = session.createQuery("SELECT DISTINCT  proj FROM Project proj JOIN proj.employees emp " +
                "JOIN emp.department dept WHERE dept.deptName =:name");
        q.setParameter("name", deptName);
        return q.getResultList();

    }
}
