package ba.sum.fpmoz.elearningplatform.controller;

import ba.sum.fpmoz.elearningplatform.model.User;
import ba.sum.fpmoz.elearningplatform.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MyFirstController {

    @Autowired
    private UsersRepository usersRepository;

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

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String registerNewUser(User user) {
            this.usersRepository.save(user);
            return "redirect:/users";
        }
    }
