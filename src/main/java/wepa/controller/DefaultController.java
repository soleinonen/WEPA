package wepa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DefaultController {

    @Autowired
    Environment env;

    @GetMapping("*")
    public String defaultController(Model model) {
        return "login";
    }

}
