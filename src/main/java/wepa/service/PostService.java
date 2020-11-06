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
        postRepository.save(post);
    }

    public List<Post> getAllPosts() {
        Account account = accountService.getLoggedInUserAccount();
        List<Account> accounts = account.getFriends();
        accounts.add(account);
        List<Post> posts = postRepository.findByOwnerInOrderByTimestampDesc(accounts);
        return posts;
    }

    public List<Post> get25Posts(){
        List<Post> posts = getAllPosts();
        if(posts.size()>25) {
            posts = posts.subList(0, 25);
        }
        return posts;
    }

    public Post getPostById(Long id) {
        return postRepository.getOne(id);
    }
    
}
