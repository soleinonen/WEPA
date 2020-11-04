package wepa.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import wepa.model.Skill;
import wepa.service.AccountService;
import wepa.service.SkillLikeService;

@Controller
public class SkillController {

    @Autowired
    SkillLikeService skillLikeService;

    @Autowired
    AccountService accountService;
    
    @PostMapping("/profile/skills")
    public String addSkill(@Valid @ModelAttribute Skill skill, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "redirect:/profile";
        }
        accountService.addSkillToLoggedInUser(skill);
        return "redirect:/profile";
    }

    @PostMapping("/users/{skillOwnerProfilePath}/skills/{skillId}")
    public String addSkillLike(@PathVariable Long skillId, @PathVariable String skillOwnerProfilePath) {
        skillLikeService.addSkillLike(skillId, skillOwnerProfilePath);
        return "redirect:/users/"+skillOwnerProfilePath;
    }


}
