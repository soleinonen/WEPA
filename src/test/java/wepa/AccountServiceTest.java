package wepa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

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
import wepa.repository.SkillRepository;
import wepa.service.AccountService;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration
public class AccountServiceTest {
    
    @Autowired
    AccountService accountService;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    SkillRepository skillRepository;

    @Before
    public void initObjects() {
        Account account = new Account();
        account.setFirstname("firstname");
        account.setSurname("surname");
        account.setUsername("user");
        account.setPassword("password");
        account.setProfilePath("profile");
        accountRepository.save(account);
        accountService.createNewAccount("etunimi", "sukunimi", "tunnus", "salasana", "profiili");
    }

    @After
    public void after() {
        accountRepository.deleteById(accountRepository.findByUsername("user").getId());
    }

    @Test
    public void testUserCreation() {
        assertSame("etunimi", accountRepository.findByProfilePath("profiili").getFirstname());
    }

    @Test
    public void cannotCreateUserWithSameUsername() {
        assertEquals(false, accountService.createNewAccount("etu", "suku", "tunnus", "salasana", "profiili2"));
        assertEquals(null, accountRepository.findByFirstname("etu"));
    }

    @Test
    public void cannotCreateUserWithSameProfilePath() {
        assertEquals(false, accountService.createNewAccount("e", "s", "t", "salasana", "profiili"));
        assertEquals(null, accountRepository.findByFirstname("e"));
    }

    @Test
    @WithMockUser
    public void getLoggedUnUserReturnsLoggedInUserAccount() {
        Account user = accountService.getLoggedInUserAccount();
        assertEquals("firstname", user.getFirstname());
        assertEquals("surname", user.getSurname());
        assertEquals("profile", user.getProfilePath());
    }
}
