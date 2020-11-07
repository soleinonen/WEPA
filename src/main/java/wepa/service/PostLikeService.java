package wepa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import wepa.model.Account;
import wepa.model.Post;
import wepa.model.PostLike;
import wepa.repository.PostLikeRepository;

@Service
public class PostLikeService {

    @Autowired
    PostLikeRepository postLikeRepository;

    @Autowired
    AccountService accountService;

    @Autowired
    PostService postService;

    public void createPostLike(Long postId) {
        Account postLiker = accountService.getLoggedInUserAccount();
        Post post = postService.getPostById(postId);
        PostLike postLike = new PostLike(post, postLiker);
        if(!postLikeRepository.exists(Example.of(postLike)) && postLiker.getFriends().contains(post.getOwner())) {
            postLikeRepository.save(postLike);
            postService.incrementLikeCount(post);
        }
        
    }
    
}
