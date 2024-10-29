package ba.sum.fpmoz.elearningplatform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyFirstController {

    @GetMapping("/")
    public String index() {
        return "index";
    }
}
