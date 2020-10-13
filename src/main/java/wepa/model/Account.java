package wepa.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.domain.AbstractPersistable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account extends AbstractPersistable<Long> {

    public Account(String firstname, String surname, String username, String password) {
        this.firstname = firstname;
        this.surname = surname;
        this.password = password;
        this.skills = new ArrayList<>();
    }

    @NotEmpty
    @NotNull
    private String firstname;

    @NotEmpty
    @NotNull
    private String surname;

    @NotEmpty
    @NotNull
    private String username;

    @NotEmpty
    @NotNull
    private String password;

    @ManyToMany
    private List<Skill> skills;

    @Lob
    private byte[] picture;

}