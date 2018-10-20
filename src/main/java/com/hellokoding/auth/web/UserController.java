package com.hellokoding.auth.web;

import com.hellokoding.auth.model.UserInfo;
import com.hellokoding.auth.service.SecurityService;
import com.hellokoding.auth.service.UserService;
import com.hellokoding.auth.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new UserInfo());

        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") UserInfo userInfoForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userInfoForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(userInfoForm);

        securityService.autologin(userInfoForm.getUsername(), userInfoForm.getPasswordConfirm());

        return "redirect:/welcome";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model) {
        return "welcome";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String getAllUsers(Model model) {
        model.addAttribute("userList", userService.userList());
        return "users";
    }

    @RequestMapping(value = "remove/{id}", method = RequestMethod.GET)
    public String deleteUser(Model model, @PathVariable("id") long id) {
        userService.delUser(id);
        model.addAttribute("userList", userService.userList());
        return "users";
    }

    @RequestMapping(value = "/first", method = RequestMethod.GET)
    public String getFirst() {
        return "first";
    }
    @RequestMapping(value = "/second", method = RequestMethod.GET)
    public String getSecond() {
        return "second";
    }
    @RequestMapping(value = "/third", method = RequestMethod.GET)
    public String getThird() {
        return "third";
    }
}
