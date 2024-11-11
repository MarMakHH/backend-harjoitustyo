package hh.sof03.forum.web;

import org.springframework.web.bind.annotation.RestController;

import hh.sof03.forum.domain.Category;
import hh.sof03.forum.domain.CategoryRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
public class CategoryRestController {

    @Autowired
    private CategoryRepository crepo;

    @GetMapping("/categorylist")
    public List<Category> getCategoryListRest() {
        return (List<Category>) crepo.findAll();
    }

    @GetMapping("/category/{id}")
    public Category findCategoryRest(@PathVariable("id") Long categoryid) {
        return crepo.findById(categoryid).get();
    }
    
}
