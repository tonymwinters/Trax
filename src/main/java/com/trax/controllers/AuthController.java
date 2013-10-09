package com.trax.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

/**
 * Created with IntelliJ IDEA.
 * User: tonywinters
 * Date: 10/8/13
 * Time: 7:37 PM
 * To change this template use File | Settings | File Templates.
 */

@Controller
@RequestMapping("/auth")
public class AuthController {

    Logger log = Logger.getLogger(AuthController.class);
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private static final String LOGIN_SQL = "SELECT first_name FROM public.user WHERE username = ? AND password = ?";

    @RequestMapping("/login")
    @ResponseBody
    public String login(){
        log.info("Authinticating User: Tonymwinters");
        String user = validateUser("tonymwinters", "password");


        return user;
    }

    @PostConstruct
    public void init() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private String validateUser(String username, String password) {
        return jdbcTemplate.queryForObject(LOGIN_SQL, String.class, username, password);

    }


    // Injected Beans ==========
    // =========================

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
