package com.mycrud.model;

import javax.persistence.*;

/**
 * Created by book on 04.02.2018.
 */

@Entity
@Table(name = "Students")
public class Students {

    private Integer id;
    private String name;
    private String phone;
    private String startyear;

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

    @Basic
    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "startyear", nullable = false)
    public String getStartyear() {
        return startyear;
    }

    public void setStartyear(String startyear) {
        this.startyear = startyear;
    }

    private Specialty specialty = new Specialty();

    @ManyToOne
    @JoinColumn(name = "specialtyid", referencedColumnName = "id", nullable = false)
    public Specialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }
}
