package wepa.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wepa.model.Comment;
import wepa.repository.CommentRepository;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    PostService postService;

    @Autowired
    AccountService accountService;

    public void createComment(String commentText, Long postId) {
        Comment comment = new Comment();
        comment.setCommentText(commentText);
        comment.setPost(postService.getPostById(postId));
        comment.setTimestamp(LocalDateTime.now());
        comment.setOwner(accountService.getLoggedInUserAccount());
        commentRepository.save(comment);
    }
    
}
