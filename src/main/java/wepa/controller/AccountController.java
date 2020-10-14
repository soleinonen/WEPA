package wepa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import wepa.service.AccountService;

@Controller
public class AccountController {

    @Autowired
    AccountService accountService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/registration")
    public String registrate(){
        return "registration";
    }

    @PostMapping("/registration")
    public String addAccount(@RequestParam String firstname, @RequestParam String surname, @RequestParam String username, @RequestParam String password) {
        boolean creationSuccesful = accountService.createNewAccount(firstname, surname, username, password);
        if(creationSuccesful) {
            return "redirect:/login";
        } else {
            return "redirect:/registration";
        }
    }

    @GetMapping("/feed")
    public String login() {
        return "feed";
    }

}