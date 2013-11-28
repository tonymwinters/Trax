package com.trax.controllers;

import com.trax.models.User;
import com.trax.models.Venue;
import com.trax.services.owner.OwnerService;
import com.trax.services.user.UserService;
import com.trax.services.venue.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tonywinters
 * Date: 10/19/13
 * Time: 3:04 PM
 * To change this template use File | Settings | File Templates.
 */

@Controller
@RequestMapping(value="/venue")
public class VenueController {

    @Autowired
    private UserService userService;

    @Autowired
    private OwnerService ownerService;

    @Autowired
    private VenueService venueService;

    @RequestMapping(value="/add", method= RequestMethod.GET)
    public ModelAndView addVenuePage() {
        ModelAndView modelAndView = new ModelAndView("venue/add-venue");

        // Send Objects to View
        modelAndView.addObject("venue", new Venue());

        return modelAndView;
    }

    @RequestMapping(value="/add", method=RequestMethod.POST)
    public ModelAndView addingVenue(@ModelAttribute Venue venue, Principal principal){

        ModelAndView modelAndView = new ModelAndView("home");

        // Get user and user's owner
        User user = userService.getUser(principal.getName());
        venue.getLocation().setName(venue.getName());
        venue.getContact().setName(venue.getName());
        venue.setOwner(user.getOwner());
        venueService.addVenue(venue);

        String message = "Venue was successfully added.";
        modelAndView.addObject("message", message);

        return modelAndView;
    }

    @RequestMapping(value="/list")
    public ModelAndView listVenues() {
        ModelAndView modelAndView = new ModelAndView("venue/list-venues");

        List<Venue> venues = venueService.getVenues();
        modelAndView.addObject("venues", venues);

        return modelAndView;
    }

    @RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
    public ModelAndView editVenuePage(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("venue/edit-venue");
        Venue venue = venueService.getVenue(id);
        modelAndView.addObject("venue",venue);
        return modelAndView;
    }

    @RequestMapping(value="/edit/{id}", method=RequestMethod.POST)
    public ModelAndView editingVenue(@ModelAttribute Venue venue, Principal principal) {

        ModelAndView modelAndView = new ModelAndView("home");

        // Get user and user's owner
        User user = userService.getUser(principal.getName());
        venue.getLocation().setName(venue.getName());
        venue.getContact().setName(venue.getName());
        venue.setOwner(user.getOwner());

        venueService.updateVenue(venue);

        String message = "Venue was successfully edited.";
        modelAndView.addObject("message", message);

        return modelAndView;
    }

    @RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
    public ModelAndView deleteVenue(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("home");
        venueService.deleteVenue(id);
        String message = "Venue was successfully deleted.";
        modelAndView.addObject("message", message);
        return modelAndView;
    }
}
