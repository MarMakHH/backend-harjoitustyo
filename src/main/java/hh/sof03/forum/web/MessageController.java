package hh.sof03.forum.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import hh.sof03.forum.domain.AppUser;
import hh.sof03.forum.domain.AppUserRepository;
import hh.sof03.forum.domain.CategoryRepository;
import hh.sof03.forum.domain.Message;
import hh.sof03.forum.domain.MessageRepository;
import hh.sof03.forum.domain.TopicRepository;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MessageController {

    @Autowired
    private AppUserRepository userrepo;

    @Autowired
    private MessageRepository messagerepo;

    @Autowired
    private TopicRepository topicrepo;

    @Autowired
    private CategoryRepository categoryrepo;

    @GetMapping("/topic")
    public String getMessages(@RequestParam(name = "id") Long topicid, Model model) {
        model.addAttribute("topicname", topicrepo.findByTopicid(topicid).getHeader());
        model.addAttribute("categoryname", categoryrepo.findByTopics(topicrepo.findByTopicid(topicid)).getHeader());
        model.addAttribute("categoryid", categoryrepo.findByTopics(topicrepo.findByTopicid(topicid)).getCategoryid());
        model.addAttribute("messages", messagerepo.findByTopic(topicrepo.findByTopicid(topicid)));
        return "topic";
    }

    @GetMapping("/addmessage")
    public String addMessage(Model model, @AuthenticationPrincipal UserDetails currentUser) {
        AppUser user = (AppUser) userrepo.findByUsername(currentUser.getUsername());
        model.addAttribute("senderUser", user);
        model.addAttribute("message", new Message());
        return "addmessage";
    }

    @PostMapping("/addmessage")
    public String saveMessage(@Valid Message message, BindingResult result) {
        if (result.hasErrors()) {
            return "addmessage";
        }
        String id = Long.toString(message.getTopic().getTopicid());
        messagerepo.save(message);
        return "redirect:topic?id=" + id;
    }

    @GetMapping("/deletemessage")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteMessage(@RequestParam(name = "id") Long id) {
        Long returnid = messagerepo.findById(id).get().getTopic().getTopicid();
        messagerepo.deleteById(id);
        return "redirect:topic?id=" + returnid;
    }

    @GetMapping("/editmessage")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String editMessage(@RequestParam(name = "id") Long id, Model model) {
        model.addAttribute("message", messagerepo.findById(id).get());
        return "editmessage";
    }

    @PostMapping("/editmessage")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String updateMessage(Message message) {
        String id = Long.toString(message.getTopic().getTopicid());
        messagerepo.save(message);
        return "redirect:topic?id=" + id;
    }

    @GetMapping("/censormessage")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String censorMessage(@RequestParam(name = "id") Long id) {
        Long returnid = messagerepo.findById(id).get().getTopic().getTopicid();
        Message message = messagerepo.findById(id).get();
        message.setDesc("(Message removed by admin)");
        messagerepo.save(message);
        return "redirect:topic?id=" + returnid;
    }
}
