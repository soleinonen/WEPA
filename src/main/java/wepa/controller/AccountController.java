package wepa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import wepa.model.Account;
import wepa.repository.AccountRepository;

@Controller
public class AccountController {
    
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/registration")
    public String registrate(){
        return "registration";
    }

    @PostMapping("/registration")
    public String addAccount(@RequestParam String firstname, @RequestParam String surname, @RequestParam String username, @RequestParam String password) {
        if(accountRepository.findByUsername(username) != null) {
            return "redirect:/registration";
        }
        Account account = new Account(firstname, surname, username, passwordEncoder.encode(password));
        accountRepository.save(account);
        return "redirect:/login";
    }

    @GetMapping("/feed")
    public String login() {
        return "feed";
    }

}