package hh.sof03.forum.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

import hh.sof03.forum.domain.Category;
import hh.sof03.forum.domain.CategoryRepository;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CategoryController {

    @Autowired
    private CategoryRepository categoryrepo;

    @GetMapping({ "/", "/index" })
    public String getCategories(Model model) {
        model.addAttribute("categories", categoryrepo.findAll());
        return "index";
    }

    @GetMapping("/addcategory")
    public String addCategory(Model model) {
        model.addAttribute("category", new Category());
        return "addcategory";
    }

    @PostMapping("/addcategory")
    public String savecategory(@Valid Category category, BindingResult result) {
        if (result.hasErrors()) {
            return "addcategory";
        } else {
            categoryrepo.save(category);
            return "redirect:index";
        }
    }

    @GetMapping("/deletecategory")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteCategory(@RequestParam(name = "id") Long id) {
        categoryrepo.deleteById(id);
        return "redirect:index";
    }

    @GetMapping("/editcategory")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String editCategory(@RequestParam(name = "id") Long id, Model model) {
        model.addAttribute("category", categoryrepo.findByCategoryid(id));
        return "editcategory";
    }

    @PostMapping("/editcategory")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String updatecategory(Category category) {
        categoryrepo.save(category);
        return "redirect:index";
    }
}
