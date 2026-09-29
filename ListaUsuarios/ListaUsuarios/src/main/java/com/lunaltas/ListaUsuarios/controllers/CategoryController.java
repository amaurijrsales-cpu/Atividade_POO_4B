package com.lunaltas.ListaUsuarios.controllers;

import com.lunaltas.ListaUsuarios.model.Category;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    private List<Category> listCategory = new ArrayList<>();
    private long nextId = 1L;

    @GetMapping("/index")
    public String index(ModelMap model) {
        model.addAttribute("categories", listCategory);
        model.addAttribute("size", listCategory.size());
        return "categories/index";
    }

    @GetMapping("/new")
    public String categoryNew(ModelMap model) {
        model.addAttribute("category", new Category());
        return "categories/new";
    }

    @PostMapping("/create")
    public String categoryCreate(@ModelAttribute Category category) { // <--- Corrigido aqui (espaço entre public e String)
        category.setId(nextId++);
        listCategory.add(category);
        return "redirect:/categories/index";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, ModelMap model) {
        Category category = listCategory.stream()
                .filter(c -> c.getId() != null && c.getId().equals(id))
                .findFirst()
                .orElse(null);
        model.addAttribute("category", category);
        return "categories/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Category category) {
        for (int i = 0; i < listCategory.size(); i++) {
            if (listCategory.get(i).getId().equals(category.getId())) {
                listCategory.set(i, category);
                break;
            }
        }
        return "redirect:/categories/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        listCategory.removeIf(c -> c.getId().equals(id));
        return "redirect:/categories/index";
    }

    @GetMapping("/show/{id}")
    public String show(@PathVariable Long id, ModelMap model) {
        Category category = listCategory.stream()
                .filter(c -> c.getId() != null && c.getId().equals(id))
                .findFirst()
                .orElse(null);
        model.addAttribute("category", category);
        return "categories/show";
    }
}