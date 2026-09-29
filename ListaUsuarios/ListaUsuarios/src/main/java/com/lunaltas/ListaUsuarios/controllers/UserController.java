package com.lunaltas.ListaUsuarios.controllers;

import com.lunaltas.ListaUsuarios.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    private List<User> listUser = new ArrayList<>();

    @GetMapping("/index")
    public String index(ModelMap model) {
        model.addAttribute("users", listUser);
        model.addAttribute("size", listUser.size());
        return "users/index"; // Aponta para templates/users/index.html
    }

    @GetMapping("/new")
    public String userNew(ModelMap model) {
        model.addAttribute("user", new User());
        return "users/new"; // Aponta para templates/users/new.html
    }

    @PostMapping("/create")
    public String userCreate(@ModelAttribute User user) {
        long id = listUser.size() + 1;
        user.setId(id);
        listUser.add(user);
        return "redirect:/users/index";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, ModelMap model) {
        User user = listUser.stream().filter(u -> u.getId().equals(id)).findFirst().orElse(null);
        model.addAttribute("user", user);
        return "users/edit"; // Aponta para templates/users/edit.html
    }

    @PostMapping("/update")
    public String update(@ModelAttribute User user) {
        listUser.stream().filter(u -> u.getId().equals(user.getId())).findFirst().ifPresent(u -> {
            u.setName(user.getName());
            u.setArroba(user.getArroba());
            u.setCpf(user.getCpf());
            u.setEmail(user.getEmail());
            u.setIdade(user.getIdade());
        });
        return "redirect:/users/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        listUser.removeIf(u -> u.getId().equals(id));
        return "redirect:/users/index";
    }

    @GetMapping("/show/{id}")
    public String show(@PathVariable Long id, ModelMap model) {
        User user = listUser.stream().filter(u -> u.getId().equals(id)).findFirst().orElse(null);
        model.addAttribute("user", user);
        return "users/show"; // Aponta para templates/users/show.html
    }
}