package com.mycrud.controller;

import com.mycrud.model.POJO.Faculty;
import com.mycrud.model.POJO.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by book on 04.02.2018.
 */
@RequestMapping("/report")
@Controller
public class ReportController {

    @Autowired
//    private ReportRepository repService;
    JdbcService jdbcService;

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model uiModel) {
        List<Report> report = jdbcService.getReport();
        uiModel.addAttribute("report", report);
        return "rep";
    }
}
