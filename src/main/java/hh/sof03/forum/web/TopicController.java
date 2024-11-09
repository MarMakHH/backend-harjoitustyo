package hh.sof03.forum.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import hh.sof03.forum.domain.CategoryRepository;
import hh.sof03.forum.domain.Topic;
import hh.sof03.forum.domain.TopicRepository;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TopicController {

    @Autowired
    private TopicRepository topicrepo;

    @Autowired
    private CategoryRepository categoryrepo;

    @GetMapping("/category")
    public String getTopicList(@RequestParam(name = "id") Long categoryid, Model model) {
        model.addAttribute("categoryname", categoryrepo.findByCategoryid(categoryid).getHeader());
        model.addAttribute("topics", topicrepo.findByCategory(categoryrepo.findByCategoryid(categoryid)));
        return "category";
    }

    @GetMapping("/addtopic")
    public String addTopic(@RequestParam("id") Long categoryid, Model model) {
        model.addAttribute("topic", new Topic());
        return "addtopic";
    }

    @PostMapping("/addtopic")
    public String saveTopic( @Valid Topic topic, BindingResult result) {
        if (result.hasErrors()) {
            return "addtopic";
        }
        String id = Long.toString(topic.getCategory().getCategoryid());
        topicrepo.save(topic);
        return "redirect:category?id=" + id;
    }

    @GetMapping("/deletetopic")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteTopic(@RequestParam(name = "id") Long id) {
        Long returnid = topicrepo.findById(id).get().getCategory().getCategoryid();
        topicrepo.deleteById(id);
        return "redirect:category?id=" + returnid;
    }

    @GetMapping("/edittopic")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String editTopic(@RequestParam(name = "id") Long id, Model model) {
        model.addAttribute("topic", topicrepo.findByTopicid(id));
        return "edittopic";
    }

    @PostMapping("/edittopic")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String updateTopic(Topic topic) {
        String id = Long.toString(topic.getCategory().getCategoryid());
        topicrepo.save(topic);
        return "redirect:category?id=" + id;
    }

}
