package com.mycrud.controller;

import com.mycrud.model.POJO.Faculty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by book on 03.02.2018.
 */
@RequestMapping("/fac")
@Controller
public class FacController {

    @Autowired
    private FacService facService;

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model uiModel) {
        List<Faculty> faculties = facService.getAllFaculty();
        uiModel.addAttribute("faculties", faculties);
        System.out.println("ws in controller " + faculties.get(1).getName());
        return "fac";
    }
}
