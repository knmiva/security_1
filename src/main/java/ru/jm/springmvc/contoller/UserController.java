package ru.jm.springmvc.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ru.jm.springmvc.model.User;
import ru.jm.springmvc.service.UserService;

@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String listUsers(Model theModel) {
        List < User > theUsers = userService.getUsers();
        theModel.addAttribute("users", theUsers);
        return "list-users";
    }

    @GetMapping("/showForm")
    public String showFormForAdd(Model theModel) {
        User theUser = new User();
        theModel.addAttribute("user", theUser);
        return "user-form";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") User theUser) {
        userService.saveUser(theUser);
        return "redirect:/";
    }

    @GetMapping("/updateForm")
    public String showFormForUpdate(@RequestParam("userId") long theId,
        Model theModel) {
        User theUser = userService.getUser(theId);
        theModel.addAttribute("user", theUser);
        return "user-form";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam("userId") long theId) {
        userService.deleteUser(theId);
        return "redirect:/";
    }
}
