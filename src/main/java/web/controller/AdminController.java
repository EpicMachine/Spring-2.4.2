package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/admin/hello", method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        List<String> messages = new ArrayList<>();
        messages.add("Hello!");
        messages.add("I'm Spring MVC-SECURITY application");
        messages.add("5.2.0 version by sep'19 ");
        model.addAttribute("messages", messages);
        return "hello";
    }


    @GetMapping(value = "/")
    public String printUsers(Model model) {
        model.addAttribute("userList", userService.listUsers());
        return "users";
    }

    @GetMapping("/edit/{id}")
    public String startEditUser(@PathVariable(name = "id") Long id, Model model) {
        model.addAttribute("userById", userService.getUserById(id));
        return "edit";
    }

    @PostMapping("/edit/{id}")
    public String endEditUser(@ModelAttribute("userById") User user) {
        userService.changeUser(user);
        return "redirect:/admin/";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable(name = "id") Long id, Model model) {
        userService.deleteUser(id);
        model.addAttribute("userList", userService.listUsers());
        return "redirect:/admin/";
    }

    @GetMapping("/add")
    public String addUser(Model model) {
        model.addAttribute("userToAdd", new User());
        return "add";
    }

    @PostMapping("/add")
    public String submissionResult(@ModelAttribute("userToAdd") User user) {
        userService.addUser(user);
        return "redirect:/admin/";
    }
}
