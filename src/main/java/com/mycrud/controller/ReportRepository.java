package com.mycrud.controller;

import com.mycrud.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by book on 04.02.2018.
 */

//не работает, ругается на "::text", с подзапросом работает криво
public interface ReportRepository extends JpaRepository<Report, Long> {
    @Query(value = "with abc as (select s.startyear as year, f.name as fac, count(s.name) as stud " +
            "from students s, specialty sp,faculty f where s.specialtyid = sp.id and " +
            "sp.facultyid = f.id group by s.startyear, f.name order by s.startyear desc ), " +
            "totals as (select year, (select k.phone from students k where k.id = 2), sum(stud) from abc group by year order by year desc ) " +
            "select * from abc union " +
            "select * from totals order by year, fac", nativeQuery = true)
    public List<Report> getReport();

}
