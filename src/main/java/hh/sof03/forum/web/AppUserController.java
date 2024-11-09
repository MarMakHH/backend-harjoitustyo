package hh.sof03.forum.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import hh.sof03.forum.domain.AppUser;
import hh.sof03.forum.domain.AppUserRepository;
import jakarta.validation.Valid;

@Controller
public class AppUserController {

    @Autowired
    private AppUserRepository userrepo;

    @GetMapping("/signup")
    public String userSignup(Model model) {
        model.addAttribute("newuser", new AppUser());
        return "signup";
    }

    @PostMapping("/signup")
    public String saveNewUser(@Valid AppUser newUser, BindingResult result) {
        if (result.hasErrors()) {
            return "signup";
        } else {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            newUser.setPasswordHash(encoder.encode(newUser.getPasswordHash()));
            newUser.setRole("USER");
            userrepo.save(newUser);
            return "redirect:index";
        }
    }
}
