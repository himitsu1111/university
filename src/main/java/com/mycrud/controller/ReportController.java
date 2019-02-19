package com.mycrud.controller;

import com.mycrud.model.Report;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.util.List;

/**
 * Created by book on 04.02.2018.
 */
@RequestMapping("/report")
@Controller
public class ReportController {

    private static final Logger logger = Logger.getLogger(ReportController.class);

    @Autowired
    JdbcService jdbcService;

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model uiModel) throws IOException {
        List<Report> report = jdbcService.getReport();
        for (Report r : report) {
            if (r.getYear() == null && r.getFac() == null) {
                report.remove(r);
                break;
            }
            if(r.getFac() == null)
                r.setFac("result:");
        }

        int sum = 0;
        int count = 0;
        for(Report summary : report) {
            if(!summary.getFac().equals("result:"))
                sum += summary.getStud();
            count++;
        }


        uiModel.addAttribute("report", report);
        uiModel.addAttribute("resultCount", sum);
        uiModel.addAttribute("finalCount", count);

        return "rep";
    }
}
