package wepa.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import wepa.model.Account;
import wepa.model.Skill;
import wepa.service.AccountService;

@Controller
public class ProfileController {

    @Autowired
    AccountService accountService;

    @GetMapping("/profile")
    public String showProfile(Model model) {
        Account account = accountService.getLoggedInUserAccount();
        List<Skill> skills = accountService.getSkillsOfLoggedInUser();
        model.addAttribute("name", account.getFirstname()+" "+account.getSurname());
        model.addAttribute("skills", skills);
        return "profile";
    }

    @PostMapping("/profile")
    public String addSkill(@Valid @ModelAttribute Skill skill, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "redirect:/profile";
        }
        accountService.addSkillToLoggedInUser(skill);
        return "redirect:/profile";
    }
    
    @PostMapping("/profile/picture")
    public String createPicture(@RequestParam("file") MultipartFile file) throws IOException {
        accountService.changePictureForLoggedInUser(file);
        return "redirect:/profile";
    }

    @GetMapping("/profile/picture/content")
    @ResponseBody
    public byte[] getContent() {
        return accountService.getPictureOfLoggedInUser();
    }
}
