package com.mycrud.controller;

import com.mycrud.model.Faculty;
import com.mycrud.model.Specialty;
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
@RequestMapping("/spec")
@Controller
public class SpecController {

    private static final Logger logger = Logger.getLogger(SpecController.class);

    @Autowired
    private SpecService specService;
    @Autowired
    private FacService facService;

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model uiModel) {
        List<Specialty> specialties = specService.getAllSpecialties();
        List<Faculty> faculties = facService.getAllFaculty();
        uiModel.addAttribute("faculties", faculties);
        uiModel.addAttribute("specialty", specialties);
        return "spec";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addNewSpec(@RequestParam("specName") String name,
                            @RequestParam("specId") String id,
                            @RequestParam("facName") String facName, Model uiModel) {

        String buf = id;
        //TODO: тест кодировки
        String nameUtf = StringHelper.convertFromUTF8(name);

        if (buf.isEmpty())
            specService.addSpec(nameUtf, facName);
        else {
            if (nameUtf.equals("TheDelete"))
                specService.deleteSpec(id);
            else
                specService.updateSpec(id, nameUtf, facName);
        }

        List<Specialty> specialties = specService.getAllSpecialties();
        List<Faculty> faculties = facService.getAllFaculty();
        uiModel.addAttribute("faculties", faculties);
        uiModel.addAttribute("specialty", specialties);
        return "spec";
    }
    @ExceptionHandler
    public String handleException(Exception e, Model uiModel) {

        uiModel.addAttribute("error", e.getMessage());
        return "error";
    }
}
