package com.mycrud.controller;

import com.mycrud.model.POJO.Faculty;
import com.mycrud.model.POJO.Students;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by book on 04.02.2018.
 */
@RequestMapping("/stud")
@Controller
public class StudController {

    @Autowired
    private StudService studService;

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model uiModel) {
        List<Students> students = studService.getAllStudents();
        uiModel.addAttribute("students", students);
        System.out.println("ws in controller " + students.get(1).getName());
        return "stud";
    }
}
