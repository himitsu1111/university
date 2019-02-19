package com.mycrud.controller;

import com.mycrud.model.Specialty;
import com.mycrud.model.Students;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by book on 04.02.2018.
 */
@RequestMapping("/stud")
@Controller
public class StudController {

    private static final Logger logger = Logger.getLogger(StudController.class);

    @Autowired
    private StudService studService;

    @Autowired
    private SpecService specService;

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model uiModel) {
        List<Students> students = studService.getAllStudents();
        List<Specialty> specialties = specService.getAllSpecialties();
        uiModel.addAttribute("students", students);
        uiModel.addAttribute("specialty", specialties);
        return "stud";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addNewStud(@RequestParam("specName") String specName,
                             @RequestParam("studName") String name,
                             @RequestParam("studYear") String year,
                             @RequestParam("studId") String id,
                             @RequestParam("studPhone") String phone, Model uiModel) {

        String buf = id;
        //TODO:
        String nameUtf = StringHelper.convertFromUTF8(name);
        if (buf.isEmpty())
            studService.addStud(nameUtf, phone, year, specName);
        else {
            if (nameUtf.equals("TheDelete"))
                studService.deleteStud(id);
            else
                studService.updateStud(id, nameUtf, phone, year, specName);
        }

        List<Students> students = studService.getAllStudents();
        List<Specialty> specialties = specService.getAllSpecialties();
        uiModel.addAttribute("students", students);
        uiModel.addAttribute("specialty", specialties);
        return "stud";
    }
    @ExceptionHandler
    public String handleException(Exception e, Model uiModel) {

        uiModel.addAttribute("error", e.getMessage());
        return "error";
    }
}
