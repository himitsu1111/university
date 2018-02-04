package com.mycrud.controller;

import com.mycrud.model.POJO.Faculty;
import com.mycrud.model.POJO.Specialty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by book on 04.02.2018.
 */
@RequestMapping("/spec")
@Controller
public class SpecController {

    @Autowired
    private SpecService specService;

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model uiModel) {
        List<Specialty> specialties = specService.getAllSpecialties();
        uiModel.addAttribute("specialty", specialties);
        System.out.println("ws in controller " + specialties.get(1).getName());
        return "spec";
    }
}
