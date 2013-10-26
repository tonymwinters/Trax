package com.trax.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created with IntelliJ IDEA.
 * User: ajdanelz
 * Date: 10/26/13
 * Time: 4:11 PM
 * To change this template use File | Settings | File Templates.
 */

@Controller
@RequestMapping(value="/admin")
public class AdminController {
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getTable() {
        return new ModelAndView("admin/table");
    }
}
