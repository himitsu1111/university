package com.mycrud.controller;

import com.mycrud.model.POJO.Specialty;
import com.mycrud.model.POJO.Students;
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
        System.out.println("ORMService queryfindAllUsersJPA is called");
        String query = "from Students";
        TypedQuery<Students> typedQuery = entityManager.createQuery(query, Students.class);
        return typedQuery.getResultList();
    }
}
