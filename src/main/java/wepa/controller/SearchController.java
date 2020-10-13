package wepa.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import wepa.model.Account;
import wepa.repository.AccountRepository;

@Controller
public class SearchController {

    @Autowired
    AccountRepository accountRepository;

    @GetMapping("/search")
    public String searchUsers(@RequestParam("query") String query, Model model) {
        List<Account> persons = new ArrayList<>();
        List<String> queryParts = Arrays.asList(query.split(" "));
        persons = accountRepository.findBySurnameIn(queryParts);
        if(persons.isEmpty()) {
            model.addAttribute("error", "No persons found, check your input.");
        }
        model.addAttribute("persons", persons);
        return "searchresult";
    }
    
}
