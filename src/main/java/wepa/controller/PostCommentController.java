package wepa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import wepa.model.Post;
import wepa.service.AccountService;
import wepa.service.CommentService;
import wepa.service.PostService;

@Controller
public class PostCommentController {

    @Autowired
    PostService postService;

    @Autowired
    AccountService accountService;

    @Autowired
    CommentService commentService;

    @PostMapping("/posts/add")
    public String createPost(@RequestParam String postText){
        postService.createPost(postText);
        return "redirect:/feed";
    }


    @GetMapping("/feed")
    public String showFeed(Model model) {
        List<Post> posts = postService.get25Posts();
        model.addAttribute("posts", posts);
        return "feed";
    }

    @PostMapping("/posts/{id}/comments/add")
    public String createComment(@RequestParam String commentText, @PathVariable Long id) {
        commentService.createComment(commentText, id);
        return "redirect:/feed";
    }
    
}
