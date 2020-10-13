package wepa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import wepa.model.Skill;

public interface SkillRepository extends JpaRepository<Skill, Long> {
    Skill findBySkillName(String skillName);
    
}
