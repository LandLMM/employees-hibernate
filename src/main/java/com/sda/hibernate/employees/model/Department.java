package com.sda.hibernate.employees.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
//@Column(name="dept")
@Table(name="departments")
public class Department {

    @Id // klucz glowny
    @GeneratedValue // autogenerowane
    @Column(name="dept_id") // przechowywane w bazie pod ta kolumna
    private Integer deptId;

    @Column(name="dept_name")
    private String deptName;

    // mappedBy - jakie pole bedzie luzylo do mapowania
    // tryb EAGER albo LAZY jesli chodzi o Lazy Mode
    // FetchType.EAGER - oznacza co zostanie pobrane z departamentu,
    // pracownicy nie zostana pobrani w tym przypadku od razu, aby zaoszczedzic zasoby
    // mappedBy = "department")- definiuje podrzedna encje, mappedBy - definiuje podrzedna zaleznosc
    @OneToMany(fetch=FetchType.LAZY, mappedBy = "department")
    private Set<Employee> employees = new HashSet<>();

    public Department() { }

    public Department(String deptName) {
        this.deptId = null;
        this.deptName = deptName;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }
}
