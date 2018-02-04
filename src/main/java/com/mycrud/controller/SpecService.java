package com.mycrud.controller;

import com.mycrud.model.POJO.Faculty;
import com.mycrud.model.POJO.Specialty;
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
        System.out.println("ORMService queryfindAllUsersJPA is called");
        String query = "from Specialty";
        TypedQuery<Specialty> typedQuery = entityManager.createQuery(query, Specialty.class);
        return typedQuery.getResultList();
    }
}
