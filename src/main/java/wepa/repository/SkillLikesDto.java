package wepa.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SkillLikesDto {

    private String skillName;

    private Long likeCount;

    private Long id;

}
