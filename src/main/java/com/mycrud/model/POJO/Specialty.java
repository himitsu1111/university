package com.mycrud.model.POJO;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by book on 04.02.2018.
 */
@Entity
@Table(name = "Specialty")
public class Specialty {

    private Integer id;
    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    private Faculty faculty = new Faculty();

    @ManyToOne
    @JoinColumn(name = "facultyid", referencedColumnName = "id", nullable = false)
    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty contact) {
        this.faculty = contact;
    }


    private Set<Students> students  = new HashSet<Students>();

    @OneToMany(mappedBy = "specialty", cascade = CascadeType.ALL, orphanRemoval = true)
    public Set<Students> getStudents() {
        return students;
    }
    public void setStudents(Set<Students> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return getName();
    }
}
