package com.mycrud.controller;

import com.mycrud.model.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by book on 04.02.2018.
 */

@Repository
@Transactional
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
        final String QUERY_SQL = "select row_number() OVER (order by s.startyear) as counter, s.startyear as year, f.name as fac, \n" +
                "\t\tcount(s.name) as stud \n" +
                "\tfrom \n" +
                "\t    students s,\n" +
                "\t    specialty sp,\n" +
                "\t    faculty f\n" +
                "\twhere\n" +
                "\t    s.specialtyid = sp.id\n" +
                "\tand sp.facultyid = f.id    \n" +
                "\tgroup by \n" +
                "\t    rollup(s.startyear, f.name)\n" +
                "\t    order by s.startyear;";
        List<Report> reportList = this.jdbcTemplate.query(QUERY_SQL, new RowMapper<Report>() {
            public Report mapRow(ResultSet resulSet, int rowNum) throws SQLException {
                Report report = new Report();
                report.setId(resulSet.getInt("counter"));
                report.setYear(resulSet.getString("year"));
                report.setFac(resulSet.getString("fac"));
                report.setStud(resulSet.getInt("stud"));
                return report;
            }
        });
        return reportList;
    }
}
