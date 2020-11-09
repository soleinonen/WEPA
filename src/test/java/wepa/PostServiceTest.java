package wepa;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import wepa.model.Account;
import wepa.model.Post;
import wepa.repository.AccountRepository;
import wepa.repository.PostRepository;
import wepa.service.PostService;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration
public class PostServiceTest {
    
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    PostService postService;

    @Before
    public void initObjects() {
        Account account = new Account();
        account.setFirstname("firstname");
        account.setSurname("surname");
        account.setUsername("user");
        account.setPassword("password");
        account.setProfilePath("profile");
        accountRepository.save(account);
        Account additionalAccount = new Account();
        additionalAccount.setFirstname("fake");
        additionalAccount.setSurname("account");
        additionalAccount.setUsername("fake");
        additionalAccount.setPassword("password");
        additionalAccount.setProfilePath("fakeprofile");
        accountRepository.save(additionalAccount);
    }

    @After
    public void after() {
        postRepository.deleteAll();
        accountRepository.deleteAll();
    }

    @Test
    @WithMockUser
    public void createPostCreatesPost() {
        postService.createPost("TEST POST");
        assertEquals("TEST POST", postRepository.findAll().get(0).getPostText());
        assertEquals(Integer.valueOf(0), postRepository.findAll().get(0).getLikeCount());
    }

    @Test
    @WithMockUser
    public void incrementLikeCountIncrements() {
        postService.createPost("TEST POST");
        Post post = postRepository.findAll().get(0);
        postService.incrementLikeCount(post);
        assertEquals(Integer.valueOf(1), postRepository.findAll().get(0).getLikeCount());
        postService.incrementLikeCount(post);
        assertEquals(Integer.valueOf(2), postRepository.findAll().get(0).getLikeCount());
    }
}
