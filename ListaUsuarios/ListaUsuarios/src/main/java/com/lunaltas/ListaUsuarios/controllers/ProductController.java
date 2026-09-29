package com.lunaltas.ListaUsuarios.controllers;

import com.lunaltas.ListaUsuarios.model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    private List<Product> listProduct = new ArrayList<>();
    private long nextId = 1L; // Controlado por contador para evitar conflito de IDs

    @GetMapping("/index")
    public String index(ModelMap model) {
        model.addAttribute("products", listProduct);
        model.addAttribute("size", listProduct.size());
        return "products/index";
    }

    @GetMapping("/new")
    public String productNew(ModelMap model) {
        model.addAttribute("product", new Product());
        return "products/new";
    }

    @PostMapping("/create")
    public String productCreate(@ModelAttribute Product product) {
        product.setId(nextId++); // Atribui e incrementa o ID corretamente
        listProduct.add(product);
        return "redirect:/products/index";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, ModelMap model) {
        Product product = listProduct.stream()
                .filter(p -> p.getId() != null && p.getId().equals(id))
                .findFirst()
                .orElse(null);
        model.addAttribute("product", product);
        return "products/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Product product) {
        for (int i = 0; i < listProduct.size(); i++) {
            if (listProduct.get(i).getId().equals(product.getId())) {
                listProduct.set(i, product); // Substitui o produto atualizado na lista
                break;
            }
        }
        return "redirect:/products/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        listProduct.removeIf(p -> p.getId().equals(id));
        return "redirect:/products/index";
    }

    @GetMapping("/show/{id}")
    public String show(@PathVariable Long id, ModelMap model) {
        Product product = listProduct.stream()
                .filter(p -> p.getId() != null && p.getId().equals(id))
                .findFirst()
                .orElse(null);
        model.addAttribute("product", product);
        return "products/show";
    }
}