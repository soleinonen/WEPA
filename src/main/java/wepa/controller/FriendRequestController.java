package wepa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import wepa.service.AccountService;
import wepa.service.FriendRequestService;

@Controller
public class FriendRequestController {

    @Autowired
    AccountService accountService;

    @Autowired
    FriendRequestService friendRequestService;

    @PostMapping("/friendrequest/{profilePath}")
    public String sendFriendRequest(@PathVariable String profilePath) {
        friendRequestService.createNewRequest(profilePath);
        return "redirect:/users/"+profilePath;
    }

    @PostMapping("/friendrequest/accept/{id}")
    public String acceptFriendRequest(@PathVariable Long id) {
        friendRequestService.acceptRequest(id);
        return "redirect:/profile";
    }

    @PostMapping("/friendrequest/decline/{id}")
    public String declineFriendRequest(@PathVariable Long id) {
        friendRequestService.declineRequest(id);
        return "redirect:/profile";
    }
}
