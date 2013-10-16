package com.trax.controllers;

import com.trax.models.Owner;
import com.trax.models.Role;
import com.trax.models.User;
import com.trax.services.owner.OwnerService;
import com.trax.services.role.RoleService;
import com.trax.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tonywinters
 * Date: 10/12/13
 * Time: 10:24 PM
 * To change this template use File | Settings | File Templates.
 */

@Controller
@RequestMapping(value="/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private OwnerService ownerService;

    @Autowired
    private RoleService roleService;

    @RequestMapping(value="/add", method= RequestMethod.GET)
    public ModelAndView addUserPage() {
        ModelAndView modelAndView = new ModelAndView("user/add-user");

        // Send Objects to View
        modelAndView.addObject("user", new User());
        modelAndView.addObject("owners", ownerService.getOwners());
        modelAndView.addObject("roles", roleService.getRoles());

        return modelAndView;
    }

    @RequestMapping(value="/add", method=RequestMethod.POST)
    public ModelAndView addingUser(@ModelAttribute User user, @RequestParam String ownerId, @RequestParam String roleId){

        ModelAndView modelAndView = new ModelAndView("home");
        Owner owner = ownerService.getOwner(Long.parseLong(ownerId));
        Role role = roleService.getRole(Long.parseLong(roleId));
        user.setOwner(owner);
        userService.addUser(user);

        String message = "User was successfully added.";
        modelAndView.addObject("message", message);

        return modelAndView;
    }

    @RequestMapping(value="/list")
    public ModelAndView listOfUsers() {
        ModelAndView modelAndView = new ModelAndView("user/list-users");

        List<User> users = userService.getUsers();
        modelAndView.addObject("users", users);

        return modelAndView;
    }

    @RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
    public ModelAndView editUserPage(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("user/edit-user");
        User user = userService.getUser(id);
        modelAndView.addObject("user",user);
        modelAndView.addObject("owners", ownerService.getOwners());
        return modelAndView;
    }

    @RequestMapping(value="/edit/{id}", method=RequestMethod.POST)
    public ModelAndView editingUser(@ModelAttribute User user, @PathVariable Long id) {

        ModelAndView modelAndView = new ModelAndView("home");

        userService.updateUser(user);

        String message = "User was successfully edited.";
        modelAndView.addObject("message", message);

        return modelAndView;
    }

    @RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
    public ModelAndView deleteUser(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("home");
        userService.deleteUser(id);
        String message = "User was successfully deleted.";
        modelAndView.addObject("message", message);
        return modelAndView;
    }
}

