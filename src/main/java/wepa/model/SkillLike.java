package wepa.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import org.springframework.data.jpa.domain.AbstractPersistable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SkillLike extends AbstractPersistable<Long>{

    @OneToOne
    private Skill skill;

    @OneToOne
    private Account skillOwner;

    @OneToOne
    private Account skillLiker;
    
}
