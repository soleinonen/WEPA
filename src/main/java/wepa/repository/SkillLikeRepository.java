package wepa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import wepa.model.SkillLike;

public interface SkillLikeRepository extends JpaRepository<SkillLike, Long>{

    @Query(value="SELECT new wepa.repository.SkillLikesDto(sl.skill.skillName, count(sl) as likeCount, sl.skill.id)  FROM SkillLike sl WHERE SKILL_OWNER_ID = :skillId GROUP BY sl.skill.id, sl.skill.skillName ORDER BY likeCount DESC")
    List<SkillLikesDto> findSkillLikeByOwner(@Param("skillId") Long skillId);
}
