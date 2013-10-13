package com.trax.controllers;

import java.util.List;

import com.trax.models.Owner;
import com.trax.services.owner.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created with IntelliJ IDEA.
 * User: tonywinters
 * Date: 10/12/13
 * Time: 2:35 PM
 * To change this template use File | Settings | File Templates.
 */

@Controller
@RequestMapping(value="/owner")
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    @RequestMapping(value="/add", method=RequestMethod.GET)
    public ModelAndView addOwnerPage() {
        ModelAndView modelAndView = new ModelAndView("owner/add-owner");
        modelAndView.addObject("owner", new Owner());
        return modelAndView;
    }

    @RequestMapping(value="/add", method=RequestMethod.POST)
    public ModelAndView addingOwner(@ModelAttribute Owner owner) {

        ModelAndView modelAndView = new ModelAndView("home");
        ownerService.addOwner(owner);

        String message = "Owner was successfully added.";
        modelAndView.addObject("message", message);

        return modelAndView;
    }

    @RequestMapping(value="/list")
    @ResponseBody
    public ModelAndView listOfOwners() {
        ModelAndView modelAndView = new ModelAndView("owner/list-owners");

        List<Owner> owners = ownerService.getOwners();
        modelAndView.addObject("owners", owners);

        return modelAndView;
    }

    @RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
    public ModelAndView editOwnerPage(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView("owner/edit-owner");
        Owner owner = ownerService.getOwner(id);
        modelAndView.addObject("owner",owner);
        return modelAndView;
    }

    @RequestMapping(value="/edit/{id}", method=RequestMethod.POST)
    public ModelAndView editingOwner(@ModelAttribute Owner owner, @PathVariable Integer id) {

        ModelAndView modelAndView = new ModelAndView("home");

        ownerService.updateOwner(owner);

        String message = "Owner was successfully edited.";
        modelAndView.addObject("message", message);

        return modelAndView;
    }

    @RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
    public ModelAndView deleteOwner(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView("home");
        ownerService.deleteOwner(id);
        String message = "Owner was successfully deleted.";
        modelAndView.addObject("message", message);
        return modelAndView;
    }
}
