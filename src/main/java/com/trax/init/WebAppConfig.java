package com.trax.init;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;


/**
 * Created with IntelliJ IDEA.
 * User: tonywinters
 * Date: 10/12/13
 * Time: 12:19 PM
 * To change this template use File | Settings | File Templates.
 */
@Configuration
@ComponentScan("com.trax.controllers")
@EnableWebMvc
public class WebAppConfig {

    @Bean
    public UrlBasedViewResolver setupViewResolver() {
        UrlBasedViewResolver resolver = new UrlBasedViewResolver();
        resolver.setPrefix("/WEB-INF/pages/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);
        return resolver;
    }
}
