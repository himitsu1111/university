package com.mycrud.controller;

import com.mycrud.model.Faculty;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by book on 03.02.2018.
 */
@RequestMapping("/fac")
@Controller
public class FacController {

    private static final Logger logger = Logger.getLogger(FacController.class);

    @Autowired
    private FacService facService;

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model uiModel) {
            List<Faculty> faculties = facService.getAllFaculty();
            uiModel.addAttribute("faculties", faculties);
        return "fac";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addNewFac(@RequestParam("facName") String name,
                            @RequestParam("facId") String id, Model uiModel) {

        String buf = id;

        if (buf.isEmpty())
            facService.addFaculty(name);
        else {
            if (name.equals("TheDelete"))
                facService.deleteFaculty(id);
            else
                facService.updateFaculty(id, name);
        }

        List<Faculty> faculties = facService.getAllFaculty();
        uiModel.addAttribute("faculties", faculties);
      //  System.out.println("ws in controller " + faculties.get(1).getName());
        return "fac";
    }
}
