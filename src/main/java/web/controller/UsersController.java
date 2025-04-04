package web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UsersService;

import javax.validation.Valid;


@Controller
@RequestMapping("/users")
public class UsersController {

    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("users", usersService.index());
        return "users/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", usersService.show(id));
        return "users/show";
    }

    @GetMapping("/new")
    public String newUser (@ModelAttribute("user") User user) {
        return "users/new";
    }

    @PostMapping
    public String create(@ModelAttribute("user") @Valid User user,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "users/new";
        }
        usersService.save(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", usersService.show(id));
        return "users/edit";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable("id") int id, @ModelAttribute("user") @Valid User user,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "users/edit";
        }
        usersService.update(id, user);
        return "redirect:/users";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") int id) {
        usersService.delete(id);
        return "redirect:/users";
    }
}