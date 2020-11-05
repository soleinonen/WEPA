package wepa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import wepa.model.Account;
import wepa.model.Skill;
import wepa.model.SkillLike;
import wepa.repository.SkillLikeRepository;
import wepa.repository.SkillLikesDto;
import wepa.repository.SkillRepository;

@Service
public class SkillLikeService {

    @Autowired
    AccountService accountService;

    @Autowired
    SkillLikeRepository skillLikeRepository;

    @Autowired
    SkillRepository skillRepository;

    public List<List<SkillLikesDto>> getSkillLikes(Account account) {
        List<SkillLikesDto> list = skillLikeRepository.findSkillLikeByOwner(account.getId());
        int size = list.size();
        List<List<SkillLikesDto>> result = new ArrayList<>(); 
        List<SkillLikesDto> topThree = new ArrayList<>();
        List<SkillLikesDto> rest = new ArrayList<>();
        if(size > 3) {
            topThree = list.subList(0, 3);
            rest = list.subList(3, size);
        } else {
            topThree = list;
            rest = new ArrayList<>();
        }
        result.add(topThree);
        result.add(rest);
        return result;
    }

    public void addSkillLike(Long skillId, String skillOwnerProfilePath) {
        SkillLike sl = new SkillLike();
        Skill skill = skillRepository.getOne(skillId);
        Account skillOwner = accountService.getByProfilePath(skillOwnerProfilePath);
        Account loggedInAccount = accountService.getLoggedInUserAccount();
        sl.setSkill(skill);
        sl.setSkillLiker(loggedInAccount);
        sl.setSkillOwner(skillOwner);
        if(!skillLikeRepository.exists(Example.of(sl)) && (loggedInAccount.getFriends().contains(skillOwner) || skillOwner.equals(loggedInAccount))) {
            skillLikeRepository.save(sl);
        }
    }
    
}
