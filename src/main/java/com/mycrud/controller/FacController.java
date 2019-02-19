package com.mycrud.controller;

import com.mycrud.model.Faculty;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by book on 03.02.2018.
 */
@RequestMapping(value = "/fac", produces = "text/plain;charset=UTF-8")
@Controller
@Scope(value = "request")
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

    @RequestMapping(method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    public String addNewFac(@RequestParam(value = "facName") String name,
                            @RequestParam("facId") String id, Model uiModel, HttpServletRequest request) {


        String buf = id;
        String nameUtf = StringHelper.convertFromUTF8(name);
        if (buf.isEmpty())
            facService.addFaculty(nameUtf);
        else {
            if (nameUtf.equals("TheDelete"))
                facService.deleteFaculty(id);
            else
                facService.updateFaculty(id, nameUtf);
        }

        List<Faculty> faculties = facService.getAllFaculty();
        uiModel.addAttribute("faculties", faculties);
        return "fac";
    }

    @ExceptionHandler
    public String handleException(Exception e, Model uiModel) {

        uiModel.addAttribute("error", e.getMessage());
        return "error";
    }
}
