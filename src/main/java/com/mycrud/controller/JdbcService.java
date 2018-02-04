package com.mycrud.controller;

import com.mycrud.model.POJO.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by book on 04.02.2018.
 */
@Repository
public class JdbcService {

    @Autowired
    DataSource dataSource;

    private JdbcTemplate jdbcTemplate;
    @PostConstruct
    public void init() {
        System.out.println("postConstruct is called. datasource = " + dataSource);
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Report> getReport() {
        final String QUERY_SQL = "with abc as (select s.startyear as year, f.name as fac, count(s.name) as stud " +
                "from students s, specialty sp,faculty f where s.specialtyid = sp.id and " +
                "sp.facultyid = f.id group by s.startyear, f.name order by s.startyear desc ), " +
                "totals as (select year, (select 'itogo'::text), sum(stud) from abc group by year order by year desc ) " +
                "select * from abc union " +
                "select * from totals order by year, fac";
        List<Report> reportList = this.jdbcTemplate.query(QUERY_SQL, new RowMapper<Report>() {
            public Report mapRow(ResultSet resulSet, int rowNum) throws SQLException {
                Report report = new Report();
                report.setYear(resulSet.getString("year"));
                report.setFac(resulSet.getString("fac"));
                report.setStud(resulSet.getInt("stud"));
                return report;
            }
        });
        return reportList;
    }
}
