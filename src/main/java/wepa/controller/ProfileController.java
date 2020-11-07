package wepa.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import wepa.model.Account;
import wepa.model.FriendRequest;
import wepa.repository.SkillLikesDto;
import wepa.service.AccountService;
import wepa.service.FriendRequestService;
import wepa.service.SkillLikeService;

@Controller
public class ProfileController {

    @Autowired
    AccountService accountService;

    @Autowired
    FriendRequestService friendRequestService;

    @Autowired
    SkillLikeService skillLikeService;

    @GetMapping("/profile")
    public String showProfile(Model model) {
        Account account = accountService.getLoggedInUserAccount();
        List<FriendRequest> requests = friendRequestService.getRequestsToBeReviewed(account);
        model.addAttribute("name", account.getFirstname()+" "+account.getSurname());
        List<List<SkillLikesDto>> list = skillLikeService.getSkillLikes(account);
        model.addAttribute("topThreeSkills", list.get(0));
        model.addAttribute("otherSkills", list.get(1));
        model.addAttribute("requests", requests);
        model.addAttribute("profilePath", account.getProfilePath());
        return "profile";
    }
    
    @PostMapping("/profile/picture")
    public String createPicture(@RequestParam("file") MultipartFile file) throws IOException {
        accountService.changePictureForLoggedInUser(file);
        return "redirect:/profile";
    }

    @PostMapping("/profile/picture/remove")
    public String removePicture() {
        accountService.removeLoggedInUserPicture();
        return "redirect:/profile";
    }

    @GetMapping("/profile/picture/content")
    @ResponseBody
    public byte[] getContent() {
        return accountService.getPictureOfLoggedInUser();
    }

    @GetMapping("/profile/connections")
    public String showProfileConnections(Model model) {
        Account account = accountService.getLoggedInUserAccount();
        model.addAttribute("connections", account.getFriends());
        model.addAttribute("requests", friendRequestService.getRequestsToBeReviewed(account));
        return "connections";
    }

    @PostMapping("/profile/connections/remove/{profilePath}")
    public String removeConnection(@PathVariable String profilePath) {
        accountService.removeConnection(profilePath);
        return "redirect:/profile/connections";
    }
}
