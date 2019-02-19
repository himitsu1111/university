package com.mycrud.controller;

import com.mycrud.model.Faculty;
import com.mycrud.model.Specialty;
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
public class SpecService {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Specialty> getAllSpecialties() {
        String query = "from Specialty";
        TypedQuery<Specialty> typedQuery = entityManager.createQuery(query, Specialty.class);
        return typedQuery.getResultList();
    }

    public void addSpec(String newSpec, String facultyId) {
        Specialty specialty = new Specialty();
        Faculty faculty = new Faculty();
        faculty.setId(Integer.valueOf(facultyId));
        faculty = entityManager.find(Faculty.class, faculty.getId());
        specialty.setName(newSpec);
        specialty.setFaculty(faculty);
        entityManager.merge(specialty);
    }

    public void updateSpec(String id, String name, String facultyId) {
        Specialty specialty = new Specialty();
        Faculty faculty = new Faculty();
        faculty.setId(Integer.valueOf(facultyId));
        faculty = entityManager.find(Faculty.class, faculty.getId());
        specialty.setId(Integer.valueOf(id));
        specialty = entityManager.find(Specialty.class, specialty.getId());
        specialty.setName(name);
        specialty.setFaculty(faculty);
        entityManager.merge(specialty);
    }

    public void deleteSpec(String id) {
        Specialty specialty = new Specialty();
        specialty.setId(Integer.valueOf(id));
        specialty = entityManager.find(Specialty.class, specialty.getId());
        entityManager.remove(specialty);
    }

}
