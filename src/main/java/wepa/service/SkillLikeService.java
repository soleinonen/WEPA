package wepa.service;

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

    public List<SkillLikesDto> getSkillLikes(Account account) {
        return skillLikeRepository.findSkillLikeByOwner(account.getId());
    }

    public void addSkillLike(Long skillId, String skillOwnerProfilePath) {
        SkillLike sl = new SkillLike();
        Skill skill = skillRepository.getOne(skillId);
        Account skillOwner = accountService.getByProfilePath(skillOwnerProfilePath);
        sl.setSkill(skill);
        sl.setSkillLiker(accountService.getLoggedInUserAccount());
        sl.setSkillOwner(skillOwner);
        if(!skillLikeRepository.exists(Example.of(sl))) {
            skillLikeRepository.save(sl);
        }
    }
    
}
