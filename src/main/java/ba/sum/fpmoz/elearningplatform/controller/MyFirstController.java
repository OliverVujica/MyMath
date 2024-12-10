package ba.sum.fpmoz.elearningplatform.controller;

import ba.sum.fpmoz.elearningplatform.model.User;
import ba.sum.fpmoz.elearningplatform.repository.UsersRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MyFirstController {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder encoder;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/users")
    public String users(Model model) {
        List<User> users = this.usersRepository.findAll();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        User user = new User();
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerNewUser(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "register";
        }
        user.setPassword(this.encoder.encode(user.getPassword()));
        this.usersRepository.save(user);
        return "redirect:/users";
    }
}
