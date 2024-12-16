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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        Optional<User> user = this.usersRepository.findById(id);
        if (user.isPresent()) {
            this.usersRepository.delete(user.get());
        }
        return "redirect:/users";
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable Long id, Model model) {
        Optional<User> user = this.usersRepository.findById(id);
        if (user.isPresent()) {
            model.addAttribute("user", user.get());
            return "users-edit";
        }
        return "redirect:/users";
    }

    @PostMapping("/users/edit")
    public String editUser(User user, BindingResult result, Model model) {
        Long id = (long) user.getId();
        Optional<User> userOptional = this.usersRepository.findById(id);
        if (userOptional.isPresent()) {
            User userFromDb = userOptional.get();
            userFromDb.setFirstname(user.getFirstname());
            userFromDb.setLastname(user.getLastname());
            this.usersRepository.save(userFromDb);
        }
        return "redirect:/users";
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
