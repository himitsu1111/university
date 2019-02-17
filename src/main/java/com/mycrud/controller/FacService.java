package com.mycrud.controller;

import com.mycrud.model.Faculty;
import com.mycrud.model.Report;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by book on 03.02.2018.
 */
@Repository
@Transactional
public class FacService {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Faculty> getAllFaculty() {
        System.out.println("ORMService queryfindAllUsersJPA is called");
        String query = "from Faculty";
        TypedQuery<Faculty> typedQuery = entityManager.createQuery(query, Faculty.class);
        return typedQuery.getResultList();
    }

    public Faculty getFacultyByName(String name){
        String query = "select id, name from faculty where name = :name";
        return entityManager.createNamedQuery(query, Faculty.class).setParameter("name", name).getSingleResult();
    }

    public List<Report> getReport() {
        String query = "select row_number() OVER (order by s.startyear) as id, s.startyear as year, f.name as fac, \n" +
                "\t\tcount(s.name) as stud \n" +
                "\tfrom \n" +
                "\t    students s,\n" +
                "\t    specialty sp,\n" +
                "\t    faculty f\n" +
                "\twhere\n" +
                "\t    s.specialtyid = sp.id\n" +
                "\tand sp.facultyid = f.id    \n" +
                "\tgroup by \n" +
                "\t    s.startyear, f.name\n" +
                "\t    order by s.startyear;";
        TypedQuery<Report> typedQuery = entityManager.createNamedQuery(query, Report.class);
        return typedQuery.getResultList();
    }

    public boolean addFaculty(String newFaculty) {
        Faculty faculty = new Faculty();
        faculty.setName(newFaculty);
        entityManager.merge(faculty);
        return true;
    }

    public boolean addFaculty(Faculty faculty) {
        entityManager.merge(faculty);
        return true;
    }

    public boolean updateFaculty(String id, String name) {
        Faculty faculty = new Faculty();
        faculty.setId(Integer.valueOf(id));
        faculty = entityManager.find(Faculty.class, faculty.getId());
        faculty.setName(name);
        entityManager.merge(faculty);
        return true;
    }

    public boolean deleteFaculty(String id) {
        Faculty faculty = new Faculty();
        faculty.setId(Integer.valueOf(id));
        faculty = entityManager.find(Faculty.class, faculty.getId());
        entityManager.remove(faculty);
        return true;
    }
    public boolean deleteFaculty(Faculty faculty) {
        entityManager.remove(entityManager.contains(faculty) ? faculty : entityManager.merge(faculty));
        return true;
    }

}
