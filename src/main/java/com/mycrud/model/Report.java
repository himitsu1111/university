package com.mycrud.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by book on 04.02.2018.
 */
@Entity
public class Report {

    private Integer id;
    private String year;
    private String fac;
    private Integer stud;

    @Id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getFac() {
        return fac;
    }

    public void setFac(String fac) {
        this.fac = fac;
    }

    public Integer getStud() {
        return stud;
    }

    public void setStud(Integer stud) {
        this.stud = stud;
    }
}
