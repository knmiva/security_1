package ru.jm.springmvc.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.jm.springmvc.model.User;
import ru.jm.springmvc.service.UserService;
import ru.jm.springmvc.util.UserValidator;

@Controller
public class UserController {

    private UserService userService;
    private UserValidator userValidator;

    @Autowired
    public UserController(UserService userService, UserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public ModelAndView errorPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("title", "Error!");
        modelAndView.addObject("message", "Oops!");
        modelAndView.setViewName("403");
        return modelAndView;
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView homePage() {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("title", "connect Spring Security");
        modelAndView.addObject("message", "This is homepage");

        modelAndView.setViewName("home");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/users", method = RequestMethod.GET)
    public ModelAndView adminPage() {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("allUsers", this.userService.getAllUsers());

        modelAndView.setViewName("admin/users");
        return modelAndView;
    }

    @RequestMapping(value = "/user/user", method = RequestMethod.GET)
    public ModelAndView userPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/user");
        return modelAndView;
    }


    @RequestMapping(value = "/admin/users/add", method = RequestMethod.GET)
    public ModelAndView addPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/add");
        return modelAndView;
    }


    @RequestMapping(value = "/admin/users/add", method = RequestMethod.POST)

    public ModelAndView saveUser(@ModelAttribute("add") User user,
                                 @RequestParam String[] checkboxRoles,
                                 BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();

        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors() || (userService.findUserByUsername(user.getUsername()) != null)) {

            modelAndView.addObject("myError", "Username or password invalid!");
            modelAndView.setViewName("admin/add");
            return modelAndView;
        }

        if (checkboxRoles.length < 2) {
            modelAndView.addObject("myError", "Role invalid. Choose a role!");
            modelAndView.setViewName("admin/add");
            return modelAndView;
        }
        modelAndView.setViewName("redirect:/admin/users");
        this.userService.addUser(user, checkboxRoles);
        return modelAndView;
    }


    @RequestMapping(value = "/admin/users/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editPage(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        User user = this.userService.getUserById(id);

        String newPassword = "";
        modelAndView.setViewName("admin/edit");

        modelAndView.addObject("user", user);

        modelAndView.addObject("newPassword", newPassword);

        modelAndView.addObject("editRoles", user.getRoles().toString());
        return modelAndView;
    }


    @RequestMapping(value = "/admin/users/edit", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView editUser(@RequestParam(name = "checkboxRoles") String[] checkboxRoles,
                                 @RequestParam(name = "newPassword") String newPassword,
                                 @ModelAttribute("user") User user,
                                 BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();

        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors() || (userService.findUserByUsername(user.getUsername()) != null)) {
            modelAndView.addObject("myError", "Username or password invalid.");
            modelAndView.setViewName("admin/edit");
            return modelAndView;
        }

        if (checkboxRoles.length < 2) {
            modelAndView.addObject("myError", "Role invalid. Choose a role!");
            modelAndView.setViewName("admin/add");
            return modelAndView;
        }

        this.userService.updateUser(user, newPassword, checkboxRoles);
        modelAndView.setViewName("redirect:/admin/users");
        return modelAndView;
    }


    @RequestMapping(value = "/admin/users/delete/{id}")
    public ModelAndView deleteUser(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin/users");
        this.userService.deleteUser(id);
        return modelAndView;
    }
}