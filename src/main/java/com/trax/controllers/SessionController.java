package com.trax.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created with IntelliJ IDEA.
 * User: mattgarkusha
 * Date: 11/23/13
 * Time: 3:34 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping(value="/session")
public class SessionController {

    @RequestMapping(value="/show")
    public ModelAndView showSession(){
        return new ModelAndView("session/session-view");
    }


}
