package wepa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import wepa.model.Account;
import wepa.model.Skill;
import wepa.service.AccountService;

@Controller
public class UserProfileController {
    
    @Autowired
    AccountService accountService;

    @GetMapping("/users/{profilePath}")
    public String showUserProfile(@PathVariable String profilePath, Model model) {
        Account account = accountService.getByProfilePath(profilePath);
        List<Skill> skills = account.getSkills();
        model.addAttribute("name", account.getFirstname()+" "+account.getSurname());
        model.addAttribute("skills", skills);
        model.addAttribute("profilePath", account.getProfilePath());
        model.addAttribute("friendsOfLoggedInUser", accountService.getLoggedInUserAccount().getFriends());
        return "user";
    }

    @GetMapping("/users/{profilePath}/content")
    @ResponseBody
    public byte[] getContent(@PathVariable String profilePath) {
        return accountService.getPictureByProfilePath(profilePath);
    }
}