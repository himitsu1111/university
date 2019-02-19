package com.mycrud.controller;

import com.mycrud.model.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by book on 04.02.2018.
 */

@Repository
@Transactional
public class JdbcService {

    @Value("classpath:reportQuery.txt")
    Resource sqlResource;

    @Autowired
    DataSource dataSource;

    private JdbcTemplate jdbcTemplate;
    @PostConstruct
    public void init() {
        System.out.println("postConstruct is called. datasource = " + dataSource);
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Report> getReport() throws IOException {
        File file = sqlResource.getFile();
        String resourceQuery = new String(Files.readAllBytes(file.toPath()));

        List<Report> reportList = this.jdbcTemplate.query(resourceQuery, new RowMapper<Report>() {
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
