package wepa.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;

import org.springframework.data.jpa.domain.AbstractPersistable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Skill extends AbstractPersistable<Long>{
    
    public Skill(String name) {
        this.skillName = name;
        this.users = new ArrayList<>();
    }

    @ManyToMany(mappedBy = "skills")
    private List<Account> users;

    @NotEmpty
    private String skillName;
}