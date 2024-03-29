package wepa.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import wepa.model.Account;
import wepa.model.Skill;
import wepa.repository.AccountRepository;
import wepa.repository.SkillRepository;

@Service
public class AccountService {

    @Autowired
    PasswordEncoder passwordEncoder;
    
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    SkillRepository skillRepository;

    @Autowired
    SkillLikeService skillLikeService;

    public boolean createNewAccount(String firstname, String surname, String username, String password, String profilePath) {
        if(accountRepository.findByUsername(username) != null || accountRepository.findByProfilePath(profilePath) != null) {
            return false;
        }
        Account account = new Account();
        account.setFirstname(firstname);
        account.setSurname(surname);
        account.setUsername(username);
        account.setPassword(passwordEncoder.encode(password));
        account.setProfilePath(profilePath);
        accountRepository.save(account);
        return true;
    }

    public Account getLoggedInUserAccount() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Account account = accountRepository.findByUsername(auth.getName());
        return account;
    }

    public void addSkillToLoggedInUser(Skill skill) {
        Account account = getLoggedInUserAccount();
        List<Skill> userSkills = account.getSkills();
        for(Skill sk: userSkills) {
            if(sk.getSkillName().equals(skill.getSkillName())) {
                return;
            }
        }
        if(skillRepository.findBySkillName(skill.getSkillName()) != null){
            skill = skillRepository.findBySkillName(skill.getSkillName());
        } else {
            skillRepository.save(skill);
        }
        userSkills.add(skill);
        accountRepository.save(account);
        skillLikeService.addSkillLike(skill.getId(), account.getProfilePath());
    }

    public void changePictureForLoggedInUser(MultipartFile file) throws IOException {
        Account account = getLoggedInUserAccount();
        if(!file.isEmpty()) {
            account.setPicture(file.getBytes());
            accountRepository.save(account);
        }
    }

    public void removeLoggedInUserPicture() {
        Account account = getLoggedInUserAccount();
        account.setPicture(new byte[0]);
        accountRepository.save(account);
    }

    public byte[] getPictureOfLoggedInUser() {
        Account account = getLoggedInUserAccount();
        return account.getPicture();
    }

    public byte[] getPictureByProfilePath(String profilePath) {
        Account account = accountRepository.findByProfilePath(profilePath);
        return account.getPicture();
    }

    public List<Account> queryForUsers(String query) {
        Pageable pageable = PageRequest.of(0, 25);
        return accountRepository.findByQuery(query, pageable);
    }

    public Account getByProfilePath(String profilePath) {
        return accountRepository.findByProfilePath(profilePath);
    }

    public void removeConnection(String profilePath) {
        Account account = getLoggedInUserAccount();
        Account connection = accountRepository.findByProfilePath(profilePath);
        account.removeFriend(connection);
        connection.removeFriend(account);
        accountRepository.save(account);
        accountRepository.save(connection);
    }
}
