package com.trax.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

/**
 * Created with IntelliJ IDEA.
 * User: tonywinters
 * Date: 10/14/13
 * Time: 9:56 PM
 * To change this template use File | Settings | File Templates.
 */

@Controller
public class AuthController {

        @RequestMapping(value = "/logoutPage", method = RequestMethod.GET)
        public String logoutPage() {
            return "logoutPage";
        }

        @RequestMapping(value = "/loginPage", method = RequestMethod.GET)
        public String loginPage() {
            return "login";
        }

}
