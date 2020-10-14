package wepa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import wepa.model.Account;
import wepa.service.AccountService;

@Controller
public class SearchController {

    @Autowired
    AccountService accountService;

    @GetMapping("/search")
    public String searchUsers(@RequestParam("query") String query, Model model) {
        List<Account> users = accountService.queryForUsers(query);
        if(users.isEmpty()) {
            model.addAttribute("error", "No persons found, check your input.");
        }
        model.addAttribute("persons", users);
        return "searchresult";
    }
    
}
