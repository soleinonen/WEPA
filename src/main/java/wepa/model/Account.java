package wepa.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
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

    @NotEmpty
    @NotNull
    private String profilePath;

    @ManyToMany
    private List<Skill> skills = new ArrayList<>();

    @Lob
    private byte[] picture = new byte[0];

    @ManyToMany
    private List<Account> friends = new ArrayList<>();

    @OneToMany(mappedBy = "reviewer")
    private List<FriendRequest> requestsToBeReviewed = new ArrayList<>();

    @OneToMany(mappedBy = "initiator")
    private List<FriendRequest> requestsSent = new ArrayList<>();

    public void addFriend(Account a) {
        this.friends.add(a);
    }

}