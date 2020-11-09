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
import wepa.repository.AccountRepository;
import wepa.repository.CommentRepository;
import wepa.repository.PostRepository;
import wepa.service.CommentService;
import wepa.service.PostService;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration
public class CommentServiceTest {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    CommentService commentService;

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
        postService.createPost("post");
    }

    @After
    public void after() {
        commentRepository.deleteAll();
        postRepository.deleteAll();
        accountRepository.deleteAll();
    }

    @Test
    @WithMockUser
    public void createCommentCreatesComment() {
        commentService.createComment("comment", postRepository.findAll().get(0).getId());
        assertEquals("comment", commentRepository.findAll().get(0).getCommentText());
        assertEquals(postRepository.findAll().get(0).getId(), commentRepository.findAll().get(0).getPost().getId());
        assertEquals("user", postRepository.findAll().get(0).getOwner().getUsername());
    }
    
}
