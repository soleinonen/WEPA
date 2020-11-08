package wepa.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wepa.model.Account;
import wepa.model.Post;
import wepa.repository.PostRepository;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    AccountService accountService;

    public void createPost(String postText) {
        Account account = accountService.getLoggedInUserAccount();
        Post post = new Post();
        post.setOwner(account);
        post.setPostText(postText);
        post.setTimestamp(LocalDateTime.now());
        post.setLikeCount(0);
        postRepository.save(post);
    }

    public List<Post> getLoggedInUserPosts() {
        Account account = accountService.getLoggedInUserAccount();
        List<Account> accounts = account.getFriends();
        accounts.add(account);
        List<Post> posts = postRepository.findTop25ByOwnerInOrderByTimestampDesc(accounts);
        return posts;
    }

    public Post getPostById(Long id) {
        return postRepository.getOne(id);
    }

    public void incrementLikeCount(Post post) {
        post.setLikeCount(post.getLikeCount()+1);
        postRepository.save(post);
    }
    
}
