package web.controller;

import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import service.UserService;

import java.util.ArrayList;
import java.util.List;


@Controller
public class UsersController {
    @Autowired
    UserService userService;

    @GetMapping(value = "/users")
    public String listUsers(Model model) {
        String title = "Пользователи";
        model.addAttribute("title", title);
        model.addAttribute("listUsers", userService.listUsers());
        return "users";
    }
    @PostMapping(value = "/adduser", produces = "text/html; charset=utf-8")
    public String addUser(Model model, User user) {
        if (user.getId() == 0) {
            userService.addUser(user);
        } else {
            userService.updateUser(user);
        }
        return "redirect:/users";
    }

    @GetMapping("/remove/{id}")
    public String removeUser(@PathVariable long id) {
        userService.removeUserById(id);
        return "redirect:/users";
    }

    @GetMapping("edit/{id}")
    public String editUser(@PathVariable long id, Model model) {
        model.addAttribute("user",userService.getUserById(id));
        User us = userService.getUserById(id);
        userService.updateUser(us);
        return "edituser";
    }
    @PostMapping("/edituser")
    public String updateUseruse(@ModelAttribute User user) {
        userService.updateUser(user);
        return "redirect:/users";
    }


    @GetMapping("userdata/{id}")
    public String userData(@PathVariable long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "userdata";
    }

    @GetMapping(value = "/test")
    public String printWelcome(ModelMap model) {
        List<String> messages = new ArrayList<>();
        messages.add("Привет");
        messages.add("Это CRUD пользователи");
        messages.add("SpringMVC 5.2.0 version by sep'19 ");
        model.addAttribute("messages", messages);
        return "test";
    }
}
