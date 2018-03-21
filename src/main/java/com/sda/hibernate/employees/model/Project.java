package com.sda.hibernate.employees.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="projects")
public class Project {

    @Id
    @GeneratedValue
    @Column(name="proj_id")
    private Integer projId;

    @Column(name="proj_name")
    private String projName;

    @ManyToMany(cascade=CascadeType.PERSIST)
    @JoinTable(name="employee_projects",
    joinColumns = {@JoinColumn(name="proj_id")},
    inverseJoinColumns = {@JoinColumn(name="emp_id")}) // Odpowiedzialne za mapowanie 2 projektu
    private Set<Employee> employees = new HashSet<>();

    public Project() { }

    public Project(String name) {
        this.projName = name;
    }


    public Integer getProjId() {
        return projId;
    }

    public void setProjId(Integer projId) {
        this.projId = projId;
    }

    public String getProjName() {
        return projName;
    }

    public void setProjName(String projName) {
        this.projName = projName;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }
}
