package com.mycrud.controller;

import com.mycrud.model.POJO.Faculty;
import com.mycrud.model.POJO.Report;
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

    public List<Report> getReport() {
        String query = "with abc as (select s.startyear as year, f.name as fac, count(s.name) as stud " +
                "from students s, specialty sp,faculty f where s.specialtyid = sp.id and " +
                "sp.facultyid = f.id group by s.startyear, f.name order by s.startyear desc ), " +
                "totals as (select year, (select 'itog'::text), sum(stud) from abc group by year order by year desc ) " +
                "select * from abc union " +
                "select * from totals order by year, fac";
        TypedQuery<Report> typedQuery = entityManager.createNamedQuery(query, Report.class);
        return typedQuery.getResultList();
    }
}
