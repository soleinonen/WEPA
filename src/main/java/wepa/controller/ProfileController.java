package wepa.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
import wepa.repository.AccountRepository;
import wepa.repository.SkillRepository;

@Controller
public class ProfileController {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    SkillRepository skillRepository;

    @GetMapping("/profile")
    public String showProfile(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Account account = accountRepository.findByUsername(username);
        List<Skill> skills = account.getSkills();
        model.addAttribute("name", account.getFirstname()+" "+account.getSurname());
        model.addAttribute("skills", skills);
        return "profile";
    }

    @PostMapping("/profile")
    public String addSkill(@Valid @ModelAttribute Skill skill, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "redirect:/profile";
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Account account = accountRepository.findByUsername(auth.getName());
        List<Skill> userSkills = account.getSkills();
        for(Skill sk: userSkills) {
            if(sk.getSkillName().equals(skill.getSkillName())) {
                return "redirect:/profile";
            }
        }
        if(skillRepository.findBySkillName(skill.getSkillName()) != null){
            skill = skillRepository.findBySkillName(skill.getSkillName());
        } else {
            skillRepository.save(skill);
        }
        userSkills.add(skill);
        account.setSkills(userSkills);
        accountRepository.save(account);
        return "redirect:/profile";
    }
    
    @PostMapping("/profile/picture")
    public String createPicture(@RequestParam("file") MultipartFile file) throws IOException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Account account = accountRepository.findByUsername(auth.getName());
        account.setPicture(file.getBytes());
        accountRepository.save(account);
        return "redirect:/profile";
    }

    @GetMapping("/profile/picture/content")
    @ResponseBody
    public byte[] getContent() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Account account = accountRepository.findByUsername(auth.getName());
        return account.getPicture();
    }
}
