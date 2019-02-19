package com.mycrud.controller;

import com.mycrud.model.Specialty;
import com.mycrud.model.Students;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by book on 04.02.2018.
 */

@Repository
@Transactional
public class StudService {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Students> getAllStudents() {
        String query = "from Students";
        TypedQuery<Students> typedQuery = entityManager.createQuery(query, Students.class);
        return typedQuery.getResultList();
    }

    public void addStud(String newStud, String phone, String year, String specId) {
        Specialty specialty = new Specialty();
        Students student = new Students();
        specialty.setId(Integer.valueOf(specId));
        specialty = entityManager.find(Specialty.class, specialty.getId());
        student.setName(newStud);
        student.setPhone(phone);
        student.setSpecialty(specialty);
        student.setStartyear(year);
        entityManager.merge(student);
    }

    public void updateStud(String id, String name, String phone, String year, String specId) {
        Specialty specialty = new Specialty();
        specialty.setId(Integer.valueOf(specId));
        specialty = entityManager.find(Specialty.class, specialty.getId());
        Students student = new Students();
        student.setId(Integer.valueOf(id));
        student = entityManager.find(Students.class, student.getId());
        student.setName(name);
        student.setPhone(phone);
        student.setStartyear(year);
        student.setSpecialty(specialty);
        entityManager.merge(student);
    }

    public void deleteStud(String id) {
        Students student = new Students();
        student.setId(Integer.valueOf(id));
        student = entityManager.find(Students.class, student.getId());
        entityManager.remove(student);
    }
}
